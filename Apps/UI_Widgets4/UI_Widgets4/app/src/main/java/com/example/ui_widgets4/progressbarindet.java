package com.example.ui_widgets4;

import android.widget.ProgressBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class progressbarindet extends AppCompatActivity{
    TextView tv;
    ProgressBar progressBar;
    String progressBarSt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbarindet);
        tv = findViewById(R.id.textView1);
        progressBarSt="indeterminate Progress bar : Stopped";
        progressBar=findViewById(R.id.progressBar2);
        tv.setText(progressBarSt);
    }
    public void stop(View view) {
        progressBar.setVisibility(View.INVISIBLE);
        progressBarSt="indeterminate Progress bar : Stopped";
        tv.setText(progressBarSt);
    }

    public void onBackPressed(){
        Intent i = new Intent(progressbarindet.this, MainActivity.class);
        startActivity(i);
    }

    public void progress(View view) {
        progressBar.setVisibility(View.VISIBLE);
        progressBarSt="indeterminate Progress bar : Started";
        tv.setText(progressBarSt);
    }
}
