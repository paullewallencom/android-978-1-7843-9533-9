package com.blundell.tut;

import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TemperatureConverterTests {

    private static final HashMap<Double, Double> conversionTableDouble = new HashMap<Double, Double>() {{
        // initialize (c, f) pairs
        put(0.0, 32.0);
        put(100.0, 212.0);
        put(-1.0, 30.20);
        put(-100.0, -148.0);
        put(32.0, 89.60);
        put(-40.0, -40.0);
        put(-273.0, -459.40);
    }};

    @Test
    public final void testFahrenheitToCelsius() {
        for (double c : conversionTableDouble.keySet()) {
            double f = conversionTableDouble.get(c);
            double ca = TemperatureConverter.fahrenheitToCelsius(f);
            double delta = Math.abs(ca - c);
            String msg = "" + f + "F -> " + c + "C but is " + ca + " (delta " + delta + ")";
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test
    public final void testCelsiusToFahrenheit() {
        for (double c : conversionTableDouble.keySet()) {
            double f = conversionTableDouble.get(c);
            double fa = TemperatureConverter.celsiusToFahrenheit(c);
            double delta = Math.abs(fa - f);
            String msg = "" + c + "C -> " + f + "F but is " + fa + " (delta " + delta + ")";
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test(expected = InvalidTemperatureException.class)
    public final void testExceptionForLessThanAbsoluteZeroF() {
        TemperatureConverter.fahrenheitToCelsius(TemperatureConverter.ABSOLUTE_ZERO_F - 1);
    }

    @Test(expected = InvalidTemperatureException.class)
    public final void testExceptionForLessThanAbsoluteZeroC() {
        TemperatureConverter.celsiusToFahrenheit(TemperatureConverter.ABSOLUTE_ZERO_C - 1);
    }

}
