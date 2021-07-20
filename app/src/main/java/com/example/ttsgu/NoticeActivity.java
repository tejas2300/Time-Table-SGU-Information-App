package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.ttsgu.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.viven.imagezoom.ImageZoomHelper;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.ttsgu.MainActivity.spins;

public class    NoticeActivity extends AppCompatActivity {

    private RecyclerView noticerv;
    private Dialog loadingDialog;
    FirebaseRecyclerOptions<NoticeModel> options;
    FirebaseRecyclerAdapter<NoticeModel, MyViewHolder>adapter;
    DatabaseReference myref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Notice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noticerv = findViewById(R.id.noticrv);
        noticerv.setLayoutManager(new LinearLayoutManager(this));

        myref= FirebaseDatabase.getInstance().getReference().child("Notice").child("Branch"+spins);
        loadingDialog = new Dialog(NoticeActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        loadingDialog.show();


        LoadNotice();



    }

    public void LoadNotice() {

        options = new FirebaseRecyclerOptions.Builder<NoticeModel>()
                   .setQuery(myref,NoticeModel.class).build();
        adapter=new FirebaseRecyclerAdapter<NoticeModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull NoticeModel model) {
                holder.textView.setText(model.getTitle());
                Picasso.get().load(model.getUrl()).into(holder.imageView);
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent =new Intent(NoticeActivity.this,ImageActivity.class);
                        intent.putExtra("NoticeKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_item,parent,false);

                return new MyViewHolder(view);
            }
        };
        adapter.startListening();
        noticerv.setAdapter(adapter);
        loadingDialog.cancel();


    }

}
