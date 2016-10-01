package testb.org.altimeter.Data;

/**
 * Created by testb on 10/1/16.
 */

public interface AltitudeRepository extends Repository {
    double getPressureHPA();
    void setPressureHPA(double pressureHPA);
    void setSeaLevelPressure(double seaLevelPressure);
    double getSealevelPressure();
    void resetSeaLevelPressure();
}
