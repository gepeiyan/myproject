package com.example.first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RatelistActivity extends AppCompatActivity implements Runnable {
    Handler handler;
    private static final String TAG = "list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_ratelist);
        ListView listView=findViewById (R.id.mylist3);
        Thread t = new Thread(this);
        t.start();
        handler = new Handler () {
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(
                            RatelistActivity.this,
                            android.R.layout.simple_list_item_1,
                            list2);
                    listView.setAdapter(adapter);

                }
                super.handleMessage(msg);
            }
        };


    }

    @Override
    public void run() {
        List<String> list1 = new ArrayList<String>();
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
                    list1.add(str + "=>" + val);
                    Log.i(TAG, str + "=>" + val);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "run:" + e.toString());
        }

        Message msg = handler.obtainMessage(7);
        msg.obj = list1;
        handler.sendMessage(msg);
    }
}