//Reference- https://www.journaldev.com/13759/android-picasso-tutorial

package com.example.third_party_libraries;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Random;
import static android.graphics.Color.parseColor;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener{
    Button btn;
    CircleImageView imageView;
    String txt="Hello";
    TextDrawable drawable;
    String[] color={"#DD2C00","#D50000","#C6FF00","#0091EA","#6200EA","#FF6F00","#4FC3F7"};
    static int flag=0;
    static int flag1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        drawable= TextDrawable.builder().beginConfig().width(60).height(60).endConfig().
        buildRect(txt.charAt(0)+"", parseColor("#4A148C"));
        imageView =findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);
        btn=findViewById(R.id.Picasso);
        btn.setMinimumWidth(width/2);
        btn.setOnClickListener(this);
        btn=findViewById(R.id.Glide);
        btn.setMinimumWidth(width/2);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.Picasso:
                i=new Intent(MainActivity.this,Picasso1.class);
                startActivity(i);
                break;
            case R.id.Glide:
                i=new Intent(MainActivity.this,glide1.class);
                startActivity(i);
                break;
        }
    }
    public void generateFlag(){
        Random r=new Random();
        flag1=r.nextInt();
        flag1%=7;
        if(flag1<0)
            flag1+=7;
        if(flag1==flag)
            generateFlag();
    }
    public void onBackPressed(){
        generateFlag();
        drawable = TextDrawable.builder().beginConfig().width(60).height(60).endConfig().buildRect
        (txt.charAt(0)+"", parseColor(color[flag1]));
        imageView =findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);
    }
}
