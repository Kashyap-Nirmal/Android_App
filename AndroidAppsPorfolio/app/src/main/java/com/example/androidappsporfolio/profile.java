package com.example.androidappsporfolio;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity implements View.OnClickListener{
    String pkg="",activity="";
    String info="Kashyap Nirmal\nB.Tech I.T. (4th Year)\nCHARUSAT University\nID : 16IT059\nApp Demo for Summer Internship";
    SpannableString content = new SpannableString("@ Akash Technolabs, Ahmedabad");
    Intent intent;
    public String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        TextView tv=findViewById(R.id.textView2);
        tv.setText(info);
        TextView tv1=findViewById(R.id.textView3);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tv1.setTextColor(Color.parseColor("#0000ff"));
        tv1.setText(content);
        tv1.setOnClickListener(this);
        Button btn;
        btn=findViewById(R.id.button2);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/2);
        btn=findViewById(R.id.button3);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/2);
    }
    @Override
    public void onBackPressed() {
        intent = new Intent(profile.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button2){
            intent = new Intent(profile.this,sms.class);
            startActivity(intent);
        } else if (v.getId()==R.id.button3){
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:+919427829341"));
            startActivity(intent);
        } else if (v.getId()==R.id.textView3){
            url="https://www.akashtechnolabs.com";
            intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            startActivity(intent);
        }
    }
}