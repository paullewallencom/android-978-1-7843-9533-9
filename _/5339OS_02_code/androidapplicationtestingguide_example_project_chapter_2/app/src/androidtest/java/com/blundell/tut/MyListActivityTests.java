package com.blundell.tut;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.ListView;

public class MyListActivityTests extends ActivityInstrumentationTestCase2<MyListActivity> {

    private MyListActivity activity;
    private ListView listView;

    public MyListActivityTests() {
        this("MyListActivityTests");
    }

    public MyListActivityTests(String name) {
        super(MyListActivity.class);
        setName(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        listView = activity.getListView();
    }

    public void testListAdapterItemsLoaded() {
        int expectedItemPosition = 6;
        String expected = "Anguilla";

        String actual = listView.getAdapter().getItem(expectedItemPosition).toString();

        assertEquals("Wrong content", actual, expected);
    }

    public void testListScrolling() {
        listView.scrollTo(0, 0);

        TouchUtils.dragQuarterScreenUp(this, activity);
        int actualItemPosition = listView.getFirstVisiblePosition();

        assertTrue("Wrong position", actualItemPosition > 0);
    }
}
