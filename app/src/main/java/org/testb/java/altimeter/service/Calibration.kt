package org.testb.java.altimeter.service

/**
 * @param date Calibration timestamp in milliseconds
 * @param qnh Adjusted sea level pressure in hectopascals (HPA)
 */
data class Calibration(val date: Long, val qnh: Double)