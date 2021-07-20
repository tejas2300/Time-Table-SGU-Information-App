package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.ttsgu.MainActivity.spdiv;
import static com.example.ttsgu.MainActivity.spins;

public class ttActivity extends AppCompatActivity {
    private RecyclerView ttrv;
//    TextView subject,faculty,time,location;
    ArrayList<LectureModel> lectureModelList;
    private FirebaseFirestore firestore;
    private int DayNo;
    private  FirestoreRecyclerAdapter adapter;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        loadingDialog=new Dialog(ttActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();



        ttrv = findViewById(R.id.ttrv);
        firestore = FirebaseFirestore.getInstance();
        DayNo = getIntent().getIntExtra("DayNo", 1);

        Query query = firestore.collection("TimeTable").document("Branch" + String.valueOf(spins+"."+spdiv))
                .collection("Day" + String.valueOf(DayNo));


        FirestoreRecyclerOptions<LectureModel> options = new FirestoreRecyclerOptions.Builder<LectureModel>()
                .setQuery(query, LectureModel.class).build();

        adapter = new FirestoreRecyclerAdapter<LectureModel, LectureViewHolder>(options) {
            @NonNull
            @Override
            public LectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tt_item,parent,false);
                return new LectureViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull LectureViewHolder lectureViewHolder, int i, @NonNull LectureModel lectureModel) {
                lectureViewHolder.subject.setText(lectureModel.getS());
                lectureViewHolder.faculty.setText(lectureModel.getF());
                lectureViewHolder.time.setText(lectureModel.getT());
                lectureViewHolder.location.setText(lectureModel.getL());
            }
        };
        loadingDialog.cancel();

        ttrv.setHasFixedSize(true);
        ttrv.setLayoutManager(new LinearLayoutManager(this));
        ttrv.setAdapter(adapter);

        }
        private class LectureViewHolder extends RecyclerView.ViewHolder {

        private TextView subject;
        private TextView faculty;
        private TextView time;
        private TextView location;
            public LectureViewHolder(@NonNull View itemView) {
                super(itemView);
                subject=itemView.findViewById(R.id.subject);
                faculty=itemView.findViewById(R.id.faculty);
                time=itemView.findViewById(R.id.time);
                location=itemView.findViewById(R.id.location);
            }

        }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            ttActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


































//        List<String> subList =new ArrayList<>();
//        subList.add("IS");
//        subList.add("CD");
//        List<String> facList =new ArrayList<>();
//        facList.add("KMA");
//        facList.add("SAP");
//        List<String> timeList =new ArrayList<>();
//        timeList.add("10:45");
//        timeList.add("11:45");
//        List<String> locationList =new ArrayList<>();
//        locationList.add("BL1");
//        locationList.add("BL2");





//        lectureModelList=new ArrayList<>();
//        lectureModelList.add(new LectureModel("IS","KMA","10:45","BL1"));
//        lectureModelList.add(new LectureModel("CD","SAP","11:45","BL2"));
//        lectureModelList.add(new LectureModel("DAA","BAK","01:45","BL3"));


//        lectureModelList=new ArrayList<>();
//        firestore.collection("TimeTable").document("Branch"+String.valueOf(category_id))
//                .collection("Day"+String.valueOf(DayNo))
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    QuerySnapshot lectures = task.getResult();
//                    Log.d("loggggggggggggg",String.valueOf(lectures.size()));
//                    for (QueryDocumentSnapshot doc  :lectures) {
//                        lectureModelList.add(new LectureModel(doc.getString("Subject"),
//                                doc.getString("Faculty"),
//                                doc.getString("Time"),
//                                doc.getString("Location")
//
//                        ));
//
//                    }
//
////
////
//
//
//                } else {
//                    Toast.makeText(ttActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//        });
//
//
////getLecturelists();
//        LectureAdapter adapter =new LectureAdapter(this,lectureModelList);
//        ttGrid.setAdapter(adapter);
//
////        setLectures();
//
//
//
//    }
//
//    private void getLecturelists() {
//
//
//
//    }
//
//    private void setLectures() {
//                    subject.setText(lectureModelList.get(0).getSubject());
//                    faculty.setText(lectureModelList.get(0).getFaculty());
//                    time.setText(lectureModelList.get(0).getTime());
//                    location.setText(lectureModelList.get(0).getLocation());
//
//    }
//}
//
