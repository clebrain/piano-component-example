import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import util.RoundOption
import util.isDiatonic
import util.midiKeyToDiatonicNumber
import kotlin.math.floor


val centerPositions = listOf(
    27.5, // C
    27.5, // C#
    45, // D
    28.5, // D#
    62.5, // E
    25.5, // F
    27.5, // F#
    38.5, // G
    27.5, // G#
    51.5, // A
    27.5, // A#
    64.5, // B
)


val whiteKeyInfo = listOf(
    Pair(0f, 35f), // C
    Pair(15f, 15f), // D
    Pair(35f, 0f), // E
    Pair(0f, 39f), // F
    Pair(13f, 26f), // G
    Pair(26f, 13f), // A
    Pair(39f, 0f), // B
)


fun DrawScope.drawPressedBlackKey(realHeight: Float) {
    drawPath(
        blackPressedBasePath(realHeight),
        brush = blackPressedBase()
    )
    clipPath(blackPressedBasePath(realHeight)) {
        drawPath(
            Path().apply {
                moveTo(5f, realHeight - 10f)
                lineTo(9f, realHeight - 27f)
                lineTo(46f, realHeight - 27f)
                lineTo(50f, realHeight - 10f)
                close()
            },
            brush = blackPressedBottom()
        )
    }
    drawPath(
        Path().apply {
            moveTo(14f, realHeight - 24f)
            relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -3f)
            relativeLineTo(0f, 17f - realHeight)
            relativeLineTo(37f, 0f)
            relativeLineTo(0f, realHeight - 17f)
            relativeCubicTo(0f, 2f, -2f, 3f, -5f, 3f)
            close()
        },
        brush = blackPressedTop()
    )

}

fun blackPressedBasePath(realHeight: Float): Path = Path().apply {
    moveTo(10f, realHeight - 10f)
    relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -4f)
    lineTo(10f, 0f)
    lineTo(50f, 0f)
    lineTo(50f, realHeight - 14f)
    relativeCubicTo(0f, 3f, -2f, 4f, -5f, 4f)
    close()
}

fun blackUnpressedBasePath(realHeight: Float): Path = Path().apply {
    moveTo(10f, realHeight - 5f)
    relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -4f)
    lineTo(10f, 0f)
    lineTo(50f, 0f)
    lineTo(50f, realHeight - 9f)
    relativeCubicTo(0f, 3f, -2f, 4f, -5f, 4f)
    close()
}

val pitchClassOffsets = listOf(
    0, // C
    55, // C#
    93, // D
    165, // D#
    186, // E
    279, // F
    330, // F#
    372, // G
    436, // G#
    465, // A
    542, // A#
    558, // B
)

val octaveWidth = 651;

fun DrawScope.drawUnpressedBlackKey(realHeight: Float) {
    drawPath(
        blackUnpressedBasePath(realHeight),
        brush = blackUnpressedBase()
    )
    clipPath(blackUnpressedBasePath(realHeight)) {
        drawPath(
            Path().apply {
                moveTo(5f, realHeight - 5f)
                lineTo(8f, realHeight - 35f)
                lineTo(47f, realHeight - 35f)
                lineTo(50f,  realHeight - 5f)
                close()
            },
            brush = blackUnpressedBottom()
        )
    }
    drawPath(
        Path().apply {
            moveTo(13f, realHeight - 32f) // 268
            relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -3f)
            relativeLineTo(0f, 3f - realHeight)
            relativeLineTo(39f, 0f)
            relativeLineTo(0f, realHeight - 3f)
            relativeCubicTo(0f, 2f, -2f, 3f, -5f, 3f)
            close()
        },
        brush = blackUnpressedTop()
    )
}

fun getKeyPosition(key: KeyInfo): Double {
    val pitchClass = key.midiKey % 12;
    val octave = floor(key.midiKey / 12.0) - 1;
    return octaveWidth * octave + pitchClassOffsets[pitchClass];
}

fun DrawScope.drawKey(
    midiKey: Int,
    maxHeight: Float,
    isPressed: Boolean,
    isLeftEnd: Boolean,
    isRightEnd: Boolean,
) {
    val diatonicClass = midiKeyToDiatonicNumber(midiKey, RoundOption.Floor) % 7
    val (leftCutWidth, rightCutWidth) = whiteKeyInfo[diatonicClass.toInt()]
    val realHeight = if (isPressed) maxHeight else maxHeight - 8;
    val cutHeight = (if (isPressed) maxHeight * 0.7 else maxHeight * 0.7 - 8).toFloat();
    val centerPath = whiteKeyCenterPath(
        width = 90f,
        height = realHeight,
        middleRounded = 3f,
        bottomRounded = 3f,
        leftCutWidth = if (isLeftEnd) 0f else leftCutWidth,
        rightCutWidth = if (isRightEnd) 0f else rightCutWidth,
        cutHeight
    )
    if (isDiatonic(midiKey)) {
        drawPath(
            centerPath,
            brush = if (isPressed) whiteCenterPressed() else whiteCenterUnpressed()
        )
        drawPath(
            whiteKeyLeftEdgePath(
                height = realHeight,
                cutHeight = if (!isLeftEnd) cutHeight else 0f,
                bottomRounded = 3f
            ), color = Color(0xffe9e9e9)
        )
        drawPath(
            whiteKeyRightEdgePath(
                height = realHeight,
                cutHeight = if (!isRightEnd) cutHeight else 0f,
                bottomRounded = 3f
            ), color = Color(0xfffafafa)
        )
    } else {
        if (isPressed) {
            drawPressedBlackKey(cutHeight)
        } else {
            drawUnpressedBlackKey(cutHeight)
        }

    }
}