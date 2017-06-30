package com.gchoy.altimeter.view.altimeter

import com.gchoy.altimeter.service.Calibration
import com.gchoy.altimeter.view.BasePresenter

interface AltimeterFragmentPresenter : BasePresenter {
    fun resetCalibration(calibration: Calibration)
    fun undoResetCalibration(): Calibration?
}