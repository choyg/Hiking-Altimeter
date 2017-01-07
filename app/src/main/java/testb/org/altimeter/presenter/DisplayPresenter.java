package testb.org.altimeter.presenter;

import testb.org.altimeter.view.DisplayView;

public interface DisplayPresenter {
    void dialogResetButtonClicked();

    void dialogCancelButtonClicked();

    void onPressureChanged(float pressure);

    void setView(DisplayView view);
}
