package com.blundell.tut;

public final class TemperatureConverter {

    static final double ABSOLUTE_ZERO_C = -273.15d;
    static final double ABSOLUTE_ZERO_F = -459.67d;

    private static final String ERROR_MESSAGE_BELOW_ZERO_FMT = "Invalid temperature: %.2f%c below absolute zero";

    private TemperatureConverter() {
        // non-instantiable helper class
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        if (fahrenheit < ABSOLUTE_ZERO_F) {
            String msg = String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, fahrenheit, 'F');
            throw new InvalidTemperatureException(msg);
        }
        return ((fahrenheit - 32) / 1.8d);
    }

    public static double celsiusToFahrenheit(double celsius) {
        if (celsius < ABSOLUTE_ZERO_C) {
            String msg = String.format(ERROR_MESSAGE_BELOW_ZERO_FMT, celsius, 'C');
            throw new InvalidTemperatureException(msg);
        }
        return (celsius * 1.8d + 32);
    }

}
