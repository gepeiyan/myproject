package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    TextView show;
    EditText inp;
    TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        EditText inp = findViewById(R.id.inp);
        String a = inp.getText().toString();
        double a2 = Double.parseDouble(a);
        double b = a2 * 1.8 + 32;
        TextView out = findViewById(R.id.opt);
        out.setText("转换为华氏度是" + b + "度");
    }
}
