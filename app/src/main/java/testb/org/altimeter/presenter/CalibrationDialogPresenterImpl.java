package testb.org.altimeter.presenter;

import javax.inject.Inject;

import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.view.CalibrationDialog;

public class CalibrationDialogPresenterImpl implements CalibrationDialogPresenter {
    private AltitudeRepository repository;
    private CalibrationDialog view;

    @Inject
    public CalibrationDialogPresenterImpl(AltitudeRepository repository) {
        this.repository = repository;
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

    @Override
    public void setView(CalibrationDialog view) {
        this.view = view;
    }
}
