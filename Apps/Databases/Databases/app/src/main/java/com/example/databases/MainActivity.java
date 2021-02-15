//References- https://www.javatpoint.com/android-preferences-example
//References- https://www.javatpoint.com/android-sqlite-tutorial

package com.example.databases;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.shared_preferences);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.sqlite);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i ;
        if(v.getId() == R.id.shared_preferences)
            i=new Intent(MainActivity.this,Shared_Preferences.class);
        else
            i=new Intent(MainActivity.this,sqlite.class);
        startActivity(i);
    }
}