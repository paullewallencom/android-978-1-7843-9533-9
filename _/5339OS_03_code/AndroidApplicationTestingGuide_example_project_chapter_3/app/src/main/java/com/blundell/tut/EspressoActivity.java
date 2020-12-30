package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class EspressoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso);

        final View cupImage = findViewById(R.id.espresso_imageview_cup);
        View orderButton = findViewById(R.id.espresso_button_order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cupImage.setVisibility(View.VISIBLE);
            }
        });
    }
}
