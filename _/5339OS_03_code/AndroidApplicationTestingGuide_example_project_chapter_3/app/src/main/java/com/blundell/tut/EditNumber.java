package com.blundell.tut;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

public class EditNumber extends EditText {

    private static final String DEFAULT_FORMAT = "%.2f";

    public EditNumber(Context context) {
        super(context);
        init();
    }

    public EditNumber(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditNumber(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // DigistKeyListener.getInstance(true, true) returns an instance that accepts digits, sign and decimal point
        InputFilter[] filters = new InputFilter[]{DigitsKeyListener.getInstance(true, true)};
        setFilters(filters);
    }

    public void clear() {
        setText("");
    }

    public double getNumber() {
        String number = getText().toString();
        Log.d("EditNumber", "getNumber() returning value of '" + number + "'");
        if (TextUtils.isEmpty(number)) {
            return 0D;
        }
        return Double.valueOf(number);
    }

    public void setNumber(double f) {
        super.setText(String.format(DEFAULT_FORMAT, f));
    }

}
