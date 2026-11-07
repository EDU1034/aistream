package com.ipvio.stream

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * Recomendações "IA" para o AI Stream TV, em dois modos:
 *  - Com chave (Ajustes > API > IA): usa uma API compatível com OpenAI (chat/completions) para sugerir títulos,
 *    que são casados com os títulos da sua playlist.
 *  - Sem chave: cai num modo baseado no TMDB/na própria playlist (em alta, por gênero/termo) — sem custo.
 * Só recomenda o que existe na sua playlist, para que dê para abrir e assistir.
 */
object AiRepo {

    data class Result(val items: List<Item>, val note: String, val usedAi: Boolean)

    suspend fun recommend(all: List<Item>, query: String): Result = withContext(Dispatchers.IO) {
        val q = query.trim()
        val pool = all.filter { it.kind == Kind.MOVIE || it.kind == Kind.SERIES }
        if (Config.aiEnabled()) {
            val titles = runCatching { llmTitles(q) }.getOrNull()
            if (!titles.isNullOrEmpty()) {
                val matched = matchTitles(pool, titles)
                if (matched.isNotEmpty()) return@withContext Result(matched, "IA", true)
            }
            return@withContext Result(heuristic(pool, q), "IA indisponível — usando TMDB/playlist", false)
        }
        Result(heuristic(pool, q), "TMDB/playlist", false)
    }

    // ---- IA real (OpenAI-compatível) ----
    private fun llmTitles(q: String): List<String> {
        val base = Config.aiBase.ifBlank { "https://api.openai.com/v1" }.trimEnd('/')
        val model = Config.aiModel.ifBlank { "gpt-4o-mini" }
        val sys = "Você recomenda filmes e séries. Responda APENAS com um array JSON de até 20 títulos reais " +
            "(strings), sem numeração e sem comentários. Use os títulos mais conhecidos."
        val body = JSONObject()
            .put("model", model)
            .put("temperature", 0.6)
            .put("messages", JSONArray()
                .put(JSONObject().put("role", "system").put("content", sys))
                .put(JSONObject().put("role", "user").put("content", q.ifBlank { "Sugira bons filmes e séries variados." })))
        val conn = (URL("$base/chat/completions").openConnection() as HttpURLConnection).apply {
            requestMethod = "POST"; doOutput = true; connectTimeout = 15000; readTimeout = 30000
            setRequestProperty("Content-Type", "application/json")
            setRequestProperty("Authorization", "Bearer ${Config.aiKey}")
        }
        conn.outputStream.use { it.write(body.toString().toByteArray(Charsets.UTF_8)) }
        val code = conn.responseCode
        val txt = (if (code in 200..299) conn.inputStream else conn.errorStream)?.bufferedReader()?.use { it.readText() } ?: ""
        if (code !in 200..299) throw RuntimeException("AI HTTP $code")
        val content = JSONObject(txt).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content")
        return parseTitles(content)
    }

    private fun parseTitles(content: String): List<String> {
        val a = content.indexOf('['); val b = content.lastIndexOf(']')
        if (a in 0 until b) runCatching {
            val arr = JSONArray(content.substring(a, b + 1))
            return (0 until arr.length()).map { arr.optString(it) }.filter { it.isNotBlank() }
        }
        // sem JSON: uma por linha
        return content.split('\n').map { it.trim().trimStart('-', '*', '•', ' ').trim().trim('"', '.', ',') }
            .filter { it.length in 2..90 }
    }

    private fun matchTitles(pool: List<Item>, titles: List<String>): List<Item> {
        val byNorm = HashMap<String, Item>()
        for (it in pool) byNorm.putIfAbsent(Trending.norm(it.title), it)
        val out = LinkedHashSet<Item>()
        for (t in titles) {
            val n = Trending.norm(t)
            if (n.length < 2) continue
            val exact = byNorm[n]
            if (exact != null) { out.add(exact); continue }
            pool.firstOrNull { val pn = Trending.norm(it.title); pn.length >= 3 && (pn.contains(n) || n.contains(pn)) }?.let { out.add(it) }
        }
        return out.toList().take(36)
    }

    // ---- sem chave: TMDB "em alta" + termos/gênero na playlist ----
    private suspend fun heuristic(pool: List<Item>, q: String): List<Item> {
        val ql = q.lowercase()
        val wantsTrend = q.isBlank() || listOf("alta", "trend", "popular", "bombando", "famos", "novidade").any { ql.contains(it) }
        if (wantsTrend) {
            val tr = runCatching { Trending.titles() }.getOrDefault(emptySet())
            if (tr.isNotEmpty()) {
                val hit = pool.filter { Trending.norm(it.title) in tr }
                if (hit.isNotEmpty()) return hit.take(40)
            }
        }
        val toks = ql.split(' ', ',', '/').map { it.trim() }.filter { it.length >= 3 }
        if (toks.isEmpty()) return pool.shuffled().take(24)
        val scored = pool.mapNotNull { item ->
            val hay = (item.title + " " + item.group).lowercase()
            val s = toks.count { hay.contains(it) }
            if (s > 0) item to s else null
        }.sortedByDescending { it.second }.map { it.first }
        return scored.take(40).ifEmpty { pool.shuffled().take(24) }
    }
}
