package testb.org.altimeter.presenter;

import javax.inject.Inject;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.view.DisplayView;

public class DisplayPresenterImpl implements DisplayPresenter {
    private AltitudeRepository repository;
    private DisplayView view;

    @Inject
    public DisplayPresenterImpl(AltitudeRepository repository) {
        this.repository = repository;
    }

    public void setView(DisplayView view) {
        this.view = view;
    }

    @Override
    public void dialogResetButtonClicked() {
        repository.setSeaLevelPressure(Constants.DEFAULT_SEA_PRESSURE);
    }

    @Override
    public void dialogCancelButtonClicked() {
        //do nothing really
    }

    @Override
    public void pressureChanged(float pressure) {
        view.updateElevation(String.valueOf(pressure));
    }
}
