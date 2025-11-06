import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun App(
    enabled: Boolean,
    setEnabled: (Boolean) -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Compose bugs example") },
                )
            }
        ) {
            Box(Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                        setEnabled(!enabled)
                    }
                ) {
                    Text(if(enabled) "Disable" else "Enable")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        var enabled by remember { mutableStateOf(false) }
        AppMenuBar(enabled)
        App(
            enabled,
            setEnabled = { enabled = it }
        )
    }
}

@Composable
fun FrameWindowScope.AppMenuBar(enabled: Boolean) {
    MenuBar {
        Menu(
            text = "Menu",
            enabled = enabled,
        ) {
            listOf("Test1", "Test2", "Test3").forEach { window ->
                Item(
                    text = window,
                    onClick = {}
                )
            }
        }
    }
}
