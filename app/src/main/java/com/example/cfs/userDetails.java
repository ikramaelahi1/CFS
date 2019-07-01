package com.example.cfs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class userDetails extends AppCompatActivity {
EditText e1,e2,e3,e4,e5,e6;
Button b1;
private FirebaseAuth firebaseAuth;
    private DatabaseReference userRef;
    private FirebaseUser mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        e1 = (EditText)findViewById(R.id.name);
        e2 = (EditText)findViewById(R.id.address);
        e3 = (EditText)findViewById(R.id.phone);
        e4 = (EditText)findViewById(R.id.city);
        e5 = (EditText)findViewById(R.id.qty);
        e6 = (EditText)findViewById(R.id.dish);
        b1 = (Button)findViewById(R.id.confirmbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        final String currentUser = mCurrentUser.getUid();
        userRef = FirebaseDatabase.getInstance().getReference().child("user Details:");
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    String name1 = e1.getText().toString().trim();
                    String address1 = e2.getText().toString().trim();
                    String city1 = e4.getText().toString().trim();
                    String phone1 = e3.getText().toString().trim();
                    String quanty1 = e5.getText().toString().trim();
                    String dish1 = e6.getText().toString().trim();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("name: ", name1);
                    hashMap.put("Address: ", address1);
                    hashMap.put("City :", city1);
                    hashMap.put("Phone :", phone1);
                    hashMap.put("Dish :", dish1);
                    hashMap.put("Quantity :", quanty1);
                    userRef.setValue(hashMap);
                    Toast.makeText(userDetails.this, "Data Saved Successfully!...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(userDetails.this, message.class);
                    startActivity(intent);

            }
        });
    }
}
