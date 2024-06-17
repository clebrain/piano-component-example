import androidx.compose.ui.graphics.Path

fun blackPress(): Path {
    val path = Path()
    path.apply {
        addPath(
            Path().apply {
                moveTo(10f, 290f)
                cubicTo(-3f, 0f, -5f, -1f, -5f, -4f)
                moveTo(10f, 0f)
                moveTo(50f, 0f)
                moveTo(50f, 286f)
                cubicTo(0f, 3f, -2f, 4f, -5f, -4f)
                close()
            }
        )
        addPath(
            Path().apply {
                moveTo(5f, 290f)
                lineTo(0f, 273f)
                moveTo(46f, 290f)
                lineTo(50f, 290f)
                close()
            }
        )
        addPath(
            Path().apply {
                moveTo(14f, 276f)
                cubicTo(-3f, 0f, -5f, -1f, -5f, -3f)
                moveTo(37f, 276f)
                moveTo(37f, 273f)
                cubicTo(0f, 2f, -2f, 3f, -5f, 3f)
                close()
            }
        )
    }
    return path
}