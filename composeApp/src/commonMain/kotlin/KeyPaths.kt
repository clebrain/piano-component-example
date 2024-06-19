import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.vector.PathParser
import kotlin.math.atan2

fun whiteKeyCenterPath(
    width: Float = 90f,
    height: Float = 450f,
    middleRounded: Float = 3f,
    bottomRounded: Float = 3f,
    leftCutWidth: Float? = null,
    rightCutWidth: Float? = null,
    cutHeight: Float = 300f,
): Path {
    val parser = PathParser()
    parser.parsePathString(
        "M $bottomRounded $height a $bottomRounded $bottomRounded 0 0 1 -${bottomRounded} -${bottomRounded} ${
            if (leftCutWidth != null) {
                "V $cutHeight H ${leftCutWidth - middleRounded} a $middleRounded $middleRounded 0 0 0 $middleRounded -${middleRounded}"
            } else ""
        } V 0 ${if (rightCutWidth != null) "H ${width - rightCutWidth} V ${cutHeight - middleRounded} a $middleRounded $middleRounded 0 0 0 $middleRounded $middleRounded" else ""} H $width V ${height - bottomRounded} a $bottomRounded $bottomRounded 0 0 1 -${bottomRounded} $bottomRounded Z"
    )
    return parser.toPath()
}


fun whiteKeyLeftEdgePath(
    c: Float = 300f,
    cutHeight: Float = c,
    edgeWidth: Float = 4f,
    height: Float = 450f,
    r: Float = 3f,
    bottomRounded: Float = r,
): Path {
    val parser = PathParser()
    parser.parsePathString(
        "M $bottomRounded $height a $bottomRounded $bottomRounded 0 0 1 -${bottomRounded} -${bottomRounded} V $cutHeight H $edgeWidth V $height Z"
    )
    return parser.toPath()
}

fun whiteKeyRightEdgePath(
    c: Float = 300f,
    cutHeight: Float = c,
    edgeWidth: Float = 3f,
    width: Float = 90f,
    height: Float = 450f,
    r: Float = 3f,
    bottomRounded: Float = r,
): Path {
    val parser = PathParser()
    parser.parsePathString(
        "M ${width - edgeWidth} $height V $cutHeight h $edgeWidth V ${height - bottomRounded} a $bottomRounded $bottomRounded 0 0 1 -${bottomRounded} $bottomRounded Z"
    )
    return parser.toPath()
}
