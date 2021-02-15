package com.example.telephony_manager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TelephonyManager tm;
    TextView tv;
    final static int PERMISSION_REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView1);
        tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of TelephonyManager the returns the information
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Requested not granted for Phone State",
                Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.
                        permission.CAMERA}, 1);
        }
        else
        {
            Toast.makeText(MainActivity.this, "Requested already granted for Phone State",
                    Toast.LENGTH_LONG).show();

        }

        String IMEINumber = tm.getDeviceId();
        //String subscriberID=tm.getDeviceId();
        String SIMSerialNumber=tm.getSimSerialNumber();
        String networkCountryISO=tm.getNetworkCountryIso();
        String SIMCountryISO=tm.getSimCountryIso();
        String softwareVersion=tm.getDeviceSoftwareVersion();
        String voiceMailNumber=tm.getVoiceMailNumber();

        //Get the phone type
        String strphoneType="";

        int phoneType=tm.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType="CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType="GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType="NONE";
                break;
        }

        //getting information if phone is in roaming
        boolean isRoaming=tm.isNetworkRoaming();

        String info="Phone Details:\n";
        info+="\n IMEI Number:"+IMEINumber;
        //info+="\n SubscriberID:"+subscriberID;
        info+="\n Sim Serial Number:"+SIMSerialNumber;
        info+="\n Network Country ISO:"+networkCountryISO;
        info+="\n SIM Country ISO:"+SIMCountryISO;
        info+="\n Software Version:"+softwareVersion;
        info+="\n Voice Mail Number:"+voiceMailNumber;
        info+="\n Phone Network Type:"+strphoneType;
        info+="\n In Roaming :"+isRoaming;

        tv.setText(info);
        PhoneCallListener phoneCallListener = new PhoneCallListener();
        tm.listen(phoneCallListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    // monitor phone call states
    private class PhoneCallListener extends PhoneStateListener {
        String TAG = "LOGGING PHONE CALL";
        private boolean phoneCalling = false;
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(TAG, "OFFHOOK");
                phoneCalling = true;
            }
            // When the call ends launch the main activity again
            if (TelephonyManager.CALL_STATE_IDLE == state) {
                Log.i(TAG, "IDLE");
                if (phoneCalling) {
                    Log.i(TAG, "restart app");
                    // restart app
                    Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage
                            (getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    phoneCalling = false;
                }
            }
        }
    }

    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            //    Toast.makeText(MainActivity.this, "Requested not granted", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.
                    permission.CALL_PHONE}, 2);
        } else {
            Toast.makeText(MainActivity.this, "Requested already granted", Toast.LENGTH_LONG).show();
            Intent phoneCallIntent = new Intent(Intent.ACTION_CALL);
            phoneCallIntent.setData(Uri.parse("tel:+919427829341"));
            startActivity(phoneCallIntent);
        }
    }
}




