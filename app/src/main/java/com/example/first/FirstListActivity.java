package com.example.first;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstListActivity extends ListActivity implements Runnable {
    Handler handler;

    private static final String TAG = "list";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread t = new Thread(this);
        t.start();
        handler = new Handler () {
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(
                            FirstListActivity.this,
                            android.R.layout.simple_list_item_1,
                            list2);
                    setListAdapter(adapter);

                }
                super.handleMessage(msg);
            }
        };

    }
    public void run() {
        List<String> list1 = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG, "run:title=" + doc.title());
            Element table = doc.getElementsByTag("table").get(1);
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
