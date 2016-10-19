package testb.org.altimeter.Views;

import testb.org.altimeter.Model.CalibrationModel;

public interface DisplayView {
    void updateElevation(String text);

    void updateCalibration(CalibrationModel calibration);

}
