package testb.org.altimeter.Presenters;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.Views.DisplayView;

public class DisplayPresenterImpl implements DisplayPresenter {
    private AltitudeRepository repository;
    private DisplayView view;

    public DisplayPresenterImpl(AltitudeRepository repository, DisplayView view) {
        this.repository = repository;
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
