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
    private List<Task> mItems = new ArrayList<>();


    public TaskListAdapter(Context context) {


        this.mContext = context;
       
    }



    public void add(Task item) {


        mItems.add(item);
        notifyDataSetChanged();

    }

    public void clear() {

        mItems.clear();
        notifyDataSetChanged();
    }

    public void removeItem(int pos){
        mItems.remove(pos);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {

        return mItems.size();
    }

    @Override
    public Task getItem(int pos) {

        return mItems.get(pos);
    }

    @Override
    public long getItemId(int pos) {

        return pos;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_list_item, viewGroup,false);
        }

        return view;
    }
}
