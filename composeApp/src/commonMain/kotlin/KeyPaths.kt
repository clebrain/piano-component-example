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
    arcTo(Rect(-bottomRounded, -bottomRounded, bottomRounded, bottomRounded), 0f, 90f, true)
    if (leftCutWidth != null) {
        lineTo(bottomRounded, cutHeight)
        lineTo(leftCutWidth - middleRounded, cutHeight)
        arcTo(Rect(middleRounded, -middleRounded, middleRounded, middleRounded), 0f, 90f, false)
    }
    lineTo(if(leftCutWidth != null) leftCutWidth - middleRounded else bottomRounded, 0f)
    if (rightCutWidth != null) {
        lineTo(width - rightCutWidth, 0f)
        lineTo(width - rightCutWidth, cutHeight - middleRounded)
        arcTo(Rect(middleRounded, middleRounded, middleRounded, middleRounded), 0f, 90f, false)
    }
    lineTo(width, if(rightCutWidth != null) cutHeight - middleRounded else 0f)
    lineTo(width, height - bottomRounded)
    arcTo(Rect(-bottomRounded, bottomRounded, bottomRounded, bottomRounded), 0f, 90f, true)
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
    arcTo(Rect(-bottomRounded, -bottomRounded, bottomRounded, bottomRounded), 0f, 90f, true)
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
    relativeLineTo(edgeWidth, 0f)
    lineTo(edgeWidth, height - bottomRounded)
    arcTo(Rect(-bottomRounded, bottomRounded, bottomRounded, bottomRounded), 0f, 90f, true)
    close()
}