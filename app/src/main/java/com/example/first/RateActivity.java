package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class RateActivity extends AppCompatActivity {
    float rate = 0f;
    String TAG = "rateCalc";
    EditText inp;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_rate);
        String title = getIntent ().getStringExtra ("title");
        rate = getIntent ().getFloatExtra ("rate", 0f);
        Log.i (TAG, "title=" + title);
        Log.i (TAG, "rate=" + rate);
        ((TextView) findViewById (R.id.title2)).setText (title);
        inp = (EditText) findViewById (R.id.inp2);
        inp.addTextChangedListener (watcher);
        show = (TextView) findViewById(R.id.result2);
    }

    TextWatcher watcher = new TextWatcher () {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i ("beforeTextChanged", s.toString ());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i ("onTextChanged", s.toString ());
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.i ("afterTextChanged", s.toString ());
            String str = s.toString();
            float r = 0;
            if (str != null && str.length() > 0) {
                r = Float.parseFloat(str);
                r = r * (100/rate);
                show.setText(String.valueOf(r));
            }
        }
    };


}