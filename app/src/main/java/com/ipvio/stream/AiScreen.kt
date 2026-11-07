package com.ipvio.stream

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * Modo IA: recomenda o que assistir na SUA playlist. Com chave de IA (Ajustes > API) usa um modelo de linguagem;
 * sem chave, usa o TMDB e a própria playlist. Toque numa sugestão ou escreva o que quer ("filme de ação", "comédia leve", "em alta").
 */
@Composable
fun AiScreen(all: List<Item>, onOpen: (Item) -> Unit, onClose: () -> Unit) {
    val scope = rememberCoroutineScope()
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<Item>>(emptyList()) }
    var note by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }

    fun ask(q: String) {
        if (busy) return
        query = q
        busy = true
        scope.launch {
            val r = AiRepo.recommend(all, q)
            results = r.items
            note = (if (r.usedAi) Lang.tr("Sugestões da IA") else Lang.tr("Sugestões (TMDB/playlist)")) +
                (if (r.items.isEmpty()) " · " + Lang.tr("Nada encontrado na sua playlist") else "")
            busy = false
        }
    }

    // mostra sugestões "em alta" ao abrir
    LaunchedEffect(Unit) { ask("") }

    val chips = listOf(
        "Em alta" to "em alta",
        "Ação" to "filme de ação",
        "Comédia" to "comédia",
        "Terror" to "terror",
        "Família" to "filme para a família",
        "Romance" to "romance",
        "Suspense" to "suspense thriller",
        "Documentário" to "documentário",
    )

    Box(
        Modifier.fillMaxSize().background(Bg).padding(28.dp)
            .onPreviewKeyEvent { e -> if (e.type == KeyEventType.KeyDown && e.key == Key.Back) { onClose(); true } else false },
    ) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("IA", color = Tok.TextHi, fontSize = 34.sp, fontWeight = FontWeight.ExtraBold)
                Text(if (Config.aiEnabled()) "· " + Lang.tr("com sua chave") else "· TMDB", color = Muted, fontSize = 16.sp)
            }
            Text(Lang.tr("O que você quer assistir? Escolha uma sugestão ou escreva abaixo."), color = Tok.TextMid, fontSize = 17.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Field(query, { query = it }, Lang.tr("Ex.: comédia leve, em alta, ação dos anos 90…"), modifier = Modifier.width(560.dp), onDone = { ask(query) })
                PillButton(if (busy) Lang.tr("Pensando…") else Lang.tr("Perguntar"), light = true) { ask(query) }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                chips.forEach { (label, q) -> SmallPill(Lang.tr(label)) { ask(q) } }
            }
            if (note.isNotBlank()) Text(raw = true, text = note, color = Muted, fontSize = 14.sp)
            LazyVerticalGrid(columns = GridCells.Adaptive(128.dp), modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(results, key = { it.id }) { item ->
                    PosterCard(item, { onOpen(item) }, Modifier.fillMaxWidth(), fixedPosterHeight = 180.dp)
                }
            }
        }
    }
}
