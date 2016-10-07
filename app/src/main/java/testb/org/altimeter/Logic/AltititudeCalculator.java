package testb.org.altimeter.Logic;

import testb.org.altimeter.Constants;
import testb.org.altimeter.Data.AltitudeRepository;

public class AltititudeCalculator {

    AltitudeRepository repo;

    public AltititudeCalculator(AltitudeRepository repo) {
        this.repo = repo;
    }

    //inject repository
    public double calculateAltitude() {
        double alt = (288.15 - (Constants.SEA_TEMPERATURE / (Math.pow(repo.getSealevelPressure() / repo.getPressureHPA(), 0.19026324)))) / 0.0065;
        return alt;
    }
    public float calculateQNH(double knownAltitude) {
        return (float) (repo.getPressureHPA() * Math.pow(Constants.SEA_TEMPERATURE / (Constants.SEA_TEMPERATURE - 0.0065 * knownAltitude), 5.25587611));
    }
}
