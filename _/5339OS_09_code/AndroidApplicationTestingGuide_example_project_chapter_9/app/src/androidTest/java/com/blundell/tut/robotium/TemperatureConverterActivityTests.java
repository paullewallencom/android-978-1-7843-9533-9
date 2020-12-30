package com.blundell.tut.robotium;

import android.test.ActivityInstrumentationTestCase2;

import com.blundell.tut.EditNumber;
import com.blundell.tut.TemperatureConverter;
import com.blundell.tut.TemperatureConverterActivity;
import com.robotium.solo.Solo;

public class TemperatureConverterActivityTests extends ActivityInstrumentationTestCase2<TemperatureConverterActivity> {

    private static final int CELSIUS_INPUT = 0;
    private static final int FAHRENHEIT_INPUT = 1;

    private TemperatureConverterActivity activity;
    private Solo solo;

    public TemperatureConverterActivityTests() {
        super(TemperatureConverterActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        solo = new Solo(getInstrumentation(), activity);
    }

    public void testFahrenheitToCelsiusConversion() {
        solo.clearEditText(CELSIUS_INPUT);
        solo.clearEditText(FAHRENHEIT_INPUT);

        solo.clickOnEditText(FAHRENHEIT_INPUT);
        solo.enterText(FAHRENHEIT_INPUT, "32.5");
        solo.clickOnEditText(CELSIUS_INPUT);
        double f = 32.5;
        double expectedC = TemperatureConverter.fahrenheitToCelsius(f);
        double actualC = ((EditNumber) solo.getEditText(CELSIUS_INPUT)).getNumber();
        double delta = Math.abs(expectedC - actualC);

        String msg = f + "F -> " + expectedC + "C but was " + actualC + "C (delta " + delta + ")";
        assertTrue(msg, delta < 0.005);
    }

//    public final void testOnCreateOptionsMenu() {
//        int decimalPlaces = 5;
//        String numberRegEx = "^[0-9]+$";
//
//        solo.sendKey(Solo.MENU);
//        solo.clickOnText("Preferences");
//        solo.clickOnText("Decimal places");
//        assertTrue(solo.searchText(numberRegEx));
//        solo.clearEditText(DECIMAL_PLACES);
//        assertFalse(solo.searchText(numberRE));
//        solo.enterText(DECIMAL_PLACES, Integer.toString(decimalPlaces));
//        solo.clickOnButton("OK");
//        solo.goBack();
//
//        solo.sendKey(Solo.MENU);
//        solo.clickOnText("Preferences");
//        solo.clickOnText("Decimal places");
//        assertTrue(solo.searchText(numberRegEx));
//        int editTextDecimalPlaces = Integer.parseInt(
//                solo.getEditText(DECIMAL_PLACES).getText().toString());
//        assertEquals(decimalPlaces, editTextDecimalPlaces);
//    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }
}
