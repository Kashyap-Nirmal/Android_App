package com.example.third_party_libraries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class Picasso1 extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picasso1);

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

        btn=findViewById(R.id.btnCallBack);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnResize);
        btn.setOnClickListener(this);
        btn.setMinimumWidth(width/3);

        btn=findViewById(R.id.btnRotate);
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
        iv=findViewById(R.id.imageView);
        TextView tv;
        tv=findViewById(R.id.textView);
        String msg="";
        int i=2;
        URL url;
        switch(v.getId()){
            case R.id.btnDrawable:
                Picasso.with(this).load(R.drawable.screenshot).into(iv);
                msg="btnDrawable";
                break;
            case R.id.btnPlaceholder:
                Picasso.with(this).load("www.journaldev.com").placeholder(R.drawable.index3).into(iv);
                msg="btnPlaceHolder";
                break;
            case R.id.btnUrl:
                Picasso.with(this).load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").placeholder(R.drawable.index2).into(iv);
                msg="btnUrl";
                break;
            case R.id.btnError:
                Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(iv);
                msg="btnError";
                break;
            case R.id.btnCallBack:
                msg="btnCallBack";
                Picasso.with(this).load("www.journaldev.com").error(R.mipmap.ic_launcher).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("TAG", "onSuccess");
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnResize:
                Picasso.with(this).load(R.drawable.index).resize(200, 200).into(iv);
                msg="btnResize";
                break;
            case R.id.btnRotate:
                msg="btnRotate";
                Picasso.with(this).load(R.drawable.index).rotate(90f).into(iv);
                break;
            case R.id.btnScale:
                msg="btnScale";
                if (i == 3)
                    i = 0;

                else {
                    if (i == 0) {
                        Picasso.with(this).load(R.drawable.index).fit().into(iv);
                        Toast.makeText(getApplicationContext(), "Fit", Toast.LENGTH_SHORT).show();
                    } else if (i == 1) {
                        Picasso.with(this).load(R.drawable.index).resize(200, 200).centerCrop().into(iv);
                        Toast.makeText(getApplicationContext(), "Center Crop", Toast.LENGTH_SHORT).show();
                    } else if (i == 2) {
                        Picasso.with(this).load(R.drawable.index).resize(200, 200).centerInside().into(iv);
                        Toast.makeText(getApplicationContext(), "Center Inside", Toast.LENGTH_SHORT).show();
                    }
                    i++;
                }
                break;
            case R.id.btnTarget:
                msg="btnTarget";
                Picasso.with(this).load("http://cdn.journaldev.com/wp-content/uploads/2017/01/android-constraint-layout-sdk-tool-install.png").placeholder(R.drawable.index).error(R.drawable.index4).into(iv);
                break;
            default:
                tv.setText("OnClick invoked default "+v.getId());
                break;
        }
        tv.setText(msg);
    }
}
