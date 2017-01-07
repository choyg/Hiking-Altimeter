package testb.org.altimeter.presenter;

import testb.org.altimeter.view.CalibrationDialog;


public interface CalibrationDialogPresenter {

    void dialogResetButtonClicked();

    void dialogCancelButtonClicked();

    void toastUndoActionClicked();

    void setView(CalibrationDialog view);

}
