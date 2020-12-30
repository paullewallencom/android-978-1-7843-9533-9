package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;

import static com.blundell.tut.TemperatureConverterActivity.TemperatureChangedWatcher.newCelciusToFehrenheitWatcher;
import static com.blundell.tut.TemperatureConverterActivity.TemperatureChangedWatcher.newFehrenheitToCelciusWatcher;

public class TemperatureConverterActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        EditNumber celsiusEditNumber = (EditNumber) findViewById(R.id.converter_celsius_input);
        EditNumber fahrenheitEditNumber = (EditNumber) findViewById(R.id.converter_fahrenheit_input);
        celsiusEditNumber.addTextChangedListener(newCelciusToFehrenheitWatcher(celsiusEditNumber, fahrenheitEditNumber));
        fahrenheitEditNumber.addTextChangedListener(newFehrenheitToCelciusWatcher(fahrenheitEditNumber, celsiusEditNumber));
    }

    /**
     * Changes fields values when the text changes; applying the correlated conversion method.
     */
    static class TemperatureChangedWatcher implements TextWatcher {

        private static final boolean BENCHMARK_TEMPERATURE_CONVERSION = true;
        private final EditNumber sourceEditNumber;
        private final EditNumber destinationEditNumber;
        private final Option option;
        String TAG = "foo";

        private TemperatureChangedWatcher(Option option, EditNumber source, EditNumber destination) {
            this.option = option;
            this.sourceEditNumber = source;
            this.destinationEditNumber = destination;
        }

        static TemperatureChangedWatcher newCelciusToFehrenheitWatcher(EditNumber source, EditNumber destination) {
            return new TemperatureChangedWatcher(Option.C2F, source, destination);
        }

        static TemperatureChangedWatcher newFehrenheitToCelciusWatcher(EditNumber source, EditNumber destination) {
            return new TemperatureChangedWatcher(Option.F2C, source, destination);
        }

        @Override
        public void onTextChanged(CharSequence input, int start, int before, int count) {
            if (!destinationEditNumber.hasWindowFocus() || destinationEditNumber.hasFocus() || input == null) {
                return;
            }

            String str = input.toString();
            if ("".equals(str)) {
                destinationEditNumber.setText("");
                return;
            }

            if (BENCHMARK_TEMPERATURE_CONVERSION) {
                Debug.startMethodTracing();
            }

            try {
                double temp = Double.parseDouble(str);
                double result = (option == Option.C2F)
                        ? TemperatureConverter.celsiusToFahrenheit(temp)
                        : TemperatureConverter.fahrenheitToCelsius(temp);
                String resultString = String.format("%.2f", result);
                destinationEditNumber.setNumber(result);
                destinationEditNumber.setSelection(resultString.length());
            } catch (NumberFormatException ignore) {
                // WARNING this is generated whilst numbers are being entered,
                // for example just a '-' so we don't want to show the error just yet
            } catch (Exception e) {
                sourceEditNumber.setError("ERROR: " + e.getLocalizedMessage());
            }

            if (BENCHMARK_TEMPERATURE_CONVERSION) {
                Debug.stopMethodTracing();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // not used
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // not used
        }
    }

}
