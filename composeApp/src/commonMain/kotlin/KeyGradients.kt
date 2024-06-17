import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color

fun blackUnpressedBase(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF3d3e40)),
            Pair(1f, Color(0xFF3f4042)),
            Pair(0f, Color(0xFF414244)),
            Pair(1f, Color(0xFF6f7374)),
        ), start = Offset(1f, 0.477f), end = Offset(-0.643f, 0.485f)
    )
}

fun blackUnpressedTop(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF5d5d5d)),
            Pair(0.044f, Color(0xFF272727)),
        ), start = Offset(0.5f, 0f), end = Offset(0.5f, 1f)
    )
}

fun blackUnpressedBottom(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF5a5b5d)),
            Pair(1f, Color(0xFF2f3032)),
        ), start = Offset(1f, 0.477f), end = Offset(-.063f, .485f)
    )
}
fun blackPressedBase(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF303031)),
            Pair(1f, Color(0xFF282829)),
            Pair(0f, Color(0xFF282829)),
            Pair(1f, Color(0xFF424445)),
        ), start = Offset(0.528f, -0.13f), end = Offset(-0.5f, 1f)
    )
}

fun blackPressedBottom(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF4c4d4f)),
            Pair(1f, Color(0xFF)),
        ), start = Offset(0.528f, -0.13f), end = Offset(-0.5f, 1f)
    )
}


fun blackPressedTop(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF434343)),
            Pair(0.044f, Color(0xFF1a1a1a)),
        ), start = Offset(0.5f, 0f), end = Offset(0.5f, 1f)
    )
}


fun whiteCenterUnpressed(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFFeaeaea)),
            Pair(1f, Color(0xFFf2f2f2)),
        ), start = Offset(1f, .161f), end = Offset(0.091f, 0.803f)
    )
}

fun whiteCenterPressed(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFFc5c4c9)),
            Pair(1f, Color(0xFFc8c7cc)),
        ), start = Offset(1f, .161f), end = Offset(0.091f, 0.803f)
    )
}
