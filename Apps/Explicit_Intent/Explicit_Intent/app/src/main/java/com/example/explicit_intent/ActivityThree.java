package com.example.explicit_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityThree extends AppCompatActivity{
    static int flag=0;
    public static String msg = "ActivityThree class Invoked.";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitythree);
        Intent i = getIntent();
        if(i.hasExtra("Key2") && i.getIntExtra("Key2",-1)!=0)
        {
            flag = i.getIntExtra("Key2",-1);
            if(flag==1)
                msg = "ActivityThree class Invoked again.";
        }
        /*else if(!i.hasExtra("Key2") && flag==0)
            msg = "Application retarted.\nActivityThree class Invoked again. "+flag;*/
        TextView text=findViewById(R.id.text1);
        text.setText(msg);
    }

    public void buttonclick2(View view){
        Toast toast=Toast.makeText(getApplicationContext(),"Exitted app.",Toast.LENGTH_SHORT);
        toast.show();
        finishAffinity();
        finish();
    }
	public void buttonclick3(View view){
		Intent i = new Intent(ActivityThree.this,MainActivity.class);
		flag=1;
        i.putExtra("Key", flag);
        startActivity(i);
	}
    @Override
    public void onBackPressed() {
        buttonclick2(null);
    }
}


/*
    Bundle extras = getIntent().getExtras();
        if (extras != null)
            if(extras.getInt("Key2")!=0)
                flag=extras.getInt("Key2");
            else if(flag==0)
                msg="Application retarted.\nActivityThree class Invoked again. "+flag;
        if(flag==1)
            msg = "ActivityThree class Invoked again.";
 */