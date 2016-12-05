package testb.org.altimeter.presenter;

public interface CalibrationPresenter {
    void deleteButtonClick();

    void numberButtonClick(String id);

    void decimalButtonClick();

    void calibrationButtonClick();

    int getUnits();

    void clearCalibrationText();

    void calibrationTextChanged(String calibrationVal);

    String getCalibrationVal();
}
