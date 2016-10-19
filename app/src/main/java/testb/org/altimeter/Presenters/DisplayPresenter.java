package testb.org.altimeter.Presenters;

public interface DisplayPresenter {
    void dialogResetButtonClicked();

    void dialogCancelButtonClicked();

    void pressureChanged(float newPressure);
}
