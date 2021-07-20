package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.ImageDecoder;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.davemorrissey.labs.subscaleview.decoder.DecoderFactory;
import com.davemorrissey.labs.subscaleview.decoder.ImageRegionDecoder;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Objects;

import static com.example.ttsgu.MainActivity.spins;

public class ImageActivity extends AppCompatActivity {

    ImageView img ;
    TextView txt;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
//        img = findViewById(R.id. fulimg);
        txt= findViewById(R.id.imgtext);
        PhotoView photoView = (PhotoView) findViewById(R.id.fulimg);

        myref= FirebaseDatabase.getInstance().getReference().child("Notice").child("Branch"+spins);


        String noticekey= getIntent().getStringExtra("NoticeKey");

        myref.child(noticekey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String title = Objects.requireNonNull(snapshot.child("title").getValue()).toString();
                    String imgurl = Objects.requireNonNull(snapshot.child("url").getValue()).toString();
//                    Picasso.get().load(imgurl).into(img);
                    Glide.with(photoView).load(imgurl).into(photoView);


                    txt.setText(title);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}