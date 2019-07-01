package com.example.cfs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class message extends AppCompatActivity {
EditText e1,e2;
Button b1;
FirebaseAuth firebaseAuth;
    private DatabaseReference userRef;
    private FirebaseUser mCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        e1 = (EditText)findViewById(R.id.phoneNum);
        e2 = (EditText)findViewById(R.id.msg);
        b1 = (Button)findViewById(R.id.sendMsgBtn);
        e1.setEnabled(false);
        e2.setEnabled(false);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = e2.getText().toString().trim();
                String phn = e1.getText().toString().trim();
                phn = "03319047361";
                msg = "I Recently ordered! Please Check DataBase to Confirm My order...";
                try{
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("smsto:"));
                    i.setType("vnd.android-dir/mms-sms");
                    i.putExtra("address", phn);
                    i.putExtra("sms_body",msg);
                    startActivity(Intent.createChooser(i, "Send sms via:"));
                }
                catch(Exception e){
                    Toast.makeText(message.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                }

//                String e = e1.getText().toString().trim();
//                try{
//                    SmsManager smgr = SmsManager.getDefault();
//                    smgr.sendTextMessage(e,null,e2.getText().toString(),null,null);
//                    Toast.makeText(message.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e){
//                    Toast.makeText(message.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}
