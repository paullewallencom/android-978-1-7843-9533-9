package com.blundell.tut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForwardingActivity extends Activity {

    private static final int GHOSTBUSTERS = 999121212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forwarding);
        View button = findViewById(R.id.forwarding_go_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("tel:" + GHOSTBUSTERS);
                startActivity(intent);
                finish();
            }
        });
    }
}
