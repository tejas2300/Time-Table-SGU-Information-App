package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class FacultyActivity extends AppCompatActivity {

    private RecyclerView facrv;
    FacultyAdapter facultyAdapter;
    private Dialog loadingDialog;
    int dept_no;

    String dept_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        Toolbar toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        facrv = findViewById(R.id.facrv);
        facrv.setLayoutManager(new LinearLayoutManager(this));
        dept_no=getIntent().getIntExtra("DEPT_ID",1);
        dept_name=getIntent().getStringExtra("CATAGORY");
        Objects.requireNonNull(getSupportActionBar()).setTitle(dept_name);

        loadingDialog=new Dialog(FacultyActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        loadingDialog.show()  ;

        Loadfaculty();



    }
public  void Loadfaculty(){

    FirebaseRecyclerOptions<FacultyModel> options =
            new FirebaseRecyclerOptions.Builder<FacultyModel>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty").child("Branch"+String.valueOf(dept_no)),FacultyModel.class)
                    .build();


    facultyAdapter=new FacultyAdapter(options){
        @Override
        public void onDataChanged() {
           loadingDialog.cancel();
        }
    };
    facrv.setAdapter(facultyAdapter);



}
    @Override
    protected void onStop() {
        super.onStop();
        facultyAdapter.stopListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        facultyAdapter.startListening();

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            FacultyActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}