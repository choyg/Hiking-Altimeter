package testb.org.altimeter.logic;

import testb.org.altimeter.Constants;
import testb.org.altimeter.data.AltitudeRepository;

public class AltitudeCalculator {


    private AltitudeRepository repo;

    public AltitudeCalculator(AltitudeRepository repo) {
        this.repo = repo;
    }

    public float calculateAltitude() {
        return (float) ((Constants.SEA_TEMPERATURE - (Constants.SEA_TEMPERATURE / (Math.pow(repo.getSealevelPressure() / repo.getPressureHPA(), 0.19026324)))) / 0.0065);
    }

    public float calculateQNH(double knownAltitude) {
        return (float) (repo.getPressureHPA() * Math.pow(Constants.SEA_TEMPERATURE / (Constants.SEA_TEMPERATURE - 0.0065 * knownAltitude), 5.25587611));
    }
}
