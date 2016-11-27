package testb.org.altimeter.Model;

import java.util.Date;

public class CalibrationModel {
    public final String elevation;
    public final Date date;

    public CalibrationModel(String elevation, Date date) {
        this.elevation = elevation;
        this.date = date;
    }
}
