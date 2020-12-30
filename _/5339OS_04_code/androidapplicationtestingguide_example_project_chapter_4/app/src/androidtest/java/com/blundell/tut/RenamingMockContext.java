package com.blundell.tut;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.RenamingDelegatingContext;

public class RenamingMockContext extends RenamingDelegatingContext {

    private static final String PREFIX = "test.";

    public RenamingMockContext(Context context) {
        super(context, PREFIX);
    }

    @Override
    public SharedPreferences getSharedPreferences(String name, int mode) {
        return super.getSharedPreferences(PREFIX + name, mode);
    }
}
