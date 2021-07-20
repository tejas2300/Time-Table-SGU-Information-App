package com.example.ttsgu;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ttsgu.R;
import com.example.ttsgu.ttActivity;

import java.util.List;
import java.util.Random;

public class DayAdapter extends BaseAdapter {

    private int numOfsets;
    private List<String> dayList;
    public DayAdapter(List<String> dayList) {
        this.dayList = dayList;

    }


    @Override
    public int getCount() {
        return dayList.size();
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
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view;
        if(convertView==null){
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout,parent,false);

        }
        else{
            view=convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(parent.getContext(), ttActivity.class);
                intent.putExtra("DayNo",position+1);
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.set_no_tv)).setText(dayList.get(position));
//        view.findViewById(R.id.cdview);

//        Random rnd=new Random();
//        int color= Color.argb(255,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
//        view.setBackgroundColor(color);

        return view;
    }
}
