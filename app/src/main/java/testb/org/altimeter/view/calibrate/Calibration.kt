package testb.org.altimeter.view.calibrate

/**
 * @param date Calibration timestamp in milliseconds
 * @param qnh Adjusted sea level pressure in hectopascals (HPA)
 */
data class Calibration(val date: Long, val qnh: Double)