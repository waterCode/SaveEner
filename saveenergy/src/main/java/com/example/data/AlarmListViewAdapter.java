package com.example.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.Model.Alarm;

import java.util.ArrayList;

/**
 * Created by mc on 16-4-17.
 */
public class AlarmListViewAdapter extends ArrayAdapter<Alarm> {


    public AlarmListViewAdapter(Context context, int resource, int textViewResourceId, Alarm[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
