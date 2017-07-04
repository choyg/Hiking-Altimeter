package org.testb.java.altimeter.view.calibrate

import org.testb.java.altimeter.Unit

interface CalibrateView {
    fun setCalibrationText(altitude: String, unit: Unit)
    fun save(altitude: String)
}