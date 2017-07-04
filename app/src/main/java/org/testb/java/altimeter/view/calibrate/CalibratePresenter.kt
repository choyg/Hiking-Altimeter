package org.testb.java.altimeter.view.calibrate

import org.testb.java.altimeter.view.BasePresenter

interface CalibratePresenter : BasePresenter {
    fun setCalibration()
    fun clearCalibration()
    fun appendCalibration(char: Char)
    fun onLoad(altitude: String)
}