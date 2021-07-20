package com.example.ttsgu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity  {

    private ImageView appName;
    private EditText field;
    private Button start;


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appName=findViewById(R.id.Splash);

        Animation anim = AnimationUtils .loadAnimation(this,R.anim.myanim);
        appName.setAnimation(anim);
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(firebaseUser==null){

                    Intent intent =new Intent(SplashActivity.this, Log_inActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                Intent intent =new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                }
            }
        }).start();





    }





}
