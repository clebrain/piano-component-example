import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
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
                keyPressedList = BooleanArray(88).toList()
            )
        }
    }
}


data class KeyInfo(
    val midiKey: Int,
    var pressed: Boolean
)

@Composable
fun Piano(
    range: Pair<Int, Int>,
    keyPressedList: List<Boolean>,
) {
    val length = range.second - range.first
    val keys: List<KeyInfo> = if (keyPressedList.size != 128) {
        List(length) {
            KeyInfo(midiKey = it + range.first, false)
        }
    } else {
        List(length) {
            KeyInfo(midiKey = it + range.first, keyPressedList[it + range.first])
        }
    }
    val basis = getKeyPosition(keys[0]);

    BoxWithConstraints(Modifier.height(450.dp)) {
        Canvas(Modifier.fillMaxSize()) {
            keys.forEachIndexed { idx, keyInfo ->
                withTransform({
                    translate(left = getKeyPosition(keyInfo).toFloat() - basis.toFloat())
                }) {
                    drawKey(
                        midiKey = keyInfo.midiKey,
                        isPressed = keyInfo.pressed,
                        isLeftEnd = idx == 0,
                        isRightEnd = idx == keys.size,
                        maxHeight = maxHeight.value
                    )
                }
            }
        }
    }
}


