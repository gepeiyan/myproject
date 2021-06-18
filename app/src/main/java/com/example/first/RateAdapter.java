package com.example.first;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Map;

public class RateAdapter extends ArrayAdapter {

    public RateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RateItem>list) {
        super (context, resource);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Map<String,String> map = (Map<String, String>) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.itemTitle);
        TextView detail = (TextView) itemView.findViewById(R.id.item);

        title.setText("Title:" + map.get("ItemTitle"));
        detail.setText("detail:" + map.get("ItemDetail"));

        return itemView;
    }
}
