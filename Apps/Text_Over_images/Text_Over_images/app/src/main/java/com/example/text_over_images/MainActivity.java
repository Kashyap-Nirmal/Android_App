package com.example.text_over_images;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        b = findViewById(R.id.button4);
        b.setWidth(width / 2);
        b.setOnClickListener(this);
        b = findViewById(R.id.button5);
        b.setOnClickListener(this);
        b.setWidth(width / 2);
    }

    @Override
    public void onClick(View v) {
        String msg = "";
        switch (v.getId()) {
            case R.id.button4:
                msg = "dir";
                break;

            case R.id.button5:
                msg = "img";
                break;
        }
        Intent i = new Intent(MainActivity.this, imageview.class);
        i.putExtra("Action", msg);
        startActivity(i);
    }
    public void  onBackPressed(){
        finishAffinity();
    }
}
