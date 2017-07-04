import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.testb.altimeter.view.altimeter.AltimeterFragmentPresenterImpl;
import org.testb.altimeter.view.altimeter.AltimeterView;

public class AltimeterFragmentPresenterTest {
    private AltimeterFragmentPresenterImpl presenter;

    @Mock
    private AltimeterView view;

    @Before
    public void setUp() {
        presenter = new AltimeterFragmentPresenterImpl(view);
    }

    @Test
    public void newAltitudeRelaysToView() {

    }

    @Test
    public void newCalibrationChangesVisibility() {

    }


}
