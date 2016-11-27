import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import testb.org.altimeter.Data.AltitudeRepository;
import testb.org.altimeter.Presenters.CalibrationDialogPresenter;
import testb.org.altimeter.Presenters.CalibrationDialogPresenterImpl;
import testb.org.altimeter.Views.CalibrationDialog;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class CalibrationDialogTest {
    private CalibrationDialogPresenter calibrationDialogPresenter;
    @Mock
    private AltitudeRepository altitudeRepository;
    @Mock
    private CalibrationDialog calibrationDialog;

    @Before
    public void setupCalibrationDialogPresenter() {
        MockitoAnnotations.initMocks(this);
        calibrationDialogPresenter = new CalibrationDialogPresenterImpl(calibrationDialog, altitudeRepository);
    }

    @Test
    public void resetDialogRepositoryResets() {
        calibrationDialogPresenter.dialogResetButtonClicked();
        verify(altitudeRepository).resetSeaLevelPressure();
    }

    @Test
    public void undoDialogRepositoryRestores() {
        calibrationDialogPresenter.toastUndoActionClicked();
        verify(altitudeRepository).getUndoCalibration();
    }


}
