package com.example.databases;

import android.content.Intent;  
import android.content.SharedPreferences;  
import android.preference.PreferenceManager;  
import android.support.v7.app.AppCompatActivity;  
import android.os.Bundle;  
import android.view.View;  
import android.widget.Button;  
import android.widget.TextView;  
  
public class Shared_Preferences extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    Button btn;
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.shared_preferences);
        btn = findViewById(R.id.storeinformation);
        btn.setOnClickListener(this);
        btn = findViewById(R.id.showinformation);
        btn.setOnClickListener(this);
        textView = findViewById(R.id.txtPrefs);
    }  
   
    private void displaySharedPreferences() {  
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Shared_Preferences.this);
        String username = prefs.getString("username", "Default NickName");  
        String passw = prefs.getString("password", "Default Password");  
        boolean checkBox = prefs.getBoolean("checkBox", false);  
        String listPrefs = prefs.getString("listpref", "Default list prefs");    
        StringBuilder builder = new StringBuilder();  
        builder.append("Username: " + username + "\n");  
        builder.append("Password: " + passw + "\n");  
        builder.append("Keep me logged in: " + checkBox + "\n");
        builder.append("List preference: " + listPrefs);  
        textView.setText(builder.toString());  
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.storeinformation:
            Intent intent = new Intent(Shared_Preferences.this,PrefsActivity.class);
            startActivity(intent);
            break;
        case R.id.showinformation:
            displaySharedPreferences();
            break;
        default:
            break;
        }
    }
}