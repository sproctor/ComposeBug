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
    setWindows: (List<String>) -> Unit
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
                        setWindows(
                            listOf("Test1", "Test2", "Test3")
                        )
                    }
                ) {
                    Text("Add windows")
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        var windows by remember { mutableStateOf(emptyList<String>()) }
        AppMenuBar(windows)
        App(
            setWindows = { windows = it },
        )
    }
}

@Composable
fun FrameWindowScope.AppMenuBar(windows: List<String>) {
    MenuBar {
        Menu("File") {
            Item("New window", onClick = { })
            Item(
                text = "Settings",
                onClick = {

                }
            )
            Separator()
            Item(
                text = "Exit",
                onClick = {

                },
            )
        }

        Menu(
            text = "Windows",
            enabled = windows.isNotEmpty(),
        ) {
            windows.sortedBy { it }.forEach { window ->
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