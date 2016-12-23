package testb.org.altimeter.model;

import testb.org.altimeter.Constants;

/**
 * Single use altitude model.
 */
public class Altitude {
    private final String altitude;
    private final int unit;

    /**
     * Single use altitude model. All values are final and cannot be updated.
     * @param altitude The height in String representation
     * @param unit An int that maps to a distance unit from {@link testb.org.altimeter.Constants.Units}
     */
    public Altitude(String altitude, int unit) {
        this.altitude = altitude;
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(altitude);
        switch (unit) {
            case Constants.Units.FEET:
                stringBuilder.append("ft");
                break;
            case Constants.Units.METERS:
                stringBuilder.append("m");
                break;
        }
        return stringBuilder.toString();
    }

    /**
     * Uses {@link Constants.Units} to map units to int
     * @return the integer representation of the distance unit
     */
    public int getUnit(){
        return unit;
    }

    /**
     * Method only returns the altitude value without a unit. Use {@link #toString()} if the unit is needed.
     * @return the string representation of altitude
     */
    public String getAltitude(){
        return altitude;
    }

}
