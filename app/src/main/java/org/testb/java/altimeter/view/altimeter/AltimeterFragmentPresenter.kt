package org.testb.java.altimeter.view.altimeter

import org.testb.java.altimeter.service.Calibration
import org.testb.java.altimeter.view.BasePresenter

interface AltimeterFragmentPresenter : BasePresenter {
    fun resetCalibration(calibration: Calibration)
    fun undoResetCalibration(): Calibration?
}