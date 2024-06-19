import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                range = Pair(21, 108),
                keypressList = BooleanArray(88).toList()
            )
        }
    }
}


data class KeyInfo(
    val midiKey: Int,
    val pressed: Boolean
)

@Composable
fun Piano(
    range: Pair<Int, Int>,
    keypressList: List<Boolean>,
) {
    val length = range.second - range.first
    val keys : List<KeyInfo> = if(keypressList.size != 128) {
        List(length) {
            KeyInfo(midiKey = it + range.first, false)
        }
    } else {
        List(length) {
            KeyInfo(midiKey = it + range.first, keypressList[it + range.first])
        }
    }
    Canvas(Modifier.fillMaxSize()) {
        keys.forEachIndexed { idx, keyInfo ->
            Keys(
                midiKey = keyInfo.midiKey,
                isPressed = keyInfo.pressed,
                isLeftEnd = idx == 0,
                isRightEnd = idx == keys.size,
                isHint = false
            )
        }
    }
}


