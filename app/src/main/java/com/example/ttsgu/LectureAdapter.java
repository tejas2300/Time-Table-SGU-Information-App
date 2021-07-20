package com.example.ttsgu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ttsgu.LectureModel;
import com.example.ttsgu.R;

import java.util.ArrayList;
import java.util.List;

public class LectureAdapter extends BaseAdapter {
//    private List<String> subList;
//    private List<String> facList;
//    private List<String> timeList;
//    private List<String> locationList;
//
//    public LectureAdapter(List<String> subList, List<String> facList, List<String> timeList, List<String> locationList) {
//        this.subList = subList;
//        this.facList = facList;
//        this.timeList = timeList;
//        this.locationList = locationList;
//    }

    LayoutInflater inflater;
    List<LectureModel> lectureModelList;

    public LectureAdapter(Context ctx, ArrayList<LectureModel> lectureModelArrayList) {
        inflater=LayoutInflater.from( ctx);
        this.lectureModelList = lectureModelArrayList;
    }

    @Override
    public int getCount() {
        return lectureModelList.size();
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
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tt_item, parent, false);

        } else {
            view = convertView;
        }

        ((TextView) view.findViewById(R.id.subject)).setText(lectureModelList.get(position).getS());
        ((TextView) view.findViewById(R.id.faculty)).setText(lectureModelList.get(position).getF());
        ((TextView) view.findViewById(R.id.time)).setText(lectureModelList.get(position).getT());
        ((TextView) view.findViewById(R.id.location)).setText(lectureModelList.get(position).getL());

        return view;
    }
}
