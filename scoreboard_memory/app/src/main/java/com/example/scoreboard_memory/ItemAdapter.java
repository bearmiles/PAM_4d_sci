package com.example.scoreboard_memory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context, List<Item> items){
        this.context = context;
        this.items = items;
    }


    @Override public int getCount(){ return items.size(); }
    @Override public Object getItem(int position){ return items.get(position); }
    @Override public long getItemId(int position) { return position; }

    @Override public View getView(int position, View contextView, ViewGroup parent) {
        if (contextView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            contextView = inflater.inflate(R.layout.custom_item, parent, false);
        }

        TextView userId = contextView.findViewById(R.id.user_id);
        userId.setText(String.valueOf(items.get(position).user_id));

        TextView nazwa = contextView.findViewById(R.id.username);
        nazwa.setText(String.valueOf(items.get(position).username));

        TextView wynik = contextView.findViewById(R.id.score);
        wynik.setText(String.valueOf(items.get(position).score));

        return contextView;
    }
}



