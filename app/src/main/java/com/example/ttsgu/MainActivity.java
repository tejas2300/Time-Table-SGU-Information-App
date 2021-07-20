package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView timetable,faculty,notice;
    public static List<String> catList=new ArrayList<>();
    private FirebaseFirestore firestore;
    private  FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference reff;
    private Dialog loadingDialog;
    public static int  spins;
    public static int  spdiv;
    public static String branch;
    public static String desc;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CSE");
        timetable=findViewById(R.id.time_table_btn);
        faculty=findViewById(R.id.Faculty_btn);
        notice=findViewById(R.id.Notice_btn);
        firestore=FirebaseFirestore.getInstance();

        loadData();
        loadingDialog=new Dialog(MainActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();


        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DayActivity.class);
                startActivity(intent);
            }
        });
         faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                desc="1";
                Intent intent=new Intent(MainActivity.this, DeptActivity.class);
                startActivity(intent);
            }
        });
         notice.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 desc="2";
                 Intent intent=new Intent(MainActivity.this, NoticeActivity.class);
                 startActivity(intent);
             }
         });


    }


    private void loadData(){

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase= FirebaseDatabase.getInstance();
        reff=firebaseDatabase.getReference("User").child(firebaseAuth.getCurrentUser().getUid());

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Email=String.valueOf(snapshot.child("email").getValue());
                String Name =String.valueOf(snapshot.child("name").getValue());
                String Phone =String.valueOf(snapshot.child("phoneNo").getValue());
                branch =String.valueOf(snapshot.child("branch").getValue());
                userHelper userhelper =snapshot.getValue(userHelper.class);
                assert userhelper != null;
                spins=userhelper.getSpNo();
                spdiv=userhelper.getSp2no();

                loadingDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });





    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.log_out:
                showPopup();
                return true;
            case R.id.help:
                Intent intent2= new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent2);
                return true;

        }


        return super.onOptionsItemSelected(item);
    }
    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Are you sure, you want to logout ?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                });

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, Log_inActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater =getMenuInflater();
       inflater.inflate(R.menu.menu,menu);

        return true;
    }
}