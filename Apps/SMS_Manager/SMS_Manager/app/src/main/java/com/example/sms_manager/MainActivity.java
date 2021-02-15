package com.example.sms_manager;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSend(View view){
        EditText ev1,ev2;
        ev1=findViewById(R.id.editText);
        ev2=findViewById(R.id.editText1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Requested not granted for Phone State",
                    Toast.LENGTH_LONG).show();

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.
                    permission.SEND_SMS}, 1);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Requested already granted for .SEND_SMS",
                    Toast.LENGTH_LONG).show();

        }
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms= SmsManager.getDefault();
        sms.sendTextMessage(ev1.getText().toString(), null, ev2.getText().toString(), pi,
        null);
        Toast.makeText(getApplicationContext(), "Message Sent successfully!",Toast.LENGTH_LONG)
        .show();
    }
}
