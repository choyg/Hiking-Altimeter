package testb.org.altimeter.Data;

/**
 * Created by testb on 10/1/16.
 */

public class AltitudeRepositoryImpl implements AltitudeRepository {

    //coupling with sharedpref?
    //probably get a sharedpref singleton

    @Override
    public double getPressureHPA() {
        return 0;
    }

    @Override
    public void setPressureHPA() {

    }

    @Override
    public void setSeaLevelPressure() {

    }

    @Override
    public double getSealevelPressure() {

    }

    @Override
    public void resetSeaLevelPressure() {

    }
}
