package com.blundell.tut;

import android.content.Context;
import android.test.AndroidTestCase;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This exercises the same logic as {@link Focus2ActivityTest} but in
 * a lighter weight manner; it doesn't need to launch the activity,
 * and it can test the focus behavior by calling {@link FocusFinder}
 * methods directly.
 * <p/>
 * {@link Focus2ActivityTest} is still useful to verify that, at an
 * end to end level, key events actually translate to focus
 * transitioning in the way we expect.
 * A good complementary way to use both types of tests might be to
 * have more exhaustive coverage in the lighter weight test case,
 * and a few end to end scenarios in the functional {@link
 * android.test.ActivityInstrumentationTestCase}.
 * This would provide reasonable assurance that the end to end
 * system is working, while avoiding the overhead of
 * having every corner case exercised in the slower,
 * heavier weight way.
 * <p/>
 * Even as a lighter weight test, this test still needs access to a
 * {@link Context} to inflate the file, which is why it extends
 * {@link AndroidTestCase}.
 * <p/>
 */
public class FocusTest extends AndroidTestCase {
    private FocusFinder focusFinder;

    private ViewGroup layout;

    private Button leftButton;
    private Button centerButton;
    private Button rightButton;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        focusFinder = FocusFinder.getInstance();

        // inflate the layout
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (ViewGroup) inflater.inflate(R.layout.view_focus, null);

        // manually measure it, and lay it out
        layout.measure(500, 500);
        layout.layout(0, 0, 500, 500);

        leftButton = (Button) layout.findViewById(R.id.focus_left_button);
        centerButton = (Button) layout.findViewById(R.id.focus_center_button);
        rightButton = (Button) layout.findViewById(R.id.focus_right_button);
    }

    public void testGoingRightFromLeftButtonJumpsOverCenterToRight() {
        View actualNextButton = focusFinder.findNextFocus(layout, leftButton, View.FOCUS_RIGHT);
        String msg = "right should be next focus from left";
        assertEquals(msg, this.rightButton, actualNextButton);
    }

    public void testGoingLeftFromRightButtonGoesToCenter() {
        View actualNextButton = focusFinder.findNextFocus(layout, rightButton, View.FOCUS_LEFT);
        String msg = "center should be next focus from right";
        assertEquals(msg, this.centerButton, actualNextButton);
    }
}
