package com.example.fragment;

import android.os.Bundle;
import android.app.Fragment;
// import android.view.LayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
// import android.view.ViewGroup;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.*;
import android.app.Activity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void selectFrag(View view) {
        Fragment fr=new fragmentC();

        if(view == view.findViewById(R.id.button2)) {
            fr=new fragmentB();
        }
        else {
            fr=new fragmentA();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_place,fr);
        fragmentTransaction.commit();
    }
}
