package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MonkeyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey);

        final EditText inputText = (EditText) findViewById(R.id.monkey_toast_input);
        View toastButton = findViewById(R.id.monkey_toast_button);
        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MonkeyActivity.this, inputText.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
