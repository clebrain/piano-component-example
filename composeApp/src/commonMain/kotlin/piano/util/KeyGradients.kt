package piano.util

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color

fun blackUnpressedBase(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF3D3E40)),
            Pair(.044f, Color(0xFF3F4042)),
            Pair(.084f, Color(0xFF414244)),
            Pair(1f, Color(0xFF6F7374)),
        ), start = Offset(1f, 0.477f), end = Offset(-0.63f, 0.485f)
    )
}

fun blackUnpressedTop(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF5D5B5D)),
            Pair(1f, Color(0xFF272727)),
        ), start = Offset(0.5f, 0f), end = Offset(0.5f, 1f)
    )
}

fun blackUnpressedBottom(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF5A5B5D)),
            Pair(1f, Color(0xFF2F3032)),
        ), start = Offset(0.5f, 0.0f), end = Offset(0.5f, 1f)
    )
}
fun blackPressedBase(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF303031)),
            Pair(.044f, Color(0xFF282829)),
            Pair(.084f, Color(0xFF282829)),
            Pair(1f, Color(0xFF424445)),
        ), start = Offset(1f, .477f), end = Offset(-0.063f, .485f)
    )
}

fun blackPressedBottom(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF4C4D4F)),
            Pair(1f, Color(0xFF)),
        ), start = Offset(0.528f, -0.13f), end = Offset(-0.5f, 1f)
    )
}


fun blackPressedTop(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFF434343)),
            Pair(0.044f, Color(0xFF1A1A1A)),
        ), start = Offset(0.5f, 0f), end = Offset(0.5f, 1f)
    )
}


fun whiteCenterUnpressed(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFFEAEAEA)),
            Pair(1f, Color(0xFFf2f2f2)),
        ), start = Offset(1f, .161f), end = Offset(0.091f, 0.803f)
    )
}

fun whiteCenterPressed(): Brush {
    return linearGradient(
        colorStops = arrayOf(
            Pair(0f, Color(0xFFC5C4C9)),
            Pair(1f, Color(0xFFC8C7CC)),
        ), start = Offset(1f, .161f), end = Offset(0.091f, 0.803f)
    )
}
