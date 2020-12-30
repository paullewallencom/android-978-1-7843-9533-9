package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MockContextExampleActivity extends Activity {

    private static final String FILE_NAME = "my_file.txt";

    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_context_example);

        textView = (TextView) findViewById(R.id.mock_text_view);
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            textView.setText(convertStreamToString(fis));
        } catch (FileNotFoundException e) {
            textView.setText("File not found");
        }
    }

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public String getText() {
        return textView.getText().toString();
    }
}
