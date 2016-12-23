package testb.org.altimeter.data;

public interface AltitudeRepository extends Repository {
    void setPressureHPA(float pressure);

    float getPressureHPA();

    void setSeaLevelPressure(double seaLevelPressure);

    float getSealevelPressure();

    void resetSeaLevelPressure();

    float getUndoCalibration();

    int getDistanceUnit();
}
