package com.blundell.tut;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;

public class ForwardingActivityTest extends ActivityUnitTestCase<ForwardingActivity> {

    private Intent startIntent;

    public ForwardingActivityTest() {
        super(ForwardingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getContext();
        startIntent = new Intent(context, ForwardingActivity.class);
    }

    public void testLaunchingSubActivityFiresIntentAndFinishesSelf() {
        Activity activity = startActivity(startIntent, null, null);
        View button = activity.findViewById(R.id.forwarding_go_button);

        button.performClick();

        assertNotNull(getStartedActivityIntent());
        assertTrue(isFinishCalled());
    }

    public void testExampleOfLifeCycleCreation() {
        Activity activity = startActivity(startIntent, null, null);

        // At this point, onCreate() has been called, but nothing else
        // so we complete the startup of the activity
        getInstrumentation().callActivityOnStart(activity);
        getInstrumentation().callActivityOnResume(activity);

        // At this point you could test for various configuration aspects
        // or you could use a Mock Context to confirm that your activity has made
        // certain calls to the system and set itself up properly.

        getInstrumentation().callActivityOnPause(activity);

        // At this point you could confirm that the activity has paused properly,
        // as if it is no longer the topmost activity on screen.

        getInstrumentation().callActivityOnStop(activity);

        // At this point, you could confirm that the activity has shut itself down appropriately,
        // or you could use a Mock Context to confirm that your activity has released any
        // system resources it should no longer be holding.

        // ActivityUnitTestCase.tearDown() is always automatically called and will take care of calling onDestroy().
    }

}
