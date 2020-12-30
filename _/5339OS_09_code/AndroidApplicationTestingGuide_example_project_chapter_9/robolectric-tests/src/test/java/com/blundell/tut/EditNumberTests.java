package com.blundell.tut;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class EditNumberTests {

    private static final double DELTA = 0.00001d;

    private EditNumber editNumber;

    @Before
    public void setUp() throws Exception {
        editNumber = new EditNumber(Robolectric.application);
        editNumber.setFocusable(true);
    }

    @Test
    public final void testClear() {
        String value = "123.45";
        editNumber.setText(value);

        editNumber.clear();

        assertEquals("", editNumber.getText().toString());
    }

    @Test
    public final void testSetNumber() {
        editNumber.setNumber(123.45);

        assertEquals("123.45", editNumber.getText().toString());
    }

    @Test
    public final void testGetNumber() {
        editNumber.setNumber(123.45);

        assertEquals(123.45, editNumber.getNumber(), DELTA);
    }

}
