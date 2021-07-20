package com.example.ttsgu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FacultyAdapter extends FirebaseRecyclerAdapter<FacultyModel,FacultyAdapter.MyViewHolder> {


    public FacultyAdapter(@NonNull FirebaseRecyclerOptions<FacultyModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull FacultyModel model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getPhone());
        Glide.with(holder.img.getContext()).load(model.getUrl()).fitCenter().dontAnimate().into(holder.img);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_fac_item, parent,false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView name,email,phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.Fac_image);
            name=itemView.findViewById(R.id.fac_name);
            email=itemView.findViewById(R.id.fac_mail);
            phone=itemView.findViewById(R.id.fac_contact);

        }
    }
}
