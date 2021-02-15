package com.example.third_party_libraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import de.hdodenhof.circleimageview.CircleImageView;

public class circleimageview extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circleimageview1);
        CircleImageView setupImage=findViewById(R.id.imageView);
        setupImage.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Profile Pic clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
