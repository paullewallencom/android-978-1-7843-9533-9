package com.blundell.tut;

import java.util.HashMap;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TemperatureConverterTests {

    private static final HashMap<Double, Double> conversionTable = new HashMap<Double, Double>() {{
        // initialize (celsius, fahrenheit) pairs
        put(0.0, 32.0);
        put(100.0, 212.0);
        put(-1.0, 30.20);
        put(-100.0, -148.0);
        put(32.0, 89.60);
        put(-40.0, -40.0);
        put(-273.0, -459.40);
    }};

    @Test
    public void testFahrenheitToCelsius() {
        for (double knownCelsius : conversionTable.keySet()) {
            double knownFahrenheit = conversionTable.get(knownCelsius);

            double resultCelsius = TemperatureConverter.fahrenheitToCelsius(knownFahrenheit);

            double delta = Math.abs(resultCelsius - knownCelsius);
            String msg = knownFahrenheit + "F -> " + knownCelsius + "C but is " + resultCelsius;
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test
    public void testCelsiusToFahrenheit() {
        for (double knownCelsius : conversionTable.keySet()) {
            double knownFahrenheit = conversionTable.get(knownCelsius);

            double resultFahrenheit = TemperatureConverter.celsiusToFahrenheit(knownCelsius);

            double delta = Math.abs(resultFahrenheit - knownFahrenheit);
            String msg = knownCelsius + "C -> " + knownFahrenheit + "F but is " + resultFahrenheit;
            assertTrue(msg, delta < 0.0001);
        }
    }

    @Test(expected = InvalidTemperatureException.class)
    public void testExceptionForLessThanAbsoluteZeroF() {
        TemperatureConverter.fahrenheitToCelsius(TemperatureConverter.ABSOLUTE_ZERO_F - 1);
    }

    @Test(expected = InvalidTemperatureException.class)
    public void testExceptionForLessThanAbsoluteZeroC() {
        TemperatureConverter.celsiusToFahrenheit(TemperatureConverter.ABSOLUTE_ZERO_C - 1);
    }

}
