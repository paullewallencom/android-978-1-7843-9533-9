package com.blundell.tut;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This Activity requires some permissions just to be verified by tests:
 * <ul>
 * <li><code>android.permission.WRITE_EXTERNAL_STORAGE</code>
 * <li><code>android.permission.READ_CONTACTS</code>
 * <li><code>android.permission.WRITE_CONTACTS</code>
 * </ul>
 */
public class MyContactsActivity extends Activity {
    private static final String TAG = MyContactsActivity.class.getSimpleName();

    private static final boolean WRITE_EXTERNAL_FILE = true;
    private static final boolean START_CALL_ACTIVITY = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if (WRITE_EXTERNAL_FILE) {
            writeExternalFile();
        }
        if (START_CALL_ACTIVITY) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9115555"));
            startActivity(intent);
        }
    }

    private void writeExternalFile() {
        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir, "dummyName");
        Log.d(TAG, "file= " + file);
        BufferedOutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytesToWrite = "This is a sample".getBytes();
            os.write(bytesToWrite);
        } catch (FileNotFoundException e) {
            throw new SecurityException("Should have detected that android.permission.WRITE_EXTERNAL_STORAGE is required.");
        } catch (IOException e) {
            throw new SecurityException("Should have detected that android.permission.WRITE_EXTERNAL_STORAGE is required.");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    Log.e(TAG, "Could not close stream", e);
                }
            }
        }

    }
}
