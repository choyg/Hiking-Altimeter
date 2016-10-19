package testb.org.altimeter.Data;

public interface AltitudeRepository extends Repository {
    double getPressureHPA();

    void setLastKnownPressureHPA(double pressureHPA);

    void setSeaLevelPressure(double seaLevelPressure);

//    void setBackupCalibration(); TODO

    double getSealevelPressure();

    void resetSeaLevelPressure();

    int getDistanceUnit();
}
