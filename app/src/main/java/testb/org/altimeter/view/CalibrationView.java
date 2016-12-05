package testb.org.altimeter.view;

/**
 * Created by testb on 9/21/16.
 */
public interface CalibrationView {
    /**
     * Update the top text which is used for custom calibration
     * Can be changed when the user
     */
    void showRemoveButton();

    void hideRemoveButton();

    String getCalibrationText();

    void setCalibrationText(String text);
}
