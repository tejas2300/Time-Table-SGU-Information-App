package com.example.ttsgu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DivisionActivity extends AppCompatActivity {

    private TextView TYA,TYB;
    public static String divsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);
        Toolbar toolbar =findViewById(R.id.setToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TYA=findViewById(R.id.tya);
        TYB=findViewById(R.id.tyb);

        TYA=findViewById(R.id.tya);
        TYB=findViewById(R.id.tyb);

        TYA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                divsc="1";
                Intent intent=new Intent(DivisionActivity.this, DayActivity.class);
                startActivity(intent);
            }
        });
        TYB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                divsc="1.1";
                Intent intent=new Intent(DivisionActivity.this, DayActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            DivisionActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}