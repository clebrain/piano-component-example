import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CancellationException
import org.jetbrains.compose.ui.tooling.preview.Preview
import util.RoundOption
import util.isDiatonic
import util.midiKeyToDiatonicNumber

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


data class KeyInfo(
    val midiKey: Int,
    var pressed: MutableState<Boolean>
)

@Composable
fun Piano(
    range: IntRange,
    keyPressedList: List<Boolean>,
) {
    val length = range.last
    val keys by remember {
        mutableStateOf(
            if (keyPressedList.size != 128) {
                List(length) {
                    KeyInfo(midiKey = it + range.first, mutableStateOf(false))
                }
            } else {
                List(length) {
                    KeyInfo(
                        midiKey = it + range.first,
                        mutableStateOf(keyPressedList[it + range.first])
                    )
                }
            }
        )
    }
    val basis = getKeyPosition(keys.first());

    BoxWithConstraints(Modifier.height(450.dp).background(Color.Black)) {
        Canvas(Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures(
                onPress = { offset ->
                    keys.forEachIndexed { _, keyInfo ->
                        val diatonic = isDiatonic(keyInfo.midiKey)
                        val keyOffset = getKeyPosition(keyInfo).toFloat() - basis.toFloat()
                        val detectKeyArea = Rect(
                            Offset(keyOffset, 0f),
                            Offset(
                                keyOffset + 90f,
                                if (diatonic) maxHeight.value else maxHeight.value * 0.7f
                            )
                        )
                        if (detectKeyArea.contains(offset)) {
                            keyInfo.pressed.value = !keyInfo.pressed.value
                            return@detectTapGestures
                        }
                    }
                }
            )
        }) {
            keys.forEachIndexed { idx, keyInfo ->
                withTransform({
                    translate(left = getKeyPosition(keyInfo).toFloat() - basis.toFloat())
                }) {
                    drawKey(
                        midiKey = keyInfo.midiKey,
                        isPressed = keyInfo.pressed.value,
                        isLeftEnd = idx == 0,
                        isRightEnd = idx == keys.size - 1,
                        maxHeight = maxHeight.value
                    )
                }
            }
        }
    }
}


