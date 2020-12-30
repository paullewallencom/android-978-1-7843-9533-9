package bdd.steps.tc;

import com.blundell.tut.TemperatureConverter;

import org.givwenzen.annotations.DomainStep;
import org.givwenzen.annotations.DomainSteps;

@DomainSteps
public class TemperatureConverterSteps {

    private static final String CELSIUS = "Celsius";
    private static final String FAHRENHEIT = "Fahrenheit";
    private static final String UNIT_NAME = "(" + CELSIUS + "|" + FAHRENHEIT + ")";
    private static final String ANY_TEMPERATURE = "([-+]?\\d+(?:\\.\\d+)?)";

    private double inputTemperature = Double.NaN;

    @DomainStep("I(?: a|')m using the TemperatureConverter")
    public void createTemperatureConverter() {
        // do nothing
    }

    @DomainStep("I enter " + ANY_TEMPERATURE + " into the " + UNIT_NAME + " field")
    public void setField(double inputTemperature, String unitName) {
        this.inputTemperature = inputTemperature;
    }

    @DomainStep("I obtain " + ANY_TEMPERATURE + " in the " + UNIT_NAME + " field")
    public boolean verifyConversion(double expectedTemperature, String unitName) {
        double outputTemperature = convertInputInto(unitName);
        return Math.abs(outputTemperature - expectedTemperature) < 0.01D;
    }

    private double convertInputInto(String unitName) {
        double convertedInputTemperature;
        if (CELSIUS.equals(unitName)) {
            convertedInputTemperature = getCelsius();
        } else if (FAHRENHEIT.equals(unitName)) {
            convertedInputTemperature = getFahrenheit();
        } else {
            throw new RuntimeException("Unknown conversion unit" + unitName);
        }
        return convertedInputTemperature;
    }

    private double getCelsius() {
        return TemperatureConverter.fahrenheitToCelsius(inputTemperature);
    }

    private double getFahrenheit() {
        return TemperatureConverter.celsiusToFahrenheit(inputTemperature);
    }

    @DomainStep("I obtain '(Invalid temperature: " + ANY_TEMPERATURE + "(C|F)" + " below absolute zero)' exception")
    public boolean verifyException(String message, String value, String unit) {
        try {
            if ("C".equals(unit)) {
                getFahrenheit();
            } else {
                getCelsius();
            }
        } catch (RuntimeException ex) {
            return ex.getMessage().contains(message);
        }
        return false;
    }

}
