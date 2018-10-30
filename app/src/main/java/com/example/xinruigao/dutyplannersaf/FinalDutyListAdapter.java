package com.example.xinruigao.dutyplannersaf;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FinalDutyListAdapter extends ArrayAdapter<String> {
    public FinalDutyListAdapter(@NonNull Context context, @NonNull List<String> soldierNames) {
        super(context, 0, soldierNames);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        String soldierName = getItem(position); //get the corresponding feed based on its position
        Log.v("SOLDIERNAMES",soldierName);
        if (convertView == null){ //means we haven't passed a recycled view yet
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.soldier_name_row, parent, false);
            //if no view that is recycled, create a new view from layout inflater
            //first parameter means cell that is created for the edit feed rss row
        }
        TextView soldierNameTextView = (TextView) convertView.findViewById(R.id.soldierNameTextView);

        //convert view is used to recycle old views
        soldierNameTextView.setText(soldierName);

        return convertView;
    }
}
