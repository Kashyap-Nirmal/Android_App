package com.example.explicit_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static int flag=0;
    public String msg = "MainActivity class Invoked by default.";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        if(i.hasExtra("Key") && i.getIntExtra("Key",-1)!=0)
        {
            flag = i.getIntExtra("Key",-1);
            if(flag==1)
                msg = "MainActivity class Invoked again.";
        }
        else if(!i.hasExtra("Key") && flag!=0)
            msg = "Application restarted after 'Exit' key pressed.\nMainActivity class Invoked again.\nExtras not found.";
        TextView text=findViewById(R.id.text1);
        text.setText(msg);
    }
    public void buttonclick(View view){
        Intent i = new Intent(MainActivity.this, ActivityTwo.class);
        i.putExtra("Key1",flag);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        return ;
    }
}

/*
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            if(extras.getInt("Key")!=0)
                flag=extras.getInt("Key");
            else{
                Toast toast;
                toast=Toast.makeText(getApplicationContext(),"Extras not found",Toast.LENGTH_SHORT);
                toast.show();
            }
        if(flag==1)
            msg = "MainActivity class Invoked again.";
        Log.d("explicit",msg);
*/
