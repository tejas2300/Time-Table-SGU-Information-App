package com.example.ttsgu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.ttsgu.MainActivity.desc;

//
//public class DiptGridAdapter extends BaseAdapter {
//    Context context;
//    private List<String> deptList;
//    private List<String> images;
//
//    View view;
//    LayoutInflater layoutInflater;
//
//
//    public DiptGridAdapter(Context context ,List<String> deptList, String[] images) {
//        this.deptList = deptList;
//        this.context = context;
//        this.images = images;
//    }
////    public DiptGridAdapter(List<String> deptList List<>) {
////        this.deptList = deptList;
////    }
//
//
//    @Override
//    public int getCount() {
//        return deptList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if(convertView==null){
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout,parent,false);
//            view = new View(context);
//
//            ImageView imageView =view.findViewById(R.id.deptImg);
//            Glide.with(context).load(images[position]).into(imageView);
//            ((TextView) view.findViewById(R.id.cat_name)).setText(deptList.get(position));
//
//
//
//        }else {
//            view =convertView;
//        }
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(String.valueOf(desc).equals("1")){
//                Intent intent=new Intent(parent.getContext(), FacultyActivity.class);
//                intent.putExtra("CATAGORY",deptList.get(position));
//                intent.putExtra("DEPT_ID",position+1);
//                parent.getContext().startActivity(intent);
//            }else{
//                    Intent intent=new Intent(parent.getContext(), NoticeActivity.class);
//                    intent.putExtra("CATAGORY",deptList.get(position));
//                    intent.putExtra("DEPT_ID",position+1);
//                    parent.getContext().startActivity(intent);
//                }
//            }
//        });
//
////
////        Random rnd=new Random();
////        int color= Color.argb(255,rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255));
////        view.setBackgroundColor(color);
//
//        return view;
//
//    }
//}


public class DiptGridAdapter extends BaseAdapter {
    Context context;
    private List<String> deptList;
    private List<String> images;

    View view;
    LayoutInflater layoutInflater;

    public DiptGridAdapter(Context context ,List<String> deptList, List<String> images) {
        this.context = context;
        this.deptList = deptList;
        this.images = images;
    }

    @Override
    public int getCount() {
        return deptList.size();
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
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout,parent,false);
//            view = new View(context);
            ImageView imageView = view.findViewById(R.id.deptImg);
            TextView textView = view.findViewById(R.id.cat_name);
            Glide.with(context).load(images.get(position)).into(imageView);
            textView.setText(deptList.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(String.valueOf(desc).equals("1")){
                        Intent intent=new Intent(parent.getContext(), FacultyActivity.class);
                        intent.putExtra("CATAGORY",deptList.get(position));
                        intent.putExtra("DEPT_ID",position+1);
                        parent.getContext().startActivity(intent);
                    }else{
                        Intent intent=new Intent(parent.getContext(), NoticeActivity.class);
                        intent.putExtra("CATAGORY",deptList.get(position));
                        intent.putExtra("DEPT_ID",position+1);
                        parent.getContext().startActivity(intent);
                    }
                }
            });
        }

        return view;
    }
}