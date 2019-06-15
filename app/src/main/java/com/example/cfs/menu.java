package com.example.cfs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class menu extends AppCompatActivity {
private Button fries,brgr,pizza,drink;
    Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pizza = (Button)findViewById(R.id.pizzabtn);
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),pizza.class);
                startActivity(intent);
            }
        });

        log = (Button)findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back);
                finish();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(menu.this, "SignOut Successfull", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
