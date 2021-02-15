package com.example.ui_widgets2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv,tv1;
    static String currentTime="",currentDate="";
    Intent i = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView1);
        tv1 = findViewById(R.id.textView2);
    }

    @Override
    protected void onResume() {
        i=getIntent();
        super.onResume();
        if(i!=null)
        {
            if(i.hasExtra("time") && i.getStringExtra("time")!=null)
            {
                currentTime=i.getStringExtra("time");
            }
            if(i.hasExtra("date") && i.getStringExtra("date")!=null)
            {
                currentDate=i.getStringExtra("date");
            }
        }
        else{
            Toast toast=Toast.makeText(getApplicationContext(),"Null intent",Toast.LENGTH_SHORT);
            toast.show();
        }
        tv.setText(currentTime);
        tv1.setText(currentDate);
    }
     public void changeTime(View view){
        Intent i = new Intent(MainActivity.this, timePicker.class);
        startActivity(i);
    }
    public void changeDate(View view){
        Intent i = new Intent(MainActivity.this, datePicker.class);
        startActivity(i);
    }
    public void onBackPressed(){
        finishAffinity();
        finish();
        Toast toast=Toast.makeText(getApplicationContext(),"Exitted app.",Toast.LENGTH_SHORT);
        toast.show();
    }
}
