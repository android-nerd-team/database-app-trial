package com.example.recepinanc.simpledatabaseapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Recepinanc on 19.09.2015.
 */
public class MyListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Country> countryList;

    public MyListAdapter (Activity activity, List<Country> countries) {
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        countryList = countries;
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Object getItem(int position) {
        return countryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null)
            v = inflater.inflate(R.layout.listview_row,null);

        TextView textView = (TextView) v.findViewById(R.id.row_textview);

        Country country = countryList.get(position);

        textView.setText(country.getCountryName());

        return v;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        countryList.addAll(countryList);
    }
}
