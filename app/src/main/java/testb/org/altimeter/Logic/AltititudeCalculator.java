package testb.org.altimeter.Logic;

import testb.org.altimeter.Data.AltitudeConstants;
import testb.org.altimeter.Data.AltitudeRepository;

/**
 * Created by testb on 9/21/16.
 */
public class AltititudeCalculator {

    AltitudeRepository repo;

    public AltititudeCalculator(AltitudeRepository repo) {
        this.repo = repo;
    }

    //inject repository
    public double calculateAltitude() {
        double alt = (288.15 - (AltitudeConstants.SEA_TEMPERATURE / (Math.pow(repo.getSealevelPressure() / repo.getPressureHPA(), 0.19026324)))) / 0.0065;
        return alt;
    }
    public float calculateQNH(double knownAltitude) {
        return (float) (repo.getPressureHPA() * Math.pow(AltitudeConstants.SEA_TEMPERATURE / (AltitudeConstants.SEA_TEMPERATURE - 0.0065 * knownAltitude), 5.25587611));
    }
}
