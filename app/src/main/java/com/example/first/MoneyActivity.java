package com.example.first;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MoneyActivity extends AppCompatActivity implements Runnable {
    private static final String TAG = "result";
    TextView show;
    EditText inp;
    float dollarRate = 0.1503f;
    float euroRate = 0.128f;
    float wonRate = 170.8f;
    Handler handler;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        inp = (EditText) findViewById(R.id.input);
        show = (TextView) findViewById(R.id.show);
        SharedPreferences sharedPreferences = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        PreferenceManager.getDefaultSharedPreferences(this);
        dollarRate = sharedPreferences.getFloat("dol_back", 0.0f);
        euroRate = sharedPreferences.getFloat("eur_back", 0.0f);
        wonRate = sharedPreferences.getFloat("won_back", 0.0f);
        String updateStr = sharedPreferences.getString("update_str", "");
        Log.i(TAG, "onCreate:dollar=" + dollarRate);
        LocalDate today = LocalDate.now();
        if (updateStr.equals(today.toString())) {
            Log.i(TAG, "onCreate:日期相等，不更新");
        } else {
            Thread t = new Thread(this);
            t.start();
        }


        handler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(@Nullable Message msg) {
                if (msg.what == 5) {
                    Bundle bdl = (Bundle) msg.obj;
                    dollarRate = bdl.getFloat("dollar");
                    euroRate = bdl.getFloat("euro");
                    wonRate = bdl.getFloat("won");
                    SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putFloat("dollar_rate", dollarRate);
                    editor.putFloat("euro_rate", euroRate);
                    editor.putFloat("won_rate", wonRate);
                    editor.putString("update_str", today.toString());
                    editor.apply();
                    Toast.makeText(MoneyActivity.this, "数据已更新", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "dollar" + dollarRate);
                    Log.i(TAG, "euro" + euroRate);
                    Log.i(TAG, "won" + wonRate);
                }
                super.handleMessage(msg);
            }
        };


    }

    public void Click(View btn) {
        String str = inp.getText().toString();
        float r = 0;
        if (str != null && str.length() > 0) {
            r = Float.parseFloat(str);
            if (btn.getId() == R.id.dol) {
                r = r * dollarRate;
                show.setText(String.valueOf(r));
            } else if (btn.getId() == R.id.eur) {
                r = r * euroRate;
                show.setText(String.valueOf(r));
            } else {
                r = r * wonRate;
                show.setText(String.valueOf(r));
            }
        } else {
            Toast.makeText(this, "请输入计算的金额", Toast.LENGTH_SHORT).show();
        }
    }

    public void openOne(View btn) {
        Log.i("open", "openOne");
        show();

    }

    private void show() {
        Intent conf = new Intent(this, setRadio.class);
        conf.putExtra("dol_key", dollarRate);
        conf.putExtra("eru_key", euroRate);
        conf.putExtra("won_key", wonRate);

        Log.i("open", "openOne:dollarRate=" + dollarRate);
        Log.i("open", "openOne:euroRate=" + euroRate);
        Log.i("open", "openOne:wonRate+" + wonRate);
        startActivityForResult(conf, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_setting) {
            show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 2) {
            Bundle bundle = data.getExtras();
            dollarRate = bundle.getFloat("dol_back", 0.1f);
            euroRate = bundle.getFloat("eur_back", 0.1f);
            wonRate = bundle.getFloat("won_back", 0.1f);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void run() {
       /* URL url =null;
        try{
            url =new URL("http://www.usd-cny.com/icbc.htm");
            HttpURLConnection http =(HttpURLConnection) url.openConnection();
            InputStream in =http.getInputStream();
            String html =inputStream2String(in);
            Log.i(TAG,"run:html="+html);
        }catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG, "run:ex="+e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"run:ex="+e.toString());
        }*/
        Message msg1 = handler.obtainMessage(6, "kkk");
        handler.sendMessage(msg1);

        Bundle bundle = new Bundle();
        try {
            Document doc = Jsoup.connect("http://www.usd-cny.com/bankofchina.htm").get();
            Log.i(TAG, "run:title=" + doc.title());
            Element table = doc.getElementsByTag("table").first();
            Elements trs = table.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() > 0) {
                    String str = tds.get(0).text();
                    String val = tds.get(5).text();
                    Log.i(TAG, str + "=>" + val);
                    if ("美元".equals(str)) {
                        float r = 100 / Float.parseFloat(val);
                        bundle.putFloat("dollar", r);
                    } else if ("欧元".equals(str)) {
                        float r = 100 / Float.parseFloat(val);
                        bundle.putFloat("euro", r);
                    } else if ("韩元".equals(str)) {
                        float r = 100 / Float.parseFloat(val);
                        bundle.putFloat("won", r);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "run:" + e.toString());
        }
        Message msg = handler.obtainMessage(5);
        msg.obj = bundle;
        handler.sendMessage(msg);
    }
}


