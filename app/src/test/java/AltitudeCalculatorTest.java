import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import testb.org.altimeter.Constants;
import testb.org.altimeter.data.AltitudeRepository;
import testb.org.altimeter.logic.AltitudeCalculator;

import static org.junit.Assert.assertEquals;

public class AltitudeCalculatorTest {
    @Mock
    private AltitudeRepository altitudeRepository;
    private AltitudeCalculator calculator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(altitudeRepository.getSealevelPressure()).thenReturn(Constants.DEFAULT_SEA_PRESSURE);
        Mockito.when(altitudeRepository.getPressureHPA()).thenReturn(Constants.DEFAULT_SEA_PRESSURE);
        calculator = new AltitudeCalculator(altitudeRepository);
    }

    @Test
    public void testCalculateAltitude() {
        assertEquals(0, calculator.calculateAltitude(), 0);
        assertEquals(1013.25, calculator.calculateQNH(0), 0);
        assertEquals(1025.3484, calculator.calculateQNH(100), 0.001);
    }
}
