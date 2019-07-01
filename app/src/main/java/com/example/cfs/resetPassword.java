package com.example.cfs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPassword extends AppCompatActivity {
EditText e1;
Button b1;
private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        e1 =(EditText)findViewById(R.id.sendEmail);
        b1 = (Button)findViewById(R.id.sendEmailbtn);
        mAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = e1.getText().toString().trim();
                if(TextUtils.isEmpty(userEmail)){
                    Toast.makeText(resetPassword.this, "Please Input valid Email...", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(resetPassword.this, "Please Check your email to reset Password...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(resetPassword.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(resetPassword.this, "ERROR: "+ error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
