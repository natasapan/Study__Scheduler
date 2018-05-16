package com.deitel.study__scheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natas on 4/19/2018.
 */

public class TaskListAdapter extends BaseAdapter {

   private Context mContext;
   private int mresource;


    public TaskListAdapter(Context context, int resource) {

        this.mContext = context;
        this.mresource = resource;
    }

    private final List<Task> mItems = new ArrayList<>();

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater lf = LayoutInflater.from(mContext);
        view = lf.inflate(mresource, viewGroup, false);





        return view;
    }
}
