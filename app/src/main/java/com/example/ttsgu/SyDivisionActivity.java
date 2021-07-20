package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SyDivisionActivity extends AppCompatActivity {
    private TextView TYA,TYB;
    public static String disc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sy_division);
        Toolbar toolbar =findViewById(R.id.setToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TYA=findViewById(R.id.sya);
        TYB=findViewById(R.id.syb);
        TYA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disc="2";
                Intent intent=new Intent(SyDivisionActivity.this, DayActivity.class);
                startActivity(intent);
            }
        });
        TYB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disc="2.2";
                Intent intent=new Intent(SyDivisionActivity.this, DayActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            SyDivisionActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}