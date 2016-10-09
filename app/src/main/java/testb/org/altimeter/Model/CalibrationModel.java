package testb.org.altimeter.Model;

import java.util.Date;

/**
 * Created by testb on 10/7/16.
 */

public class CalibrationModel {
    public final String elevation;
    public final Date date;

    public CalibrationModel(String elevation, Date date) {
        this.elevation = elevation;
        this.date = date;
    }
}
