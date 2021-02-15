package com.example.bluetooth_broadcast_receier;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView textInfo;
    Button buttonEnableBT;
    BluetoothAdapter bluetoothAdapter;
    BroadcastReceiver receiver;
    final static int REQUEST_ENABLE_BT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textInfo =findViewById(R.id.textView1);
        buttonEnableBT =findViewById(R.id.button);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            textInfo.setText("BlueTooth not supported in this device");
            buttonEnableBT.setEnabled(false);
        }else{
            if (bluetoothAdapter.isEnabled()) {
                buttonEnableBT.setText("Turn OFF");
                textInfo.setText("BlueTooth enabled");
            }else{
                buttonEnableBT.setText("Turn ON");
                textInfo.setText("Bluetooth disabled, click button to turn on BlueTooth.");
            }
            receiver=new MyReceiver1();
            //register BroadcastReceiver
            registerReceiver(receiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void buttonvlick(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        textInfo=findViewById(R.id.textView1);
        if(buttonText=="Turn OFF")
        {
            BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
            bAdapter.disable();
            Toast.makeText(MainActivity.this, "BlueTooth Turning Off", Toast.LENGTH_LONG).show();
            b.setText("Turn ON");
            textInfo.setText("Bluetooth Disabled.");
        }
        else{
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            b.setText("Turn OFF");
            textInfo.setText("Bluetooth Disabled.");
        }
    }
}
