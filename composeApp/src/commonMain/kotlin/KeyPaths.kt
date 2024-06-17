import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

fun whiteKeyCenterPath(
    width: Float = 90f,
    height: Float = 450f,
    middleRounded: Float = 3f,
    bottomRounded: Float = 3f,
    leftCutWidth: Float? = null,
    rightCutWidth: Float? = null,
    cutHeight: Float = 300f,
): Path = Path().apply {
        moveTo(bottomRounded, height)
        // todo: add arcTo
        if (leftCutWidth != null) {
            lineTo(bottomRounded, cutHeight)
            lineTo(leftCutWidth - middleRounded, cutHeight)
            // todo: add arcTo
        }
        if (rightCutWidth != null) {
            lineTo(width - rightCutWidth, cutHeight)
            lineTo(width - rightCutWidth, cutHeight - middleRounded)
            // todo: add arcTo
        }
        lineTo(width, cutHeight - middleRounded)
        lineTo(width, height - bottomRounded)
        // todo: add arcTo
        close()
    }


fun whiteKeyLeftEdgePath(
    c: Float = 300f,
    cutHeight: Float = c,
    edgeWidth: Float = 4f,
    height: Float = 450f,
    r: Float = 3f,
    bottomRounded: Float = r,
): Path = Path().apply {
    moveTo(bottomRounded, height)
    // todo: add arcTo
    lineTo(bottomRounded, cutHeight)
    lineTo(edgeWidth, cutHeight)
    lineTo(edgeWidth, height)
    close()
}
fun whiteKeyRightEdgePath(
    c: Float = 300f,
    cutHeight: Float = c,
    edgeWidth: Float = 3f,
    width: Float = 90f,
    height: Float = 450f,
    r: Float = 3f,
    bottomRounded: Float = r,
): Path = Path().apply {
    moveTo(width - edgeWidth, height)
    lineTo(width - edgeWidth, cutHeight)
    lineTo(edgeWidth, cutHeight)
    lineTo(edgeWidth,  height - bottomRounded)
    // todo: add arcTo
    close()
}