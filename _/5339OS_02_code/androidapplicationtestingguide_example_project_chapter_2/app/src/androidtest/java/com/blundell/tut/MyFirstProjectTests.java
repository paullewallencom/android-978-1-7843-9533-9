package com.blundell.tut;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

public class MyFirstProjectTests extends TestCase {

    public MyFirstProjectTests() {
        this("MyFirstProjectTests");
    }

    public MyFirstProjectTests(String name) {
        super(name);
    }

    @SmallTest
    public void testSomething() {
        assertTrue(true);
    }

    @VeryImportantTest
    public void testOtherStuff() {
        assertTrue(true);
    }

    public void testShouldThrowException() {
        try {
            MyFirstProjectActivity.methodThatShouldThrowException();
            fail("Exception was not thrown");
        } catch (Exception ex) {
            // do nothing test has passed!
        }
    }

    public void testMax() {
        int a = 10;
        int b = 20;

        int actual = Math.max(a, b);

        String failMessage = "Expected " + b + " but was " + actual;
        assertEquals(failMessage, b, actual);
    }

}
