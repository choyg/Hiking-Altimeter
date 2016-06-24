package testb.org.altimeter;

import android.widget.Toast;

public class CalculationSingleton {

    final private double CONSTANT_A = 87.9826374782; // R divided by g*M * log(SeaLevelPressure in HPA)
    final private double CONSTANT_B = 29.2717674; // R divided by g*M
    final private double temperatureCelsius = 18.3333;
    final private float DEFAULT_SEA_PRESSURE = 1013.25f;
    final private double SEA_TEMPERATURE = 288.15;
    private float seaLevelPressure = 1013.25f;
    private double pressureHPA = 1012.25;
    private static CalculationSingleton calculationInstance = null;

    private CalculationSingleton() {
    }

    public static synchronized CalculationSingleton getInstance() {
        if (calculationInstance == null) {
            calculationInstance = new CalculationSingleton();
            //test
        }
        return calculationInstance;
    }

    public double calculateAltitude() {
        double alt = (288.15 - (SEA_TEMPERATURE / (Math.pow(seaLevelPressure / pressureHPA, 0.19026324)))) / 0.0065;
        return alt;
    }

    public float calculateQNH(double knownAltitude) {
        return (float) (pressureHPA * Math.pow(SEA_TEMPERATURE / (SEA_TEMPERATURE - 0.0065 * knownAltitude), 5.25587611));
    }

    public void setSealevelPressure(double pressure) {
        seaLevelPressure = (float) pressure;
    }

    public void setPressure(double pressure) {
        pressureHPA = pressure;
    }

    public double getPressure() {
        return pressureHPA;
    }

    public float getSeaLevelPressure() {
        return seaLevelPressure;
    }

    public void resetSealLevelPressure() {
        setSealevelPressure(DEFAULT_SEA_PRESSURE);
    }

}
