package org.testb.java.altimeter.view.altimeter

import org.testb.java.altimeter.service.Altitude
import org.testb.java.altimeter.service.Calibration

interface AltimeterView {
    /**
     * @parm altitude The Altitude representation to be formatted and displayed
     */
    fun setAltimeterText(altitude: Altitude)

    /**
     * Displays a calibration button which shows dialog containing to reset
     */
    fun setCalibration(calibration: Calibration)

    /**
     * Sets visibility of calibration button
     */
    fun setCalibrationVisible(boolean: Boolean)

    fun showUndoSnackbar()
}