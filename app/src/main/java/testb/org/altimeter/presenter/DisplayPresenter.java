package testb.org.altimeter.presenter;

public interface DisplayPresenter {
    void dialogResetButtonClicked();

    void dialogCancelButtonClicked();

    void pressureChanged(float newPressure);
}
