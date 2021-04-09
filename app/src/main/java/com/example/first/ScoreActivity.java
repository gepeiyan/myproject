package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private static final String TAG = "Scoreactivity";
    int score1 =0;
    int score2 =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
    private void show(){
        Log.i(TAG,"show:score"+score1);
        TextView show =findViewById(R.id.zero);
        show.setText(String.valueOf(score1));
    }

    public void btn3(View v){
        score1+=3;
        show();
    }
}