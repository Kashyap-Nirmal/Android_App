//Reference- https://firebase.google.com/docs/firestore/query-data/get-data

package com.example.firestore_demo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.client.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    Intent i;
    Button b;
    EditText et1,et2;
    String uname,password;
    FirebaseFirestore db;
    static int count=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(MainActivity.this);
        count++;
        b=findViewById(R.id.button);
        b.setOnClickListener(this);
        textView=findViewById(R.id.textView);
        textView.setTextColor(Color.parseColor("#FFAB40"));
        Intent i=getIntent();
        if(i.hasExtra("Activity") && i.hasExtra("Status") && i.hasExtra("emsg")){
            if(i.getStringExtra("Activity")=="sign_up" && i.getStringExtra("emsg")!="Noerror" || "Noerror".equals(i.getStringExtra("emsg"))){
                b.setVisibility(View.INVISIBLE);
                textView.setTextColor(Color.parseColor("#d50000"));
                textView.setText("You are registered.\nPlease login now.");
                textView.append("Status = "+i.getIntExtra("Status",-1)+"\nError Message = "+i.getStringExtra("emsg"));
            }
            else
                textView.setText("Status = "+i.getIntExtra("Status",-1)+"\nError Message = "+i.getStringExtra("emsg"));
        }
        else if(i.hasExtra("Activity") || i.hasExtra("Status") || i.hasExtra("esmg"))
            if(i.getStringExtra("Activity")=="sign_up" && i.getIntExtra("Status",-1)!=1){
                textView.setText("New User ID not inserted.\nError Message = "+i.hasExtra("emsg"));
                textView.append("Status = "+i.getIntExtra("Status",-1)+"\nError Message = "+i.getStringExtra("emsg"));
            }
        else if(i.hasExtra("Activity") || i.hasExtra("Status") || i.hasExtra("esmg"))
                if(i.getStringExtra("Activity")=="sign_up")
                    textView.setText("Inside else.");
        b=findViewById(R.id.button2);
        b.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button :
                i=new Intent(MainActivity.this,sign_up.class);
                break;
            case R.id.button2 :
                    i=new Intent(MainActivity.this,sign_in.class);
                break;
        }
        et1=findViewById(R.id.editText);
        uname=et1.getText().toString();
        et2=findViewById(R.id.editText2);
        password=et2.getText().toString();
        if(uname.isEmpty() || password.isEmpty()){
            et1.setText("");
            et2.setText("");
            textView.setText("One of the *Required fields in empty.\nPlease enter the necessary fields");
        }
        else{
            i.putExtra("Uname",uname);
            i.putExtra("password",password);
            i.putExtra("Count",count);
            startActivity(i);
        }
    }
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}