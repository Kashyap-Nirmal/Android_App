//Implicit Intent
package com.example.intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public String url ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buttonclick(View view){
        EditText et=findViewById(R.id.edittext);
        url=et.getText().toString();
        if(url!=null && !url.isEmpty() && !url.equals("")){
            Toast toast=Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT);
            toast.show();
            if(!url.contains("https://"))
                url="https://"+url;
            Intent data= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            startActivity(data);
        }
        else{
            Toast toast1=Toast.makeText(getApplicationContext(),url+"Empty url",Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
}


