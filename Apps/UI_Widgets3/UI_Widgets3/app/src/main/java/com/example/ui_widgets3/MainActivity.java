package com.example.ui_widgets3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s;
    AutoCompleteTextView actv;
    TextView tv;
    String msg="",msg1="",msg2="",msgs="Spinner = ",msgactv="AutoCompleteTextView = ";
    String[] country = {"--Select--","India", "USA", "China", "Japan", "Other"};
    String[] country1 = {"Other","Japan", "USA","India", "China","UK", "--Select--"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        s=findViewById(R.id.spinner);
        actv=findViewById(R.id.autoCompleteTextView);
        msg=msgactv+" "+msg1+"\n"+msgs+" "+msg2;
        tv.setText(msg);

        s.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s.setAdapter(aa);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
        (this,android.R.layout.select_dialog_item,country1);
        //Getting the instance of AutoCompleteTextView
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLUE);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                //msg1=country1[position];
                msg1=""+parent.getSelectedItem();
                msg1= ""+adapter.getItem(position);
                //msg1=parent.getSelectedItem().toString();
                msg=msgactv+" "+msg1+"\n"+msgs+" "+msg2;
                tv.setText(msg);
            }
        });
    }
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        msg2=country[position];
        msg=msgactv+" "+msg1+"\n"+msgs+" "+msg2;
        tv.setText(msg);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
