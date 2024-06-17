import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.withTransform
import util.RoundOption
import util.isDiatonic
import util.midiKeyToDiatonicNumber


val DEFAULT_HINT_COLOR = Color(0xFFffce00)

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


fun DrawScope.drawPressedBlackKey() {
    withTransform({
        this.scale(2f, 2f)
        this.translate(250f, 500f)
    }) {
        drawPath(
            blackPressedBasePath,
            brush = blackPressedBase()
        )
        clipPath(blackPressedBasePath) {
            drawPath(
                Path().apply {
                    moveTo(5f, 290f)
                    lineTo(9f, 273f)
                    lineTo(46f, 273f)
                    lineTo(50f, 290f)
                    close()
                },
                brush = blackPressedBottom()
            )
        }
        drawPath(
            Path().apply {
                moveTo(14f, 276f)
                relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -3f)
                relativeLineTo(0f, -273f)
                relativeLineTo(37f, 0f)
                relativeLineTo(0f, 273f)
                relativeCubicTo(0f, 2f, -2f, 3f, -5f, 3f)
                close()
            },
            brush = blackPressedTop()
        )
    }

}

val blackPressedBasePath: Path = Path().apply {
    moveTo(10f, 290f)
    relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -4f)
    lineTo(10f, 0f)
    lineTo(50f, 0f)
    lineTo(50f, 286f)
    relativeCubicTo(0f, 3f, -2f, 4f, -5f, 4f)
    close()
}

val blackUnpressedBasePath: Path = Path().apply {
    moveTo(10f, 295f)
    relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -4f)
    lineTo(10f, 0f)
    lineTo(50f, 0f)
    lineTo(50f, 291f)
    relativeCubicTo(0f, 3f, -2f, 4f, -5f, 4f)
    close()
}

fun DrawScope.drawUnpressedBlackKey() {
    withTransform({
        this.scale(2f, 2f)
        this.translate(500f, 500f)
    }) {
        drawPath(
            blackUnpressedBasePath,
            brush = blackUnpressedBase()
        )
        clipPath(blackUnpressedBasePath) {
            drawPath(
                Path().apply {
                    moveTo(5f, 295f)
                    lineTo(8f, 265f)
                    lineTo(47f, 265f)
                    lineTo(50f, 295f)
                    close()
                },
                brush = blackUnpressedBottom()
            )
        }
        drawPath(
            Path().apply {
                moveTo(13f, 268f)
                relativeCubicTo(-3f, 0f, -5f, -1f, -5f, -3f)
                relativeLineTo(0f, -265f)
                relativeLineTo(39f, 0f)
                relativeLineTo(0f, 265f)
                relativeCubicTo(0f, 2f, -2f, 3f, -5f, 3f)
                close()
            },
            brush = blackUnpressedTop()
        )
    }
}

@Composable
fun DrawScope.Keys(
    midiKey: Int,
    isPressed: Boolean,
    isHint: Boolean,
    isLeftEnd: Boolean,
    isRightEnd: Boolean,
    translate: String,
    hintColor: Color = DEFAULT_HINT_COLOR,
) {
    val centerPosition = centerPositions[midiKey % 12]
    if (isDiatonic(midiKey)) {
        val diatoicClass = midiKeyToDiatonicNumber(midiKey, RoundOption.Floor) % 7
        val (leftCutWidth, rightCutWidth) = whiteKeyInfo[diatoicClass.toInt()]
        val realHeight = if (isPressed) 458f else 450f;
        val cutHeight = if (isPressed) 308f else 300f;
        val centerPath = whiteKeyCenterPath(
            width = 90f,
            height = realHeight,
            middleRounded = 3f,
            bottomRounded = 3f,
            leftCutWidth = if (isLeftEnd) 0f else leftCutWidth,
            rightCutWidth = if (isRightEnd) 0f else rightCutWidth,
            cutHeight
        )

        drawPath(
            centerPath,
            brush = if (isPressed) whiteCenterPressed() else whiteCenterUnpressed()
        )
        drawPath(
            whiteKeyLeftEdgePath(
                height = realHeight,
                cutHeight = if (!isLeftEnd) cutHeight else 0f,
                bottomRounded = 3f
            ), color = Color(0xfffafafa)
        )
        drawPath(
            whiteKeyLeftEdgePath(
                height = realHeight,
                cutHeight = if (!isRightEnd) cutHeight else 0f,
                bottomRounded = 3f
            ), color = Color(0xfffafafa)
        )
        if (isHint) {
            drawPath(
                centerPath,
                color = hintColor.copy(alpha = 0.7f),
            )
        }
    } else {
        if (isPressed) {
            drawPressedBlackKey()
        } else {
            drawUnpressedBlackKey()
        }
        if (isHint) {
            drawPath(
                if (isPressed) Path().apply {
                    moveTo(44.638f, 289.253f)
                    lineTo(10.362f, 289.253f)
                    relativeCubicTo(-3.047f, 0f, -4.891f, -.879f, -4.891f, -2.841f)
                    lineTo(4.83f, 0f)
                    relativeLineTo(45.34f, 0f)
                    lineTo(48.84f, 285.682f)
                    relativeCubicTo(0f, 1.962f, -1.112f, 3.571f, -4.159f, 3.571f)
                    close()
                } else Path().apply {
                    moveTo(44.638f, 294.858f)
                    lineTo(10.362f, 294.858f)
                    relativeCubicTo(-3.047f, 0f, -5.532f, -1.64f, -5.532f, -3.64f)
                    lineTo(10.362f, 0f)
                    relativeLineTo(45.34f, 0f)
                    relativeLineTo(0f, 291.218f)
                    relativeCubicTo(0f, 2f, -2.485f, 3.65f, -5.532f, 3.64f)
                    close()
                },
                color = hintColor.copy(alpha = 0.7f),
            )
        }
    }
}