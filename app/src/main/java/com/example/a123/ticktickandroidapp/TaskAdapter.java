package com.example.a123.ticktickandroidapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Activity context_1;
    private ArrayList<Task> pairs;


    public TaskAdapter(Activity context,
                             ArrayList<Task> pairs) {
        context_1 = context;
        this.pairs = pairs;
    }

    @Override
    public int getCount() {
        return pairs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecyclerView.ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context_1).inflate(
                    R.layout.item_list, null);

        }
        return null;
    }

}
