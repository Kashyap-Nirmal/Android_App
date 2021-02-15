package com.example.androidappsporfolio;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sms extends AppCompatActivity implements View.OnClickListener {
    EditText ev;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        btn=findViewById(R.id.button4);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        ev=findViewById(R.id.editText);
        SmsManager sms= SmsManager.getDefault();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
        sms.sendTextMessage("9427829341", null, ev.getText().toString(), pi, null);
        Toast.makeText(this, "SMS sent Successfully", Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed() {
        Intent intent = new Intent(sms.this,profile.class);
        startActivity(intent);
    }
}


