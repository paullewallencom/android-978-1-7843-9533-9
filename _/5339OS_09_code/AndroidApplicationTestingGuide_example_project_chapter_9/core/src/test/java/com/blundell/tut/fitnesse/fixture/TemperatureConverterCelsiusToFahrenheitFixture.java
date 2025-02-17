package com.blundell.tut.fitnesse.fixture;

import com.blundell.tut.TemperatureConverter;

public class TemperatureConverterCelsiusToFahrenheitFixture {

    private double celsius;

    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public String fahrenheit() throws Exception {
        try {
            double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
            return String.valueOf(fahrenheit);
        } catch (RuntimeException e) {
            return e.getLocalizedMessage();
        }
    }

}
