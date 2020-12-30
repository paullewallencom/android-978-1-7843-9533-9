package com.blundell.tut;

import android.test.AndroidTestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AccessPrivateDataTest extends AndroidTestCase {

    public void testAccessAnotherAppsPrivateDataIsNotPossible() {
        String filesDirectory = getContext().getFilesDir().getPath();
        String privateFilePath = filesDirectory + "/data/com.android.cts.appwithdata/private_file.txt";
        try {
            new FileInputStream(privateFilePath);
            fail("Was able to access another app's private data");
        } catch (FileNotFoundException e) {
            // expected
        }
    }
}
