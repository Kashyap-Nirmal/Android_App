//Reference- https://www.androidhive.info/2013/06/android-working-with-xml-animations/

package com.example.android_animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity  extends AppCompatActivity implements View.OnClickListener{
    Button btn;
    Animation anim;
    ImageView img;
    String msg="";
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView output = findViewById(R.id.textView);
                switch(v.getId()){
                    case R.id.blink:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
                        msg="Blink";
                    break;
                    case R.id.bounce:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
                        msg="Bounce";
                        break;
                    case R.id.fadein:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                        msg="FadeIN";
                        break;
                    case R.id.move:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
                        msg="Move";
                        break;
                    case R.id.rotate:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
                        msg="Rotate";
                        break;
                    case R.id.sequentialanimation:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequentialanimation);
                        msg="Sequantial Animation";
                        break;
                    case R.id.slidedown:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slidedown);
                        msg="SlideDown";
                        break;
                    case R.id.slideup:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideup);
                        msg="SlideUP";
                        break;
                    case R.id.zoomin:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
                        msg="ZoomIN";
                        break;
                    case R.id.zoomout:
                        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomout);
                        msg="ZoomOut";
                        break;
                    default:
                        anim=null;
                }
                img=findViewById(R.id.imageView);
                if(anim!=null)
                    img.startAnimation(anim);
                else
                    Toast.makeText(getApplicationContext(),"onCLick error",Toast.LENGTH_SHORT);
                output.setText(msg);
            }
        };

        btn=findViewById(R.id.blink);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.bounce);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.fadein);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.move);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.rotate);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.sequentialanimation);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.slidedown);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.slideup);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.zoomin);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
        btn=findViewById(R.id.zoomout);
        btn.setMinimumWidth(width/3);
        btn.setOnClickListener(onClickListener);
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getApplicationContext(),"onCLick",Toast.LENGTH_SHORT);
    }
}

