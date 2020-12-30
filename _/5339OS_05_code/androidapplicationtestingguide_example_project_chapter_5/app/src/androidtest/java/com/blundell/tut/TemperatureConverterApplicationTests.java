package com.blundell.tut;

import android.test.ApplicationTestCase;

public class TemperatureConverterApplicationTests extends ApplicationTestCase<TemperatureConverterApplication> {

    public TemperatureConverterApplicationTests() {
        this("TemperatureConverterApplicationTests");
    }

    public TemperatureConverterApplicationTests(String name) {
        super(TemperatureConverterApplication.class);
        setName(name);
    }

    public void testSetAndRetrieveDecimalPlaces() {
        RenamingMockContext mockContext = new RenamingMockContext(getContext());
        setContext(mockContext);
        createApplication();
        TemperatureConverterApplication application = getApplication();

        application.setDecimalPlaces(3);

        assertEquals(3, application.getDecimalPlaces());
    }
}
