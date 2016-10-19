package testb.org.altimeter.Presenters;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.Views.CalibrationDialog;

public class CalibrationDialogPresenterImpl implements CalibrationDialogPresenter {
    AltitudeRepository repository;
    CalibrationDialog view;

    public CalibrationDialogPresenterImpl(CalibrationDialog view, AltitudeRepository repository) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void dialogResetButtonClicked() {
        repository.setSeaLevelPressure(Constants.DEFAULT_SEA_PRESSURE);
        view.showResetSnackbar();
    }

    @Override
    public void dialogCancelButtonClicked() {
        //nothing to do rn
    }

    @Override
    public void toastResetActionClicked() {
        //TODO restore backup calibration
    }
}
