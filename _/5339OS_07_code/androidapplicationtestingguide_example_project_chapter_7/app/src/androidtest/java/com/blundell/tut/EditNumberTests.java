package com.blundell.tut;

import android.test.AndroidTestCase;
import android.text.TextWatcher;

import static com.blundell.tut.CharSequenceMatcher.charSequenceEq;
import static com.blundell.tut.EditableCharSequenceMatcher.editableCharSequenceEq;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class EditNumberTests extends AndroidTestCase {

    private EditNumber editNumber;

    public EditNumberTests() {
        this("EditNumberTests");
    }

    public EditNumberTests(String name) {
        setName(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        editNumber = new EditNumber(mContext);
        editNumber.setFocusable(true);
    }

    public void testClear() {
        String value = "123.45";
        editNumber.setText(value);

        editNumber.clear();

        assertEquals("", editNumber.getText().toString());
    }

    public void testSetNumber() {

        editNumber.setNumber(123.45);

        assertEquals("123.45", editNumber.getText().toString());
    }

    public void testGetNumber() {

        editNumber.setNumber(123.45);

        assertEquals(123.45, editNumber.getNumber());
    }

    public void testTextChangedFilterWorksForBlankInput() {
        assertEditNumberTextChangeFilter("", "");
    }

    public void testTextChangedFilterWorksForSingleDigitInput() {
        assertEditNumberTextChangeFilter("1", "1");
    }

    public void testTextChangedFilterWorksForMultipleDigitInput() {
        assertEditNumberTextChangeFilter("123", "123");
    }

    public void testTextChangedFilterWorksForZeroInput() {
        assertEditNumberTextChangeFilter("0", "0");
    }

    public void testTextChangedFilterWorksForDecimalInput() {
        assertEditNumberTextChangeFilter("1.2", "1.2");
    }

    public void testTextChangedFilterWorksForNegativeInput() {
        assertEditNumberTextChangeFilter("-1.2", "-1.2");
    }

    public void testTextChangedFilterWorksForDashedInput() {
        assertEditNumberTextChangeFilter("1-2-3", "123");
    }

    public void testTextChangedFilterWorksForPositiveInput() {
        assertEditNumberTextChangeFilter("+1", "+1");
    }

    public void testTextChangedFilterWorksForCharacterInput() {
        assertEditNumberTextChangeFilter("A1A", "1");
    }

    public void testTextChangedFilterWorksForDoubleDecimalInput() {
        assertEditNumberTextChangeFilter("1.2.3", "12.3");
    }

    /**
     * @param input  the text to be filtered by {@link EditNumber}
     * @param output the result you expect once the {@param input} has been filtered
     */
    private void assertEditNumberTextChangeFilter(String input, String output) {
        int lengthAfter = output.length();
        TextWatcher mockTextWatcher = mock(TextWatcher.class);
        editNumber.addTextChangedListener(mockTextWatcher);

        editNumber.setText(input);

        verify(mockTextWatcher).afterTextChanged(editableCharSequenceEq(output));
        verify(mockTextWatcher).onTextChanged(charSequenceEq(output), eq(0), eq(0), eq(lengthAfter));
        verify(mockTextWatcher).beforeTextChanged(charSequenceEq(""), eq(0), eq(0), eq(lengthAfter));
    }
}
