package com.example.explicit_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity{
    static int flag=0;
    public static String msg = "ActivityTwo class Invoked.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitytwo);
        Intent i = getIntent();
        if(i.hasExtra("Key1") && i.getIntExtra("Key1",-1)!=0)
        {
            flag = i.getIntExtra("Key1",-1);
            if(flag==1)
                msg = "ActivityTwo class Invoked again.";
        }
        TextView text=findViewById(R.id.text1);
        text.setText(msg);
    }
    public void buttonclick1(View view){
	    Intent i = new Intent(ActivityTwo.this, ActivityThree.class);
        i.putExtra("Key2",flag);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        return ;
    }
}


