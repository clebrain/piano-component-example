package piano

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import piano.key.drawKey
import piano.key.getKeyPosition
import piano.util.isDiatonic

data class KeyInfo(
    val midiKey: Int,
    var pressed: MutableState<Boolean>
)

suspend fun PointerInputScope.detectPressGestures(
    onPressStart: ((Offset) -> Unit)? = null,
    onPressEnd: (() -> Unit)? = null,
) = coroutineScope {
    awaitEachGesture {
        val firstDown = awaitFirstDown()
        firstDown.consume()
        onPressStart?.invoke(firstDown.position)

        do {
            val event = awaitPointerEvent()
            event.changes.forEach { changeEvent: PointerInputChange -> changeEvent.consume() }
        } while (event.changes.any { it.pressed })

        onPressEnd?.invoke()
    }
}

@Composable
fun Piano(
    modifier: Modifier = Modifier,
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

    BoxWithConstraints(modifier.aspectRatio(1920.0f / 234.0f)) {
        val density = LocalDensity.current
        val height = with(density) { maxHeight.toPx() }
        Canvas(Modifier.fillMaxSize().pointerInput(Unit) {
            detectPressGestures(
                onPressStart = { offset ->
                    keys.forEachIndexed { _, keyInfo ->
                        val diatonic = isDiatonic(keyInfo.midiKey)
                        val keyOffset = getKeyPosition(keyInfo).toFloat() - basis.toFloat()
                        val detectKeyArea = Rect(
                            Offset(keyOffset, 0f),
                            Offset(
                                keyOffset + 90f,
                                if (diatonic) height else height * 0.7f
                            )
                        )
                        if (detectKeyArea.contains(offset)) {
                            keyInfo.pressed.value = !keyInfo.pressed.value
                            return@detectPressGestures
                        }
                    }
                },
                onPressEnd = {
                    keys.forEachIndexed { _, keyInfo ->
                        keyInfo.pressed.value = false
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
                        maxHeight = height,
                        isPressed = keyInfo.pressed.value,
                        isLeftEnd = idx == 0,
                        isRightEnd = idx == keys.size - 1,
                    )
                }
            }
        }
    }
}
