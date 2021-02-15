package com.example.wifi_broadcast_receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView text;
    String mac="",state="",ip="",ssid="",bssid="",speed="",rssi="";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        DisplayWifiState();
        this.registerReceiver(this.myWifiReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private BroadcastReceiver myWifiReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            // TODO Auto-generated method stub
            NetworkInfo networkInfo = arg1.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
                DisplayWifiState();
            }
        }};

    private void DisplayWifiState(){
        ConnectivityManager myConnManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo myNetworkInfo = myConnManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        WifiManager myWifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
        mac="Mac address : "+myWifiInfo.getMacAddress()+"\n";
        if (myNetworkInfo.isConnected()){
            int myIp = myWifiInfo.getIpAddress();
            state="--- CONNECTED ---\n";
            int intMyIp3 = myIp/0x1000000;
            int intMyIp3mod = myIp%0x1000000;
            int intMyIp2 = intMyIp3mod/0x10000;
            int intMyIp2mod = intMyIp3mod%0x10000;
            int intMyIp1 = intMyIp2mod/0x100;
            int intMyIp0 = intMyIp2mod%0x100;
            ip="IP : "+intMyIp0+"."+intMyIp1+"."+intMyIp2+"."+intMyIp3+"\n";
            ssid="SSID : "+myWifiInfo.getSSID()+"\n";
            bssid="BSSID : "+myWifiInfo.getBSSID()+"\n";
            speed="Speed : "+myWifiInfo.getLinkSpeed()+" "+WifiInfo.LINK_SPEED_UNITS+"\n";
            rssi="RSSI : "+myWifiInfo.getRssi()+"\n";
            text.setText(state+mac+ip+ssid+bssid+speed+rssi);
        }
        else{
            state="--- DIS-CONNECTED! ---\n";
            text.setText(state+mac
            );
        }
    }
}