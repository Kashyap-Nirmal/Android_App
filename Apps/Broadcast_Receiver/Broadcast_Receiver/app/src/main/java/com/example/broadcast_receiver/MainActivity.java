package com.example.broadcast_receiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void broadcastIntent(View view){
        Intent intent=new Intent();
        intent.setAction("com.example.broadcast.custom_intent");
        intent.putExtra("HighScore",1000);
        sendBroadcast(intent);
    }
}
