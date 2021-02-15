package com.example.third_party_libraries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;

public class glide1 extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.glide1);
        imageView = findViewById(R.id.imageView);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        btn=findViewById(R.id.btnDrawable);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnPlaceholder);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnUrl);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnError);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.CircleImageView);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnCallBack);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnResize);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn =findViewById(R.id.btnScale);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnTarget);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

    }

    @Override
    public void onClick(View v) {
        TextView tv;
        tv=findViewById(R.id.textView);
        String msg="";
        int i=1;
        switch(v.getId()) {
            case R.id.btnUrl:
                Glide.with(this).load("http://via.placeholder.com/300.png").into(imageView);
                msg = "btnURL";
                break;
            case R.id.btnDrawable:
                Glide.with(this).load(R.drawable.screenshot).into(imageView);
                msg="btnDrawable";
                break;
            case R.id.btnPlaceholder:
                Glide.with(this).load("www.journaldev.com").placeholder(R.drawable.index3).into(imageView);
                msg="btnPlaceHolder";
                break;
            case R.id.btnError:
                Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(imageView);
                msg="btnError";
                break;
            case R.id.CircleImageView:
                Intent i1=new Intent(glide1.this,circleimageview.class);
                startActivity(i1);
                //setContentView(R.layout.circleimageview1);
                break;
            case R.id.btnCallBack:
                msg="btnCallBack";
                Glide.with(this).load("www.journaldev.com").error(R.mipmap.ic_launcher).into(imageView);
                break;
            case R.id.btnResize:
                Glide.with(this).load(R.drawable.index).override(200, 200).into(imageView);
                msg="btnResize";
                break;
            case R.id.btnScale:
                msg="btnScale";
                if (i == 3)
                    i = 0;

                else {
                    if (i == 0) {
                        Glide.with(this).load(R.drawable.index).override(200,200).fitCenter().into(imageView);
                        Toast.makeText(getApplicationContext(), "Fit", Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        Glide.with(this).load(R.drawable.index).override(200, 200).centerCrop().into(imageView);
                        Toast.makeText(getApplicationContext(), "Center Crop", Toast.LENGTH_SHORT).show();
                    }
                    i++;
                }
                break;
            case R.id.btnTarget:
                msg="btnTarget";
                Glide.with(this).load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").placeholder(R.drawable.index).error(R.drawable.index4).into(imageView);
                break;
            default:
                tv.setText("OnClick invoked default "+v.getId());
                break;
        }
        tv.setText(msg);
    }
}
