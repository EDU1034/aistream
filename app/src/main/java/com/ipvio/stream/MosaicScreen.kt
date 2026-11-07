package com.ipvio.stream

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Canais escolhidos para o mosaico (definido na aba de TV ao vivo). */
object MosaicContext {
    var list: List<Item> = emptyList()
}

/**
 * Mosaico multiview: vários canais ao vivo tocando ao mesmo tempo numa grade.
 * Tamanhos variados: 4 (2×2), 6 (3×2) e 9 (3×3). O canal em foco fica com o áudio e com a borda.
 * Cada célula usa o player leve do segundo stream (resolução baixa), para pesar o mínimo possível.
 */
@Composable
fun MosaicScreen(channels: List<Item>, onOpen: (Item) -> Unit, onClose: () -> Unit) {
    if (channels.isEmpty()) { LaunchedEffect(Unit) { onClose() }; return }
    // (colunas, linhas) -> 4, 6, 9 células
    val grids = listOf(2 to 2, 3 to 2, 3 to 3)
    val counts = listOf(4, 6, 9)
    var gi by remember { mutableIntStateOf(0) }
    var page by remember { mutableIntStateOf(0) }
    var focus by remember { mutableIntStateOf(0) }

    val cols = grids[gi].first
    val rows = grids[gi].second
    val per = cols * rows
    val pages = ((channels.size + per - 1) / per).coerceAtLeast(1)
    val pageC = page.coerceIn(0, pages - 1)
    val cells = channels.drop(pageC * per).take(per)
    val focusC = focus.coerceIn(0, (cells.size - 1).coerceAtLeast(0))

    val root = remember { FocusRequester() }
    LaunchedEffect(Unit) { runCatching { root.requestFocus() } }

    Box(
        Modifier.fillMaxSize().background(Color.Black)
            .focusRequester(root).focusable()
            .onPreviewKeyEvent { e ->
                if (e.type != KeyEventType.KeyDown) return@onPreviewKeyEvent false
                when (e.key) {
                    Key.DirectionRight -> {
                        if (focusC % cols < cols - 1 && focusC + 1 < cells.size) focus = focusC + 1
                        else if (pageC < pages - 1) { page = pageC + 1; focus = 0 }
                        true
                    }
                    Key.DirectionLeft -> {
                        if (focusC % cols > 0) focus = focusC - 1
                        else if (pageC > 0) { page = pageC - 1; focus = 0 }
                        true
                    }
                    Key.DirectionDown -> { if (focusC + cols < cells.size) focus = focusC + cols; true }
                    Key.DirectionUp -> { if (focusC - cols >= 0) focus = focusC - cols; true }
                    Key.DirectionCenter, Key.Enter, Key.NumPadEnter -> { cells.getOrNull(focusC)?.let(onOpen); true }
                    Key.Back -> { onClose(); true }
                    Key.Menu -> { gi = (gi + 1) % grids.size; focus = 0; page = 0; true }
                    Key.ChannelUp -> { if (pageC < pages - 1) { page = pageC + 1; focus = 0 }; true }
                    Key.ChannelDown -> { if (pageC > 0) { page = pageC - 1; focus = 0 }; true }
                    else -> false
                }
            },
    ) {
        Column(Modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            for (r in 0 until rows) {
                Row(Modifier.weight(1f).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    for (cc in 0 until cols) {
                        val idx = r * cols + cc
                        val item = cells.getOrNull(idx)
                        Box(Modifier.weight(1f).fillMaxHeight()) {
                            if (item != null) {
                                SecondStream(item, split = false, audioOn = idx == focusC, modifier = Modifier.fillMaxSize())
                                if (idx == focusC) Box(Modifier.fillMaxSize().border(3.dp, Accent2, RoundedCornerShape(10.dp)))
                            } else {
                                Box(Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp)).background(Color(0xFF0E0E0E)))
                            }
                        }
                    }
                }
            }
        }
        // dica no topo
        Box(
            Modifier.align(Alignment.TopCenter).padding(top = 8.dp)
                .clip(RoundedCornerShape(10.dp)).background(Color(0xAA000000)).padding(horizontal = 14.dp, vertical = 5.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = "Mosaik", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(raw = true, text = "${counts[gi]}" + (if (pages > 1) " · ${pageC + 1}/$pages" else ""), color = Color(0xFFBBBBBB), fontSize = 14.sp)
                Text(text = "Größe: Menü · Vollbild: OK · Zurück: schließen", color = Color(0xFFBBBBBB), fontSize = 13.sp)
            }
        }
    }
}
