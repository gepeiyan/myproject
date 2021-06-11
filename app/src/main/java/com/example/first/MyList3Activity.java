package com.example.first;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyList3Activity extends ListActivity implements Runnable, AdapterView.OnItemClickListener {
    Handler handler;
    ListView listView;
    SimpleAdapter listItemAdapter;


    private static final String TAG = "list";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_list3);
        listView = findViewById (R.id.mylist3);
        //getListView ();
        ProgressBar progressBar = findViewById (R.id.progressBar);
        Thread t = new Thread (this);
        t.start ();

        ;
        handler = new Handler () {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@Nullable Message msg) {
                if (msg.what == 7){
                    List<HashMap<String,String>> list2 = (List<HashMap<String,String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(MyList3Activity.this,list2,
                            R.layout.list_item,
                            new String[]{"ItemTitle","ItemDetail"},
                            new int[]{R.id.itemTitle,R.id.item});
                    listView.setAdapter (listItemAdapter);
                    progressBar.setVisibility (View.GONE);
                    listView.setVisibility (View.VISIBLE);

                }
                super.handleMessage (msg);

            }
        };
        listView.setOnItemClickListener (this);
    }
    public void run() {
        List<HashMap<String,String>> list1 = new ArrayList<HashMap<String,String>> ();
        try {
            Thread.sleep (1000);
            Document doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/").get();
            Log.i(TAG, "run:title=" + doc.title());
            Element table = doc.getElementsByTag("table").get(1);
            Elements trs = table.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() > 0) {
                    String str = tds.get(0).text();
                    String val = tds.get(5).text();
                    HashMap<String,String> map=new HashMap<String, String> ();
                    map.put("ItemTitle",str);
                    map.put("ItemDetail",val);
                    list1.add(map);
                    Log.i(TAG, str + "=>" + val);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "run:" + e.toString());
        }

        Message msg = handler.obtainMessage(7);
        msg.obj = list1;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView title=(TextView)view.findViewById (R.id.itemTitle);
        TextView detail=(TextView)view.findViewById (R.id.item);
        String title2=String.valueOf (title.getText());
        String detail2=String.valueOf (detail.getText());
        Intent rate=new Intent(this,RateActivity.class);
        rate.putExtra ("title",title2);
        rate.putExtra ("rate",Float.parseFloat (detail2));
        startActivity (rate);
         // adapter.remove(title);



    }

}
