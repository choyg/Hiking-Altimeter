package testb.org.altimeter.presenter;

import testb.org.altimeter.view.CalibrationView;

public interface CalibrationPresenter {
    void deleteButtonClick();

    void numberButtonClick(String id);

    void decimalButtonClick();

    void calibrationButtonClick();

    int getUnits();

    void clearCalibrationText();

    void calibrationTextChanged(String calibrationVal);

    String getCalibrationVal();

    void setView(CalibrationView view);

    void restoreState(String calibrationVal);
}
