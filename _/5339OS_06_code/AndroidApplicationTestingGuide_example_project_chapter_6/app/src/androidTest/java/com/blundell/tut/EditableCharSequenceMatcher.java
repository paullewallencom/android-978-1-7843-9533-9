package com.blundell.tut;

import android.text.Editable;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.argThat;

class EditableCharSequenceMatcher extends ArgumentMatcher<Editable> {

    private final CharSequence expected;

    EditableCharSequenceMatcher(CharSequence expected) {
        this.expected = expected;
    }

    static Editable editableCharSequenceEq(CharSequence expected) {
        return argThat(new EditableCharSequenceMatcher(expected));
    }

    @Override
    public boolean matches(Object actual) {
        return expected.toString().equals(actual.toString());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(expected.toString());
    }
}
