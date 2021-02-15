package com.example.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.hasExtra("HighScore") && intent.getIntExtra("HighScore",0)!=0)
            Toast.makeText(context,""+intent.getIntExtra("HighScore",0), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"Not found", Toast.LENGTH_LONG).show();
    }
}
