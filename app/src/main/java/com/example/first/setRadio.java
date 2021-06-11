package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class setRadio extends AppCompatActivity {
    EditText dol;
    EditText eur;
    EditText won;
    public Object Bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_radio);
        Intent intent = getIntent();
        float dollar2 = intent.getFloatExtra("dol_key", 0.0f);
        float euro2 = intent.getFloatExtra("eur_key", 0.0f);
        float won2 = intent.getFloatExtra("won_key", 0.0f);

        Log.i("set", "onCreate:dollar2=" + dollar2);
        Log.i("set", "onCreate:euro2=" + euro2);
        Log.i("set", "onCreate:won2=" + won2);
        EditText dol=(EditText)findViewById(R.id.dol);
        EditText eur=(EditText)findViewById(R.id.eur);
        EditText won=(EditText)findViewById(R.id.won);
        dol.setText(String.valueOf(dollar2));
        eur.setText(String.valueOf(euro2));
        won.setText(String.valueOf(won2));
    }

public void sendMessage(View btn){

    if (dol.length() > 0 && eur.length() > 0 && won.length() > 0) {
        String dol1 = dol.getText().toString();
        String eur1 = eur.getText().toString();
        String won1 = won.getText().toString();
        float dol_back = Float.parseFloat(dol1);
        float eur_back = Float.parseFloat(eur1);
        float won_back = Float.parseFloat(won1);
        SharedPreferences sp =getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor =sp.edit();
        editor.putFloat("dol_back", dol_back);
        editor.putFloat("eur_back", eur_back);
        editor.putFloat("won_back", won_back);
        editor.commit();
        Intent intent = getIntent();
        Bundle bdl = new Bundle();
        bdl.putFloat("dol_back", dol_back);
        bdl.putFloat("eur_back", eur_back);
        bdl.putFloat("won_back", won_back);
        intent.putExtras(bdl);
        setResult(2, intent);


        finish();
    }
    else{
        Toast.makeText(this, "请输入汇率", Toast.LENGTH_SHORT).show();
    }
}
}