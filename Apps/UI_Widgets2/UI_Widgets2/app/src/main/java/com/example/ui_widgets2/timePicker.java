package com.example.ui_widgets2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class timePicker extends AppCompatActivity {
    TextView tv;
    String currentTime;
    TimePicker timepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);
        tv=findViewById(R.id.textView1);
        timepicker=findViewById(R.id.timePicker);
        currentTime="Selected Time: "+timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        tv.setText(currentTime);
    }

    public void changeTime(View view){
        currentTime="Selected Time: "+timepicker.getCurrentHour()+":"+timepicker.getCurrentMinute();
        tv.setText(currentTime);
    }
    public void onBackPressed(){
        Intent i = new Intent(timePicker.this, MainActivity.class);
        i.putExtra("time",currentTime);
        startActivity(i);
    }
}
