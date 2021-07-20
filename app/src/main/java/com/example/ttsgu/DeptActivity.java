package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class DeptActivity extends AppCompatActivity {

    private GridView dept_grid;
    private FirebaseFirestore firestore;
    public List<String> deptList =new ArrayList<>();
    public List<String> deptImgList =new ArrayList<>();
    private Dialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        dept_grid=findViewById(R.id.Dept_GridView);
        firestore=FirebaseFirestore.getInstance();
        Toolbar toolbar =findViewById(R.id.setToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Departments");


        loadingDialog=new Dialog(DeptActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

//        deptList.add("CSE");
//
//        DiptGridAdapter adapter = new DiptGridAdapter(deptList);
//
//        dept_grid.setAdapter(adapter);
//
//        loadingDialog.cancel();

        loadSets();


    }







    public void loadSets() {
        firestore.collection("TimeTable").document("Branches")
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
                            String catName = doc.getString("Branch"+ String.valueOf(i));
                            String imageslist = doc.getString("T"+ String.valueOf(i));

                            deptList.add(catName);
                            deptImgList.add(imageslist);
//                            Log.d("tejas", "DocumentSnapshot added with ID: " + task);

                        }
//

                        DiptGridAdapter adapter = new DiptGridAdapter(getApplicationContext(),deptList,deptImgList);
                        dept_grid.setAdapter(adapter);

                    } else {
                        Toast.makeText(DeptActivity.this, "NO  Document Exists!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(DeptActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
                loadingDialog.cancel();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            DeptActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}