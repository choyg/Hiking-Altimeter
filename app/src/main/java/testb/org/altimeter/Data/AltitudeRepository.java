package testb.org.altimeter.Data;

public interface AltitudeRepository extends Repository {
    double getPressureHPA();

    void setSeaLevelPressure(double seaLevelPressure);

    float getUndoCalibration();

    float getSealevelPressure();

    void resetSeaLevelPressure();

    int getDistanceUnit();
}
