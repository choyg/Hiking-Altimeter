package com.gchoy.altimeter

import java.text.DecimalFormat

/**
 *  @param time The length of an object in seconds
 */
fun stringifySeconds(time: Long): String {
    val seconds: Long = time % 60
    val minutes: Long = seconds / 60
    val hours: Long = minutes / 60
    val format = DecimalFormat("#")

    if (hours > 0) {
        return format.format(hours)
    } else {
        return format.format(minutes)
    }
}

fun getHours(time: Long): Int {
    val seconds: Long = time % 60
    val minutes: Long = seconds / 60
    val hours: Long = minutes / 60
    return hours.toInt()
}