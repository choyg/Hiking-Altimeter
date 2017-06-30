import com.gchoy.altimeter.view.altimeter.AltimeterFragment;
import com.gchoy.altimeter.view.altimeter.AltimeterFragmentPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AltimeterFragmentPresenterTest {
    private AltimeterFragmentPresenterImpl presenter;

    @Mock
    private AltimeterFragment view;

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
