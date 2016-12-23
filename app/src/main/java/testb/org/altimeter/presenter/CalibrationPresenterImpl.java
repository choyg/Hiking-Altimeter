package testb.org.altimeter.presenter;

import javax.inject.Inject;

import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.view.CalibrationView;

/**
 * Presenter for CalibrationFragment
 */
public class CalibrationPresenterImpl implements CalibrationPresenter {

    private CalibrationView view;
    private String calibrationVal;
    private AltitudeRepository repository;

    @Inject
    public CalibrationPresenterImpl(AltitudeRepository repository) {
        this.repository = repository;
        //view.setCalibrationText(calibrationVal); Presenter is null when this is called for some reason
    }

    public void setView(CalibrationView view, String initialVal) {
        this.view = view;
        calibrationVal = initialVal;
    }

    @Override
    public void calibrationTextChanged(String calibrationText) { //listener for any changes to the textfield in the view
        if (calibrationVal.equals("0")) {
            view.hideRemoveButton();
        } else {
            view.showRemoveButton();
        }
    }

    @Override
    public void deleteButtonClick() {
        clearCalibrationText();
    }

    @Override
    public void numberButtonClick(String id) {
        if (calibrationVal.equals("0")) {
            view.setCalibrationText(calibrationVal = id);
        } else if (calibrationVal.length() < 8 || (calibrationVal.length() < 9 && calibrationVal.contains("."))) {
            view.setCalibrationText(calibrationVal += id);
        }
    }

    @Override
    public void decimalButtonClick() {
        if (!calibrationVal.contains(".")) {
            view.setCalibrationText(calibrationVal += ".");
        }
    }

    @Override
    public void calibrationButtonClick() {
        //TODO
    }


    @Override
    public int getUnits() {
        return repository.getDistanceUnit();
    }

    @Override
    public void clearCalibrationText() {
        view.setCalibrationText(calibrationVal = "0");
    }

    @Override
    public String getCalibrationVal() {
        return calibrationVal;
    }
}
