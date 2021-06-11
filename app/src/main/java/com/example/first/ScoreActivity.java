package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView scorea;
    TextView score;
    private static final String TAG = "Selectivity";
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score=(TextView)findViewById(R.id.score);
        scorea=(TextView)findViewById(R.id.scorea);
    }
    private void showScorea(int inc){
        Log.i("show","inc="+inc);
        String oldScorea =(String)scorea.getText();
        int newScorea=Integer.parseInt(oldScorea)+inc;

        scorea.setText(""+newScorea);
    }
    private void showScore(int inc){
        Log.i("show","inc="+inc);
        String oldScore =(String)score.getText();
        int newScore=Integer.parseInt(oldScore)+inc;

        score.setText(""+newScore);
    }

    public void btnAdd3a(View v){
        showScorea(3);
    }
    public void btnAdd2a(View v){
        showScorea(2);
    }
    public void btnAdd1a(View v){
        showScorea(1);
    }
    public void btnAdd3(View v){
        showScore(3);
    }
    public void btnAdd2(View v){
       showScore(2);
    }
    public void btnAdd1(View v){
        showScore(1);
    }
   
    public void btnReset(View btn){
        score.setText("0");
        scorea.setText("0");
    }


}