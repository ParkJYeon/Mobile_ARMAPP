package com.example.macaron.mobile_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.macaron.mobile_project.Class.ListViewItem;
import com.example.macaron.mobile_project.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{

    private ArrayList<ListViewItem> listViewItems = new ArrayList<ListViewItem>();

    public ListViewAdapter(){

    }

    @Override
    public int getCount() {
        return listViewItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView titleview = (TextView)convertView.findViewById(R.id.title_knew);

        ListViewItem listViewItem = listViewItems.get(position);

        titleview.setText(listViewItem.getTitleSrt());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItems.get(position);
    }

    public void addItem(String title){
        ListViewItem item = new ListViewItem();
        item.setTitleSrt(title);

        listViewItems.add(item);
    }

}
