package com.example.ui_widgets2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.DatePicker;

public class datePicker extends Activity {
    TextView tv;
    String currentDate;
    int month=0;
    DatePicker datepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);
        tv=findViewById(R.id.textView1);
        datepicker=findViewById(R.id.datePicker);
        month=datepicker.getMonth()+1;
        currentDate="Selected Date: "+datepicker.getDayOfMonth()+"/"+month+"/"+datepicker.getYear();
        tv.setText(currentDate);
    }

    public void changeDate(View view){
        month=datepicker.getMonth()+1;
        currentDate="Selected Date: "+datepicker.getDayOfMonth()+"/"+month+"/"+datepicker.getYear();
        tv.setText(currentDate);
    }
    public void onBackPressed(){
        Intent i = new Intent(datePicker.this, MainActivity.class);
        i.putExtra("date",currentDate);
        startActivity(i);
    }
}
