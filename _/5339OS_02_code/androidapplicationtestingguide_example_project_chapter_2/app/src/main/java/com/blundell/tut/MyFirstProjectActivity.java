package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.blundell.dummylibrary.Dummy;

public class MyFirstProjectActivity extends Activity {
    private Dummy dummy;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText messageInput = (EditText) findViewById(R.id.main_input_message);
        Button capitalizeButton = (Button) findViewById(R.id.main_button_capitalize);
        capitalizeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = messageInput.getText().toString();
                messageInput.setText(input.toUpperCase());
            }
        });

        dummy = new Dummy();
    }

    public Dummy getDummy() {
        return dummy;
    }

    public static void methodThatShouldThrowException() throws Exception {
        throw new Exception("This is an exception");
    }

}
