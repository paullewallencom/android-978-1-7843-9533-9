package com.blundell.tut;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Window;
import android.view.WindowManager;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

public class ExampleEspressoTest extends ActivityInstrumentationTestCase2<EspressoActivity> {

    public ExampleEspressoTest() {
        super(EspressoActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        Activity activity = getActivity();
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
    }

    public void testClickingButtonShowsImage() {
        onView(withId(R.id.espresso_button_order))
                .perform(click());

        onView(withId(R.id.espresso_imageview_cup))
                .check(matches(isDisplayed()));
    }

}
