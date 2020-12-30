package com.blundell.tut;

import org.hamcrest.Description;
import org.mockito.ArgumentMatcher;

import static org.mockito.Matchers.argThat;

class CharSequenceMatcher extends ArgumentMatcher<CharSequence> {

    private final CharSequence expected;

    CharSequenceMatcher(CharSequence expected) {
        this.expected = expected;
    }

    static CharSequence charSequenceEq(CharSequence expected) {
        return argThat(new CharSequenceMatcher(expected));
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
