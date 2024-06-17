package util

import models.octave
import kotlin.math.floor

fun isDiatonic(midiKey: Int): Boolean {
    return octave[midiKey % 12].length == 1
}


fun midiKeyToDiatonicNumber(
    midiKey: Int,
    roundOption: RoundOption
): Double {
    val midiKeyDiatonicPitchClassMap: MutableMap<Number, Number> = mutableMapOf()
    val diatonics = octave.map { k -> k.length == 1 }
    var diatonicPitchClass = 0f;
    for ((midiKeyPitchClass, diatonic) in diatonics.withIndex()) {
        if (diatonic) {
            midiKeyDiatonicPitchClassMap[midiKeyPitchClass] = diatonicPitchClass;
            diatonicPitchClass++;
        }
    }
    val realMidiKey = if (isDiatonic(midiKey)) midiKey else midiKey + (when (roundOption) {
        RoundOption.Floor -> -1
        else -> 1
    })
    val octave = floor(realMidiKey.toDouble() / 12) - 1;
    val pitchClass = midiKeyDiatonicPitchClassMap[realMidiKey % 12]
    return octave * 7 + (pitchClass?.toDouble() ?: 0.0);
}

enum class RoundOption {
    Floor,
    Ceil
}