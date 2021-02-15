package com.example.activity_lifecycle;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "lifecycle";
    public static String msg = "Welcome";
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            TextView text=(TextView) findViewById(R.id.text1);
            msg = "onCreate invoked";
            Log.d(TAG,msg);
            text.append("\n"+msg);
        }
        @Override
        protected void onStart() {
            super.onStart();
            TextView text=(TextView) findViewById(R.id.text1);
            msg = "onStart invoked";
            Log.d(TAG,msg);
            text.append("\n"+msg);
        }
        @Override
        protected void onResume() {
            super.onResume();
            msg = "onResume invoked";
            Log.d(TAG,msg);
            TextView text=(TextView) findViewById(R.id.text1);
            text.append("\n"+msg);
        }
        @Override
        protected void onPause() {
            super.onPause();
            msg = "onPause invoked";
            Log.d(TAG,msg);
            TextView text=(TextView) findViewById(R.id.text1);
            text.append("\n"+msg);
        }
        @Override
        protected void onStop() {
            super.onStop();
            msg = "onStop invoked";
            Log.d(TAG,msg);
            TextView text=(TextView) findViewById(R.id.text1);
            text.append("\n"+msg);
        }
        @Override
        protected void onRestart() {
            super.onRestart();
            msg = "onRestart invoked";
            Log.d(TAG,msg);
            TextView text=(TextView) findViewById(R.id.text1);
            text.append("\n"+msg);
        }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            msg="onDestroy invoked";
            Toast toast=Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
            Log.d(TAG,msg);
            toast.show();
        }
}

