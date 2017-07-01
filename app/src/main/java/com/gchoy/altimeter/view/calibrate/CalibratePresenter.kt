package com.gchoy.altimeter.view.calibrate

import com.gchoy.altimeter.view.BasePresenter

interface CalibratePresenter : BasePresenter {
    fun setCalibration()
    fun clearCalibration()
    fun appendCalibration(char: Char)
}