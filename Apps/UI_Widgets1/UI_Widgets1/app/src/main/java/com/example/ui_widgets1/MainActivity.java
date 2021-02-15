package com.example.ui_widgets1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    public String msg1 = "Toggle ON", msg2 = "On", msgtb = "Toggle button : ", msgsw = "Switch : ", msg;
    public String msg3 = "True", msg4 = "False", msgc1 = "CheckBox1 : ", msgc2 = "CheckBox2 : ";
    TextView tv;
    ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        msg = msgtb + " " + msg1 + "\n" + msgsw + " " + msg2 + "\n" + msgc1 + " " + msg3 + "\n" + msgc2 + " " + msg4;
        tv.setText(msg);
        final Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    msg2 = (String) switch1.getTextOn();
                } else {
                    msg2 = (String) switch1.getTextOff();
                }
                msg = msgtb + " " + msg1 + "\n" + msgsw + " " + msg2 + "\n" + msgc1 + " " + msg3 + "\n" + msgc2 + " " + msg4;
                tv.setText(msg);
            }
        });

        CheckBox cb1 = findViewById(R.id.checkBox);

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (buttonView.isChecked())
                    msg3 = "True";
                else
                    msg3 = "False";
                msg = msgtb + " " + msg1 + "\n" + msgsw + " " + msg2 + "\n" + msgc1 + " " + msg3 + "\n" + msgc2 + " " + msg4;
                tv.setText(msg);
            }
        });

        CheckBox cb2 = findViewById(R.id.checkBox2);
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (buttonView.isChecked())
                    msg4 = "True";
                else
                    msg4 = "False";
                msg = msgtb + " " + msg1 + "\n" + msgsw + " " + msg2 + "\n" + msgc1 + " " + msg3 + "\n" + msgc2 + " " + msg4;
                tv.setText(msg);
            }
        });


    }

    public void toggle(View view) {
        tb = findViewById(R.id.toggleButton);
        msg1 = (String) tb.getText();
        msg = msgtb + " " + msg1 + "\n" + msgsw + " " + msg2 + "\n" + msgc1 + " " + msg3 + "\n" + msgc2 + " " + msg4;
        tv.setText(msg);
    }
}

