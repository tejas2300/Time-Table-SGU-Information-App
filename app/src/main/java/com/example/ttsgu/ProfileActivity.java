package com.example.ttsgu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView email,prn,name;
    Button lgbtn;
    FirebaseDatabase firebaseDatabase;
    String userId;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email =findViewById(R.id.emailTV);
        prn =findViewById(R.id.prnNoTV);
        name =findViewById(R.id.nameTv);
        lgbtn=findViewById(R.id.lgoutbtn);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        reff=firebaseDatabase.getReference("User").child(firebaseAuth.getCurrentUser().getUid());



        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Email=String.valueOf(snapshot.child("email").getValue());
                String Name =String.valueOf(snapshot.child("name").getValue());
                String Phone =String.valueOf(snapshot.child("phoneNo").getValue());
                userHelper userhelper =snapshot.getValue(userHelper.class);
//                userhelper
//                assert userhelper != null;
//                name.setText(userhelper.getName());

//                Log.d("Tejasa", "onDataChange: "+userhelper.getName());
                Toast.makeText(ProfileActivity.this, "Email"+Email, Toast.LENGTH_SHORT).show();

//
                name.setText(Name);
                email.setText(Email);
//                prn.setText(userHelper.getPhoneNo());
//                Toast.makeText(ProfileActivity.this, "email"+userHelper.getEmail(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ProfileActivity.this, error.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

//        email.setText(firebaseUser.getEmail());




        lgbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
               Intent intent= new Intent(ProfileActivity.this, Log_inActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


    }
}