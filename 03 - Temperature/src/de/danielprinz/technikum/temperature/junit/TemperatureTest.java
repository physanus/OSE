package de.danielprinz.technikum.temperature.junit;

import de.danielprinz.technikum.temperature.Temperature;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by el17x002 on 26.09.2018.
 */
public class TemperatureTest {

    private static final double DELTA = 0.00000000001;


    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testGetCelsius() throws Exception {
        Temperature temperature = Temperature.fromCelsius(20);
        assertEquals(temperature.getCelsius(), 20, DELTA);
        assertEquals(temperature.getFahrenheit(), 68, DELTA);
        assertEquals(temperature.getKelvin(), 293.15, DELTA);

    }

    @Test
    public void testGetFahrenheit() throws Exception {
        Temperature temperature = Temperature.fromFahrenheit(68);
        assertEquals(temperature.getCelsius(), 20, DELTA);
        assertEquals(temperature.getFahrenheit(), 68, DELTA);
        assertEquals(temperature.getKelvin(), 293.15, DELTA);
    }

    @Test
    public void testGetKelvin() throws Exception {
        Temperature temperature = Temperature.fromKelvin(293.15);
        assertEquals(temperature.getCelsius(), 20, DELTA);
        assertEquals(temperature.getFahrenheit(), 68, DELTA);
        assertEquals(temperature.getKelvin(), 293.15, DELTA);
    }
}