package com.example.first;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
public class task extends ArrayAdapter {

    public task(@NonNull Context context, int resource,@NonNull ArrayList<HashMap<String,String>>list) {
        super (context, resource,list);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView=convertView;
        if(itemView==null){
            itemView= LayoutInflater.from(getContext ()).inflate(R.layout.list_item,parent,false);
        }
        Map<String,String> map=(Map<String, String>)getItem (position);
        TextView title=(TextView)itemView.findViewById(R.id.itemTitle);
        TextView detail=(TextView)itemView.findViewById(R.id.item);
        title.setText ("T:"+map.get("ItemTitle"));
        detail.setText ("d:"+map.get("ItemDetail"));

        return itemView;
    }

}
