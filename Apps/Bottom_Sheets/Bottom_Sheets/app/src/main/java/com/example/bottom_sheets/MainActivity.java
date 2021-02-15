//Reference- https://www.youtube.com/watch?v=IfpRL2K1hJk

package com.example.bottom_sheets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=findViewById(R.id.btn_bottom_sheet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheets btmsht = new bottomsheets();
                btmsht.show(getSupportFragmentManager(),"bottomsheet");
            }
        });
    }
}