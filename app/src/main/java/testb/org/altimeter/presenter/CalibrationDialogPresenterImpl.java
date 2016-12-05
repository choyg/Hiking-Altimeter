package testb.org.altimeter.presenter;

import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.view.CalibrationDialog;

public class CalibrationDialogPresenterImpl implements CalibrationDialogPresenter {
    private AltitudeRepository repository;
    private CalibrationDialog view;

    public CalibrationDialogPresenterImpl(CalibrationDialog view, AltitudeRepository repository) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void dialogResetButtonClicked() {
        repository.resetSeaLevelPressure();
        view.showUndoSnackbar();
    }

    @Override
    public void dialogCancelButtonClicked() {
        //nothing to do rn
    }

    @Override
    public void toastUndoActionClicked() {
        repository.setSeaLevelPressure(repository.getUndoCalibration());
    }
}
