package com.blundell.tut;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.test.ViewAsserts.*;

public class TemperatureConverterActivityTests extends ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private TemperatureConverterActivity activity;
    private EditNumber celsiusInput;
    private EditNumber fahrenheitInput;
    private TextView celsiusLabel;
    private TextView fahrenheitLabel;

    public TemperatureConverterActivityTests() {
        this("TemperatureConverterActivityTests");
    }

    public TemperatureConverterActivityTests(String name) {
        super(TemperatureConverterActivity.class);
        setName(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        celsiusLabel = (TextView) activity.findViewById(R.id.converter_celsius_label);
        celsiusInput = (EditNumber) activity.findViewById(R.id.converter_celsius_input);
        fahrenheitLabel = (TextView) activity.findViewById(R.id.converter_fahrenheit_label);
        fahrenheitInput = (EditNumber) activity.findViewById(R.id.converter_fahrenheit_input);
    }

//	public void testOnCreateBundle() {
//		fail("Not yet implemented");
//	}

    public void testHasInputFields() {
        assertNotNull(celsiusInput);
        assertNotNull(fahrenheitInput);
    }

    public void testFieldsShouldStartEmpty() {
        assertEquals("", celsiusInput.getText().toString());
        assertEquals("", fahrenheitInput.getText().toString());
    }

    public void testFieldsOnScreen() {
        View origin = activity.getWindow().getDecorView();

        assertOnScreen(origin, celsiusInput);
        assertOnScreen(origin, fahrenheitInput);
    }

    public void testAlignment() {
        assertLeftAligned(celsiusLabel, celsiusInput);
        assertLeftAligned(fahrenheitLabel, fahrenheitInput);
        assertLeftAligned(celsiusInput, fahrenheitInput);
        assertRightAligned(celsiusInput, fahrenheitInput);
    }

    public void testCelciusInputFieldCoversEntireScreen() {
        int expected = LayoutParams.MATCH_PARENT;

        LayoutParams lp = celsiusInput.getLayoutParams();

        assertEquals("celsiusInput layout width is not MATCH_PARENT", expected, lp.width);
    }

    public void testFahrenheitInputFieldCoversEntireScreen() {
        LayoutParams lp = fahrenheitInput.getLayoutParams();

        int expected = LayoutParams.MATCH_PARENT;

        assertEquals("fahrenheitInput layout width is not MATCH_PARENT", expected, lp.width);
    }

    public void testFontSizes() {
        float pixelSize = getFloatPixelSize(R.dimen.label_text_size);

        assertEquals(pixelSize, celsiusLabel.getTextSize());
        assertEquals(pixelSize, fahrenheitLabel.getTextSize());
    }

    public void testCelsiusInputMargins() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) celsiusInput.getLayoutParams();

        assertEquals(getIntPixelSize(R.dimen.margin), lp.leftMargin);
        assertEquals(getIntPixelSize(R.dimen.margin), lp.rightMargin);
    }

    public void testFahrenheitInputMargins() {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fahrenheitInput.getLayoutParams();

        assertEquals(getIntPixelSize(R.dimen.margin), lp.leftMargin);
        assertEquals(getIntPixelSize(R.dimen.margin), lp.rightMargin);
    }

    public void testCelsiusInputJustification() {
        int expectedGravity = Gravity.END | Gravity.CENTER_VERTICAL;
        int actual = celsiusInput.getGravity();
        String errorMessage = String.format("Expected 0x%02x but was 0x%02x", expectedGravity, actual);
        assertEquals(errorMessage, expectedGravity, actual);
    }

    public void testFahrenheitInputJustification() {
        int expectedGravity = Gravity.END | Gravity.CENTER_VERTICAL;
        int actual = fahrenheitInput.getGravity();
        String errorMessage = String.format("Expected 0x%02x but was 0x%02x", expectedGravity, actual);
        assertEquals(errorMessage, expectedGravity, actual);
    }

    public void testVirtualKeyboardSpaceReserved() {
        int expected = getIntPixelSize(R.dimen.keyboard_space);

        int actual = fahrenheitInput.getBottom();

        String errorMessage = "Space not reserved, expected " + expected + " actual " + actual;
        assertTrue(errorMessage, actual <= expected);
    }

    @UiThreadTest
    public void testFahrenheitToCelsiusConversion() {
        celsiusInput.clear();
        fahrenheitInput.clear();

        fahrenheitInput.requestFocus();
        fahrenheitInput.setText("32.5");
        celsiusInput.requestFocus();
        double f = 32.5;
        double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
        double actualC = celsiusInput.getNumber();
        double delta = Math.abs(expectedC - actualC);

        String msg = "" + f + "F -> " + expectedC + "C but was " + actualC + "C (delta " + delta + ")";
        assertTrue(msg, delta < 0.005);
    }

    public void testInputFilter() throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                celsiusInput.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();

        sendKeys("MINUS 1 PERIOD 2 PERIOD 3 PERIOD 4");
        double number = celsiusInput.getNumber();

        String msg = "-1.2.3.4 should be filtered to -1.234 but is " + number;
        assertEquals(msg, -1.234d, number);
    }

    public void testInvalidTemperatureInCelsius() throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                celsiusInput.requestFocus();
            }
        });
        getInstrumentation().waitForIdleSync();

        // temp less than ABSOLUTE_ZERO_C
        sendKeys("MINUS 3 8 0");

        String msg = "Expected celsius input to contain an error msg";
        assertNotNull(msg, celsiusInput.getError());
    }

    private int getIntPixelSize(int dimensionResourceId) {
        return (int) getFloatPixelSize(dimensionResourceId);
    }

    private float getFloatPixelSize(int dimensionResourceId) {
        return getActivity().getResources().getDimensionPixelSize(dimensionResourceId);
    }
}
