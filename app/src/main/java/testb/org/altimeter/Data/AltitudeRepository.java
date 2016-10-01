package testb.org.altimeter.Data;

/**
 * Created by testb on 10/1/16.
 */

public interface AltitudeRepository extends Repository {
    public double getPressureHPA();
    public void setPressureHPA(double pressureHPA);
    public void setSeaLevelPressure(double seaLevelPressure);
    public double getSealevelPressure();
    public void resetSeaLevelPressure();
}
