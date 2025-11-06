import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun App() {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Compose bugs example") },
                )
            }
        ) {
            BoxWithConstraints(Modifier.fillMaxSize()) {
                val middle = maxWidth / 2.0f
                Box(Modifier.width(middle).height(50.dp).background(Color.LightGray))
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val ref = createRef()
                    Text(
                        modifier = Modifier.constrainAs(ref) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = middle)
                        },
                        text = "Middle: $middle"
                    )
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
