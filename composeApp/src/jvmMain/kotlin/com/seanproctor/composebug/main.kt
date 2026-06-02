package com.seanproctor.composebug

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.round
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.singleWindowApplication
import org.jetbrains.jewel.intui.standalone.theme.IntUiTheme
import org.jetbrains.jewel.ui.component.PopupMenu
import org.jetbrains.jewel.ui.component.Text

@OptIn(ExperimentalComposeUiApi::class)
fun main() = singleWindowApplication {
    IntUiTheme {
        var offset by remember { mutableStateOf<IntOffset?>(null) }
        Box(Modifier.fillMaxSize().onPointerEvent(PointerEventType.Press) {
            offset = it.changes.first().position.round()
        }) {
            Text("Click anywhere", modifier = Modifier.align(Alignment.Center))
        }
        offset?.let { anchor ->
            PopupMenu(
                onDismissRequest = { offset = null; true },
                popupPositionProvider = remember(anchor) {
                    object : PopupPositionProvider {
                        override fun calculatePosition(
                            anchorBounds: IntRect,
                            windowSize: IntSize,
                            layoutDirection: LayoutDirection,
                            popupContentSize: IntSize,
                        ): IntOffset = anchor
                    }
                },
            ) {
                selectableItem(selected = false, onClick = {}) { Text("Item A") }
                selectableItem(selected = false, onClick = {}) { Text("Item B") }
                submenu(submenu = {
                    selectableItem(selected = false, onClick = {}) { Text("Sub 1") }
                    selectableItem(selected = false, onClick = {}) { Text("Sub 2") }
                }) { Text("More") }
            }
        }
    }
}
