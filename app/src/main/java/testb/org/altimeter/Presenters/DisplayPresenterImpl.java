package testb.org.altimeter.Presenters;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;

/**
 * Created by testb on 10/11/16.
 */

public class DisplayPresenterImpl implements DisplayPresenter {
    private AltitudeRepository repository;

    public DisplayPresenterImpl(AltitudeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void dialogResetButtonClicked() {
        repository.setSeaLevelPressure(Constants.DEFAULT_SEA_PRESSURE);
    }

    @Override
    public void dialogCancelButtonClicked() {

    }
}
