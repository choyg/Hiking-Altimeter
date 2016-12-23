package testb.org.altimeter.view;

import testb.org.altimeter.model.Altitude;
import testb.org.altimeter.model.CalibrationModel;

public interface DisplayView {
    void updateElevation(Altitude text);

    void updateCalibration(CalibrationModel calibration);

}
