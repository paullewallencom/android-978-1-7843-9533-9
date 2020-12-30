package com.blundell.tut;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.RenamingDelegatingContext;

/**
 * This test will fail, unless you follow the chapter instructions and create the test txt file
 */
public class MockContextExampleTest extends ActivityUnitTestCase<MockContextExampleActivity> {

    private static final String PREFIX = "test.";
    private RenamingDelegatingContext mockContext;

    public MockContextExampleTest() {
        super(MockContextExampleActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockContext = new RenamingDelegatingContext(getInstrumentation().getTargetContext(), PREFIX);
        mockContext.makeExistingFilesAndDbsAccessible();
    }

    public void testSampleTextDisplayed() {
        setActivityContext(mockContext);

        startActivity(new Intent(), null, null);

        assertEquals("This is *MOCK* data\n", getActivity().getText());
    }
}
