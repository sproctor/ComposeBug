import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun main() = application {
    val showExtraState = remember { MutableStateFlow(true) }
    val showExtraFlow: Flow<Boolean> = showExtraState
    MaterialTheme {
        Window(onCloseRequest = ::exitApplication) {
            MyMenu(showExtra = showExtraFlow.collectAsState(false).value, exit = ::exitApplication)
            Box(Modifier.fillMaxSize()) {
                val size = calculateWindowSizeClass()
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(text = "Hello, ${size.widthSizeClass}")
                    Button(
                        onClick = {
                            showExtraState.value = !showExtraState.value
                        }
                    ) {
                        Text("Toggle menu extra")
                    }
                }
            }
        }
    }
}

@Composable
fun FrameWindowScope.MyMenu(showExtra: Boolean, exit: () -> Unit) {
    MenuBar {
        Menu("File") {
            Item("New ...", onClick = { })
            Item(
                text = "Settings",
                onClick = {}
            )
            Separator()
            Item(
                text = "Exit",
                enabled = showExtra,
                onClick = {
                    exit()
                }
            )
        }

        Menu(
            text = "Windows",
            enabled = showExtra,
        ) {
            windows.forEach { window ->
                CheckboxItem(
                    text = window,
                    checked = false,
                    onCheckedChange = {
                    }
                )
            }
        }
        Menu("Help") {
            Item("Updates") {
            }
            Item("About") {
            }
        }
    }
}

val windows = listOf("test", "example", "foo", "bar")