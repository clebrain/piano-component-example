import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalHapticFeedback
import org.jetbrains.compose.ui.tooling.preview.Preview
import piano.Piano

@Composable
@Preview
fun App() {
    val haptic = LocalHapticFeedback.current
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Box(Modifier.clickable { haptic.performHapticFeedback(HapticFeedbackType.LongPress) }) {
//                Text("Click me!")
//            }
//        }
        Column(Modifier.fillMaxWidth()) {
            Piano(
                range = 21 until 108,
                BooleanArray(88).toMutableList()
            )
        }
    }
}




