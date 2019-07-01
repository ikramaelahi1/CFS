package com.example.cfs;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class regForm extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText cpass;
    private Button b1;
    private EditText name , password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_form);
        firebaseAuth= FirebaseAuth.getInstance();
        b1 = (Button)findViewById(R.id.submit);
        name = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.createPass);
        cpass=(EditText)findViewById(R.id.createPass);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namefield = name.getText().toString().trim();
                String passfield = password.getText().toString().trim();
                firebaseAuth.createUserWithEmailAndPassword(namefield, passfield).addOnCompleteListener(regForm.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendEmailVerificationMessage();
                            finish();
                        } else {
                            Toast.makeText(regForm.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                }
        });

        }
    private void sendEmailVerificationMessage(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(regForm.this, "Registration Successfull: Now check and verify your account.", Toast.LENGTH_SHORT).show();
                        sendToLoginActivity();
                        firebaseAuth.signOut();
                    }else{
                        String error = task.getException().getMessage();
                        Toast.makeText(regForm.this, "ERROR: "+error, Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                    }
                }
            });
        }
    }
    private void sendToLoginActivity(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
    }


