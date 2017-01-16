package testb.org.altimeter.model;

import testb.org.altimeter.Constants;

/**
 * Single use altitude model.
 */
public class Altitude {
    private final float altitude;
    private final int unit;

    /**
     * Single use stateful altitude model. All values are final and should not be updated. <p>
     * Ensure that the altitude supplied is already converted to the proper unit.
     *
     * @param altitude The height in String representation
     * @param unit     An int that maps to a distance unit from {@link testb.org.altimeter.Constants.Units}
     */
    public Altitude(float altitude, int unit) {
        this.altitude = altitude;
        this.unit = unit;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(altitude));
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
     *
     * @return the integer representation of the distance unit
     */
    public int getUnit() {
        return unit;
    }

    /**
     * Method only returns the altitude value without a unit. Use {@link #toString()} if the unit is needed.
     *
     * @return the float representation of altitude
     */
    public float getAltitude() {
        return altitude;
    }

}
