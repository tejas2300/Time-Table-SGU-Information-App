package com.example.ttsgu;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Log_inActivity extends AppCompatActivity {
    public  Button start;
    Button login_btn;
    TextView reg_text;
    EditText txtEmail,txtPassword;
    private FirebaseAuth firebaseAuth;
    private Dialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login_btn=(Button) findViewById(R.id.login_btn);
        reg_text= (TextView) findViewById(R.id.reg_text);

        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPassword=(EditText)findViewById(R.id.txtPass);


        firebaseAuth = FirebaseAuth.getInstance();



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog=new Dialog(Log_inActivity.this);
                loadingDialog.setContentView(R.layout.loading_progressbar);
                loadingDialog.setCancelable(false);
                Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawableResource(R.drawable.progress_background);
                loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingDialog.show();

                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Log_inActivity.this, "Please Enter Email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Log_inActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 3) {
                    Toast.makeText(Log_inActivity.this, "Password is too short ", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Log_inActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Log_inActivity.this, "Your Successfully logged in ", Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(Log_inActivity.this, MainActivity.class);

                                    startActivity(intent);
                                    loadingDialog.cancel();
                                    Log_inActivity.this.finish();

                                } else {

                                    Toast.makeText(Log_inActivity.this, "Please Enter Valid Email addres and Password", Toast.LENGTH_SHORT).show();
                                    loadingDialog.cancel();
                                }

                            }
                        });


            }
        });

        reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Log_inActivity.this, Sign_inActivity.class);
                startActivity(intent);
            }
        });

    }


}