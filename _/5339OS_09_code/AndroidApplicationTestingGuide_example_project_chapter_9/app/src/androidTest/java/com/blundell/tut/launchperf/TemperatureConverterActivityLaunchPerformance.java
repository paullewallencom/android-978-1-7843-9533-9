package com.blundell.tut.launchperf;

import android.app.Activity;
import android.os.Bundle;

import com.blundell.tut.BuildConfig;

public class TemperatureConverterActivityLaunchPerformance extends LaunchPerformanceBase {

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        String className = "com.blundell.tut.TemperatureConverterActivity";
        intent.setClassName(BuildConfig.APPLICATION_ID, className);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        launchApp();
        finish(Activity.RESULT_OK, results);
    }
}
