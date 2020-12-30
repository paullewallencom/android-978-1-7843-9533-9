package com.blundell.tut;

import android.content.Intent;
import android.test.ServiceTestCase;

public class DummyServiceTest extends ServiceTestCase<DummyService> {

    public DummyServiceTest() {
        super(DummyService.class);
    }

    public void testBasicStartup() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), DummyService.class);
        startService(startIntent);
    }

    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), DummyService.class);
        bindService(startIntent);
    }
}
