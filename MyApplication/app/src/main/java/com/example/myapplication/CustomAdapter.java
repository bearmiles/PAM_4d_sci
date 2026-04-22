package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<CustomItem> customItemList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<CustomItem> customItemList){
        this.context = context;
        this.customItemList = customItemList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return customItemList.size();
    }

    @Override
    public Object getItem(int position){
        return customItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();

            holder.radioGroup = convertView.findViewById(R.id.radioGroup);

            holder.textPytanie = convertView.findViewById(R.id.textPytanie);
            holder.radioOdpA = convertView.findViewById(R.id.radioOdpA);
            holder.radioOdpB = convertView.findViewById(R.id.radioOdpB);
            holder.radioOdpC = convertView.findViewById(R.id.radioOdpC);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CustomItem item = (CustomItem) getItem(position);

        holder.textPytanie.setText(item.getPytanie());
        holder.radioOdpA.setText(item.getOdpA());
        holder.radioOdpB.setText(item.getOdpB());
        holder.radioOdpC.setText(item.getOdpC());

        if (holder.radioGroup != null) {
            holder.radioGroup.setOnCheckedChangeListener(null);
            holder.radioGroup.clearCheck();
        }
        holder.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioOdpA) item.ZaznaczonaOdp = 1;
            else if (checkedId == R.id.radioOdpB) item.ZaznaczonaOdp = 2;
            else if (checkedId == R.id.radioOdpC) item.ZaznaczonaOdp = 3;
        });
        return convertView;
    }

    // Klasa ViewHolder musi istnieć!
    static class ViewHolder {
        TextView textPytanie;
        RadioButton radioOdpA;
        RadioButton radioOdpB;
        RadioButton radioOdpC;
        RadioGroup radioGroup;
    }
}