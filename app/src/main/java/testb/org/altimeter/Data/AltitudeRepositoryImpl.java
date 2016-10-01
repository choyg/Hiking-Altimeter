package testb.org.altimeter.Data;

/**
 * Created by testb on 10/1/16.
 */

public class AltitudeRepositoryImpl implements AltitudeRepository {
    @Override
    public double getPressureHPA() {
        return 0;
    }

    @Override
    public void setPressureHPA(double pressureHPA) {

    }

    @Override
    public void setSeaLevelPressure(double seaLevelPressure) {

    }

    @Override
    public double getSealevelPressure() {
        return 0;
    }

    @Override
    public void resetSeaLevelPressure() {

    }

    //coupling with sharedpref?
    //probably get a sharedpref singleton


}
