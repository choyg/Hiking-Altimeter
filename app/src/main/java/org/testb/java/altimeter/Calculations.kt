package org.testb.java.altimeter

val SEA_TEMPERATURE = 288.15
val DEFAULT_SEA_PRESSURE = 1013.25

fun calculateAltitude(currentPressure: Double, seaLevelPressure: Double = DEFAULT_SEA_PRESSURE): Double {
    return ((SEA_TEMPERATURE - (SEA_TEMPERATURE / (Math.pow(seaLevelPressure / currentPressure, 0.19026324)))) / 0.0065)
}

fun calculateQNH(knownAltitude: Double, currentPressure: Double): Double {
    return currentPressure * Math.pow(SEA_TEMPERATURE / (SEA_TEMPERATURE - 0.0065 * knownAltitude), 5.25587611)
}

fun convertMetersToFeet(meters: Double): Double {
    return meters * 3.28084
}

fun convertFeetToMeters(feet: Double): Double {
    return feet / 3.28084
}
