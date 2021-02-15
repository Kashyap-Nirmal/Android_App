package com.example.ui_widgets4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv,tv1,tv2;
    static String currentrating="",currentprogress="",currentprogress1="";
    Intent i = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView1);
        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
    }

    public void porgressBarfun(View view) {
        Intent i = new Intent(MainActivity.this, progressdialog.class);
        startActivity(i);
    }

    public void ratingBarfun(View view) {
        Intent i = new Intent(MainActivity.this, ratingbar.class);
        startActivity(i);
    }

    public void progressBar(View view) {
        Intent i = new Intent(MainActivity.this, progressbar.class);
        startActivity(i);
    }

    public void progressBar1(View view) {
        Intent i = new Intent(MainActivity.this, progressbarindet.class);
        startActivity(i);
    }

    protected void onResume() {
        i=getIntent();
        super.onResume();
        if(i!=null)
        {
            if(i.hasExtra("rating") && i.getStringExtra("rating")!=null)
            {
                currentrating=i.getStringExtra("rating");
            }
            if(i.hasExtra("progress") && i.getStringExtra("progress")!=null)
            {
                currentprogress=i.getStringExtra("progress");
            }
            if(i.hasExtra("progress1") && i.getStringExtra("progress1")!=null)
            {
                currentprogress1=i.getStringExtra("progress1");
            }
        }
        else{
            Toast toast=Toast.makeText(getApplicationContext(),"Null intent",Toast.LENGTH_SHORT);
            toast.show();
        }
        tv.setText(currentrating);
        tv1.setText(currentprogress);
        tv2.setText(currentprogress1);
    }
    public void onBackPressed(){
        finishAffinity();
        finish();
        Toast toast=Toast.makeText(getApplicationContext(),"Exitted app.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
