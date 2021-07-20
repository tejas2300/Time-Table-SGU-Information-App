package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.ttsgu.MainActivity.spdiv;
import static com.example.ttsgu.MainActivity.spins;
//import static com.example.ttsgu.SyDivisionActivity.divsc;

public class DayActivity extends AppCompatActivity {

    private GridView day_grid;
    private FirebaseFirestore firestore;
    public static int category_id;
    public static int spNo1;
    public   List<String> dayList =new ArrayList<>();
    private Dialog loadingDialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);


        Toolbar toolbar =findViewById(R.id.setToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Week");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        day_grid=findViewById(R.id.setsGridView);
        firestore=FirebaseFirestore.getInstance();


        loadingDialog=new Dialog(DayActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        loadSets();


    }

    public void loadSets() {
        firestore.collection("TimeTable").document("Branch"+String.valueOf(spins+"."+spdiv))


                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    assert doc != null;
                    if (doc.exists()) {
                        long days = (long) doc.get("COUNT");
//                        String names=(String) doc.get("names");
                        for (int i = 1; i <= days; i++) {
                            String catName = doc.getString("Day"+ String.valueOf(i));
                            dayList.add(catName);
//                            Log.d("tejas", "DocumentSnapshot added with ID: " + task);

                        }
//

                        DayAdapter adapter = new DayAdapter(dayList);
                        day_grid.setAdapter(adapter);

                    } else {
                        Toast.makeText(DayActivity.this, "NO  Document Exists!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(DayActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
                loadingDialog.cancel();
            }
        });
            
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            DayActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}