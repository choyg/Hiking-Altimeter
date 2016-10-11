package testb.org.altimeter.Presenters;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;

/**
 * Created by testb on 10/11/16.
 */

public class CalibrationDialogPresenterImpl implements CalibrationDialogPresenter {
    AltitudeRepository repository;
    public CalibrationDialogPresenterImpl(AltitudeRepository repository){
        this.repository = repository;
    }
    @Override
    public void dialogResetButtonClicked() {
        repository.setSeaLevelPressure(Constants.DEFAULT_SEA_PRESSURE);
        System.out.println("reset button pressed presenter detect");

    }

    @Override
    public void dialogCancelButtonClicked() {

    }
}
