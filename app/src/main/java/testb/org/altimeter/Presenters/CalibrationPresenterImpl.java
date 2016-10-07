package testb.org.altimeter.Presenters;

import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.Views.CalibrationView;

/**
 * Presenter for CalibrationFragment
 */
public class CalibrationPresenterImpl implements CalibrationPresenter {

    CalibrationView view;
    String calibrationText;
    AltitudeRepository repository;

    public CalibrationPresenterImpl(CalibrationView view, AltitudeRepository repository) {
        this.view = view;
        this.repository = repository;
        calibrationText = view.getCalibrationText();
    }

    public void calibrationTextChanged(String calibrationText) { //listener for any changes to the textfield in the view
        this.calibrationText = calibrationText;
    }

    @Override
    public void deleteButtonClick() {
        view.setCalibrationText("0");
    }

    @Override
    public void numberButtonClick(String id) {
        //check
        if (calibrationText.isEmpty()) {
            calibrationText = "";
        } else if (calibrationText.length() < 8 || (calibrationText.length() < 9 && calibrationText.contains("."))) {
            view.setCalibrationText(calibrationText += id);
        }

        if (calibrationText.equals("0")) {
            view.hideRemoveButton();
        } else {
            view.showRemoveButton();
        }
    }

    @Override
    public void decimalButtonClick() {
        if (!calibrationText.contains(".")) {
            view.setCalibrationText(calibrationText += ".");
        }
    }

    @Override
    public void calibrationButtonClick() {
    }

    @Override
    public int getUnits() {
        return repository.getDistanceUnit();
    }
}
