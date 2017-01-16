package testb.org.altimeter.presenter;

import javax.inject.Inject;

import testb.org.altimeter.Constants;
import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.logic.AltitudeCalculator;
import testb.org.altimeter.model.Altitude;
import testb.org.altimeter.view.DisplayView;

public class DisplayPresenterImpl implements DisplayPresenter {
    private AltitudeRepository repository;
    private DisplayView view;
    private AltitudeCalculator calculator;

    @Inject
    public DisplayPresenterImpl(AltitudeRepository repository) {
        this.repository = repository;
        calculator = new AltitudeCalculator(repository);
    }

    public void setView(DisplayView view) {
        this.view = view;
        onPressureChanged(0);
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
    public void onPressureChanged(float pressure) {
        float altitude = calculator.calculateAltitude();
        int unit = repository.getDistanceUnit();
        switch (unit) {
            case Constants.Units.FEET:
                altitude *= Constants.METERS_TO_FEET;
                break;
            case Constants.Units.METERS:
                //no change to altitude
                break;
        }
        view.updateElevation(new Altitude(altitude, unit));

    }


}
