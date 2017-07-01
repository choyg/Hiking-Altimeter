package com.gchoy.altimeter.view.calibrate

import com.gchoy.altimeter.Unit

interface CalibrateView {
    fun setCalibrationText(altitude: String, unit: Unit)
}