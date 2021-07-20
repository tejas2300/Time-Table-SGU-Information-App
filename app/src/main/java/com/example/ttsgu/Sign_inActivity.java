package com.example.ttsgu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Sign_inActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FirebaseAuth firebaseAuth;
    Button btn_regester;
    EditText sname,usname,txtEmail, txtPassword, txtConfirmpassword,phoneNo;
    String year,email,name,uname,password,confirmPassword,phone,div;
    int spYear,spDiv;
    private Dialog loadingDialog;


    //    String text;
    FirebaseDatabase rootNode;
    DatabaseReference referencce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        btn_regester = findViewById(R.id.reg);
        txtEmail = findViewById(R.id.txtEmail);
        sname = findViewById(R.id.fname);
        usname = findViewById(R.id.username);
        txtPassword = findViewById(R.id.txtPass);
        phoneNo = findViewById(R.id.phoneNo);
        txtConfirmpassword = findViewById(R.id.txtconPass);


        Spinner spinner1 = findViewById(R.id.spinner1);
        Spinner spinner2 = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.year, R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Div, R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);


        firebaseAuth = FirebaseAuth.getInstance();

        closekeyboard();

        btn_regester.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadingDialog = new Dialog(Sign_inActivity.this);
                loadingDialog.setContentView(R.layout.loading_progressbar);
                loadingDialog.setCancelable(false);
                Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
                loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingDialog.show();


                rootNode = FirebaseDatabase.getInstance();
                referencce = rootNode.getReference();
                //getall Values
                spYear = spinner1.getSelectedItemPosition() + 1;
                spDiv = spinner2.getSelectedItemPosition();
                year = String.valueOf(spinner1.getSelectedItem());
                div = String.valueOf(spinner2.getSelectedItem());
                email = txtEmail.getText().toString().trim();
                name = sname.getText().toString().trim();
                uname = usname.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                confirmPassword = txtConfirmpassword.getText().toString().trim();
                phone = phoneNo.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Sign_inActivity.this, "Please Enter Email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Sign_inActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPassword)) {
                    Toast.makeText(Sign_inActivity.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 3) {
                    Toast.makeText(Sign_inActivity.this, "Password is too short ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spYear >= 2 && spYear >=1){


                if (password.equals(confirmPassword) ) {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Sign_inActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        sendUserData();
                                        Toast.makeText(Sign_inActivity.this, "Registration successfull", Toast.LENGTH_SHORT).show();
                                        loadingDialog.cancel();
                                        firebaseAuth.signOut();
                                        finish();
                                        Intent intent = new Intent(Sign_inActivity.this, Log_inActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Sign_inActivity.this, "you are already user please login", Toast.LENGTH_SHORT).show();
                                        loadingDialog.cancel();
                                    }

                                }

                            });
                } else {
                    Toast.makeText(Sign_inActivity.this, "Password Does not match ", Toast.LENGTH_SHORT).show();
                    loadingDialog.cancel();

                }
            }else {
                    Toast.makeText(Sign_inActivity.this, "Carefully Select Your Year And Branch", Toast.LENGTH_SHORT).show();
                loadingDialog.cancel();
                }
            }
        });

    }

    private void closekeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        text =  parent.getItemAtPosition(position).toString();
//
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void sendUserData(){
        FirebaseDatabase firebaseDatabase =FirebaseDatabase .getInstance();
        DatabaseReference databaseReference =firebaseDatabase.getReference("User").child(firebaseAuth.getCurrentUser().getUid());
        DatabaseReference databaseReference1 =firebaseDatabase.getReference("User1").child(""+spYear).child(firebaseAuth.getCurrentUser().getUid());
        userHelper helper=new userHelper(name,uname,email,password,phone,year,spYear,spDiv);

        databaseReference.setValue(helper);
        databaseReference1.setValue(helper);
    }

}
