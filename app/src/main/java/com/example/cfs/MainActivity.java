package com.example.cfs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   public EditText ed1,ed2;
    Button b1,b2;
    TextView tx1;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //{ID's}
        ed1 = (EditText)findViewById(R.id.email);
        ed2 = (EditText)findViewById(R.id.password);
        b1= (Button)findViewById(R.id.login);
        b2= (Button)findViewById(R.id.registration);
        tx1 = (TextView)findViewById(R.id.register);
        //{ID's}
        firebaseAuth= FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                String pass = ed2.getText().toString();
                {
                    firebaseAuth.signInWithEmailAndPassword(name, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Accepted Redirecting...", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), menu.class);
                                startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Wrong Credentials...", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    ;
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Redirected to Form...", Toast.LENGTH_SHORT).show();
                Intent inten = new Intent(getApplicationContext(),regForm.class);
                startActivity(inten);
            }
        });

    }
}