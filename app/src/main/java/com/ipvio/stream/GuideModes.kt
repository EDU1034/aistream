package com.ipvio.stream

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

/** Modos alternativos do guia (além da grade/timeline): 1 = Agora/A seguir, 2 = Dia do canal. */
@Composable
fun GuideAltPane(
    mode: Int,
    channels: List<Item>,
    curId: String,
    nowSec: Long,
    startFocus: FocusRequester,
    onPick: (Item) -> Unit,
    onArchive: (Item, EpgEntry) -> Unit,
    onBackToCats: () -> Unit,
    modifier: Modifier,
) {
    if (mode == 2) GuideChannelDay(channels, curId, nowSec, startFocus, onPick, onArchive, onBackToCats, modifier)
    else GuideNowNext(channels, curId, nowSec, startFocus, onPick, onBackToCats, modifier)
}

/** Lista simples por canal: o que passa agora e o que vem a seguir. */
@Composable
private fun GuideNowNext(
    channels: List<Item>, curId: String, nowSec: Long, startFocus: FocusRequester,
    onPick: (Item) -> Unit, onBackToCats: () -> Unit, modifier: Modifier,
) {
    val startIdx = channels.indexOfFirst { it.id == curId }.coerceAtLeast(0)
    val ls = rememberLazyListState(maxOf(startIdx - 1, 0))
    LazyColumn(
        modifier.clip(RoundedCornerShape(DS.RadiusL)).background(Color(0x0AFFFFFF)).padding(8.dp),
        state = ls, verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(channels, key = { _, ch -> ch.id }) { i, ch ->
            val epg by produceState(emptyList<EpgEntry>(), ch.id) { value = runCatching { EpgFull.get(ch) }.getOrDefault(emptyList()) }
            val now = Epg.current(epg)
            val next = if (now != null) epg.firstOrNull { it.start >= now.end } else epg.firstOrNull { it.end > nowSec }
            var focused by remember { mutableStateOf(false) }
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp))
                    .background(if (focused) Color(0x26FFFFFF) else Color(0x0CFFFFFF))
                    .then(if (i == startIdx) Modifier.focusRequester(startFocus) else Modifier)
                    .onFocusChanged { focused = it.isFocused }.focusable()
                    .onPreviewKeyEvent { e ->
                        if (e.type != KeyEventType.KeyDown) false else when (e.key) {
                            Key.DirectionCenter, Key.Enter, Key.NumPadEnter -> { onPick(ch); true }
                            Key.DirectionLeft -> { onBackToCats(); true }
                            else -> false
                        }
                    }
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (ch.logo != null) AsyncImage(ch.logo, null, Modifier.size(width = 58.dp, height = 34.dp), contentScale = ContentScale.Fit)
                Column(Modifier.width(190.dp)) {
                    Text(raw = true, text = ch.title, color = Tok.TextHi, fontSize = 16.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    if (ch.id == curId) Text("Läuft gerade", color = Accent2, fontSize = 12.sp)
                }
                Column(Modifier.weight(1f)) {
                    Text(raw = true, text = now?.let { hhmm(it.start) + "   " + it.title } ?: Lang.tr("Kein Programm verfügbar"),
                        color = Tok.TextHi, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    if (next != null) Text(raw = true, text = Lang.tr("Danach") + " · " + hhmm(next.start) + "   " + next.title,
                        color = Muted, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}

/** Dois painéis: lista de canais à esquerda e a programação do dia do canal em foco à direita. */
@Composable
private fun GuideChannelDay(
    channels: List<Item>, curId: String, nowSec: Long, startFocus: FocusRequester,
    onPick: (Item) -> Unit, onArchive: (Item, EpgEntry) -> Unit, onBackToCats: () -> Unit, modifier: Modifier,
) {
    val startIdx = channels.indexOfFirst { it.id == curId }.coerceAtLeast(0)
    var sel by remember { mutableIntStateOf(startIdx) }
    val dayFocus = remember { FocusRequester() }
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        // canais
        val cls = rememberLazyListState(maxOf(startIdx - 1, 0))
        LazyColumn(
            Modifier.width(230.dp).fillMaxHeight().clip(RoundedCornerShape(DS.RadiusL)).background(Color(0x0AFFFFFF)).padding(8.dp),
            state = cls, verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            itemsIndexed(channels, key = { _, ch -> ch.id }) { i, ch ->
                var focused by remember { mutableStateOf(false) }
                Row(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(9.dp))
                        .background(if (focused) Color(0x26FFFFFF) else if (i == sel) Color(0x14FFFFFF) else Color.Transparent)
                        .then(if (i == startIdx) Modifier.focusRequester(startFocus) else Modifier)
                        .onFocusChanged { if (it.isFocused) sel = i; focused = it.isFocused }.focusable()
                        .onPreviewKeyEvent { e ->
                            if (e.type != KeyEventType.KeyDown) false else when (e.key) {
                                Key.DirectionCenter, Key.Enter, Key.NumPadEnter -> { onPick(ch); true }
                                Key.DirectionRight -> { runCatching { dayFocus.requestFocus() }; true }
                                Key.DirectionLeft -> { onBackToCats(); true }
                                else -> false
                            }
                        }
                        .padding(horizontal = 10.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp),
                ) {
                    if (ch.logo != null) AsyncImage(ch.logo, null, Modifier.size(width = 46.dp, height = 28.dp), contentScale = ContentScale.Fit)
                    Text(raw = true, text = ch.title, color = if (i == sel) Tok.TextHi else Tok.TextMid, fontSize = 14.sp,
                        fontWeight = if (i == sel) FontWeight.Bold else FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
        }
        // dia do canal selecionado
        val ch = channels.getOrNull(sel)
        val day by produceState(emptyList<EpgEntry>(), ch?.id) { value = ch?.let { runCatching { EpgFull.get(it) }.getOrDefault(emptyList()) } ?: emptyList() }
        val nowIdx = day.indexOfLast { it.start <= nowSec && it.end > nowSec }
        val dls = rememberLazyListState(maxOf(nowIdx - 1, 0))
        LazyColumn(
            Modifier.weight(1f).fillMaxHeight().clip(RoundedCornerShape(DS.RadiusL)).background(Color(0x0AFFFFFF)).padding(8.dp),
            state = dls, verticalArrangement = Arrangement.spacedBy(3.dp),
        ) {
            if (day.isEmpty()) item { Text(Lang.tr("Kein Programm verfügbar"), color = Muted, fontSize = 15.sp, modifier = Modifier.padding(12.dp)) }
            itemsIndexed(day, key = { _, e -> e.start }) { i, e ->
                val live = e.start <= nowSec && e.end > nowSec
                val past = e.end <= nowSec
                var focused by remember { mutableStateOf(false) }
                Row(
                    Modifier.fillMaxWidth().clip(RoundedCornerShape(9.dp))
                        .background(if (focused) Color(0x26FFFFFF) else if (live) Color(0x1F4FC3F7) else Color.Transparent)
                        .then(if (i == maxOf(nowIdx, 0)) Modifier.focusRequester(dayFocus) else Modifier)
                        .onFocusChanged { focused = it.isFocused }.focusable()
                        .onPreviewKeyEvent { ev ->
                            if (ev.type != KeyEventType.KeyDown) false else when (ev.key) {
                                Key.DirectionCenter, Key.Enter, Key.NumPadEnter -> {
                                    val c = ch
                                    if (c != null) { if (past && Catchup.of(c) != null) onArchive(c, e) else onPick(c) }
                                    true
                                }
                                Key.DirectionLeft -> { runCatching { startFocus.requestFocus() }; true }
                                else -> false
                            }
                        }
                        .padding(horizontal = 12.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(raw = true, text = hhmm(e.start), color = if (past) Muted else Tok.TextMid, fontSize = 13.sp,
                        fontWeight = FontWeight.Bold, modifier = Modifier.width(52.dp))
                    Text(raw = true, text = e.title, color = if (past) Color(0x80FFFFFF) else Tok.TextHi, fontSize = 15.sp,
                        fontWeight = if (live) FontWeight.Bold else FontWeight.Normal, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                    if (live) Box(Modifier.clip(RoundedCornerShape(6.dp)).background(Color(0xFFE11D48)).padding(horizontal = 7.dp, vertical = 1.dp)) {
                        Text("LIVE", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
        }
    }
}
