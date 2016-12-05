package testb.org.altimeter.view;

import testb.org.altimeter.model.CalibrationModel;

public interface DisplayView {
    void updateElevation(String text);

    void updateCalibration(CalibrationModel calibration);

}
