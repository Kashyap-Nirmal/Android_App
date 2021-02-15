package com.example.androidappsporfolio;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    Intent launchIntentx;
    String pkg="",activity="";
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        btn.setVisibility(View.INVISIBLE);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            launchIntentx = new Intent(MainActivity.this,profile.class);
            startActivity(launchIntentx);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        tv=findViewById(R.id.text_view);
        btn=findViewById(R.id.button);
        btn.setVisibility(View.VISIBLE);
        String msg="";
        Button btn=findViewById(R.id.button);
        btn.setOnClickListener(this);
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.tryframe) {
            msg="Status: Needs Improvements.\nThis is a demo of Tryframe. Here the Google Face API is used to detect the facial landmarks. And when the face of the user is at specific angle glasses will appear. It can be considered to be somewhat similar to Instagram filters.";
            tv.setText(msg);
            pkg="com.example.tryframe_demo";
            activity="com.example.tryframe_demo.MainActivity";
        } else if (id == R.id.firestore) {
            msg="This is a demo of Google Cloud Firestore. Here the basic Sign in and Sign UP can be perfomed.";
            tv.setText(msg);
            pkg="com.example.firestore_demo";
            activity="com.example.firestore_demo.MainActivity";
        } else if (id == R.id.tts) {
            msg="This is a demo of Google text to speech and Speech to text API.Here the Text to speech is performed on the text recognized from the user inputed Speech.";
            tv.setText(msg);
            pkg="com.example.speech_to_text";
            activity="com.example.speech_to_text.MainActivity";
        } else if (id == R.id.activity_lifecycle) {
            msg="This is a simple demo to showcase what Activity lifecycle method is invoked currently. From this app we can get the brief overview of how the Activity lifecycle methods are invoked.";
            tv.setText(msg);
            pkg="com.example.activity_lifecycle";
            activity="com.example.activity_lifecycle.MainActivity";
        } else if (id == R.id.databases) {
            msg="This is a demo where SQLite and Shared Preferences are used. It covers both these in the most simplest manner";
            tv.setText(msg);
            pkg="com.example.databases";
            activity="com.example.databases.MainActivity";
        } else if (id == R.id.third_party) {
            msg="This is a demo where various Third party libraries like Picasso , Glide , CircleImageView , Text Drawable. \n\n!! Here back button wont bring you back to this app. \n!! Take Note of it.";
            tv.setText(msg);
            pkg="com.example.third_party_libraries";
            activity="com.example.third_party_libraries.MainActivity";
        } else if (id == R.id.services) {
            msg="This is a demo where I have demonstrated the use of services in Android. Here a music clip is used for the background music.";
            tv.setText(msg);
            pkg="com.example.services";
            activity="com.example.services.MainActivity";
        } else if (id == R.id.broadcast_receivers) {
            msg="This is a simple demo of Custom Broadcast receiver.";
            tv.setText(msg);
            pkg="com.example.broadcast_receiver";
            activity="com.example.broadcast_receiver.MainActivity";
        } else if (id == R.id.wifi_broadcast_receivers) {
            msg="This is a simple demo wherein the basic details regarding the WIFI are displayed as the part of WIFI broadcast receiver.";
            tv.setText(msg);
            pkg="com.example.wifi_broadcast_receiver";
            activity="com.example.wifi_broadcast_receiver.MainActivity";
        } else if (id == R.id.blue_broadcast_receivers) {
            msg="This is a simple demo wherein the basic details regarding the Bluetooth state change are displayed as Toast message.";
            tv.setText(msg);
            pkg="com.example.bluetooth_broadcast_receiver";
            activity="com.example.bluetooth_broadcast_receiver.MainActivity";
        } else if (id == R.id.video_view) {
            msg="This is a demo where a video view is shown with the most basic of controls.";
            tv.setText(msg);
            pkg="com.example.video_view_with_controls";
            activity="com.example.video_view_with_controls.MainActivity";
        }else if (id == R.id.animations) {
            msg="This is a demo to display various Android animations.";
            tv.setText(msg);
            pkg="com.example.android_animations";
            activity="com.example.android_animations.MainActivity";
        } else if (id == R.id.ui_widgets) {
            msg="This is a demo where UI widgets namely ToggleButton, Switch, CheckBox are shown.";
            tv.setText(msg);
            pkg="com.example.ui_widgets1";
            activity="com.example.ui_widgets1.MainActivity";
        } else if (id == R.id.ui_widgets2) {
            msg="This is a demo where UI widgets namely TimePicker and Date Picker are displayed.";
            tv.setText(msg);
            pkg="com.example.ui_widgets2";
            activity="com.example.ui_widgets2.MainActivity";
        } else if (id == R.id.ui_widgets3) {
            msg="This is a demo where UI widgets namely AutoCompleteTextView and Spinner are shown.";
            tv.setText(msg);
            pkg="com.example.ui_widgets3";
            activity="com.example.ui_widgets3.MainActivity";
        } else if (id == R.id.ui_widgets4) {
            msg="This is a demo where UI widgets namely Rating Bar, Progress Bar, Progress Dialog, Indeterminate Progress Bar are shown.";
            tv.setText(msg);
            pkg="com.example.ui_widgets4";
            activity="com.example.ui_widgets4.MainActivity";
        } else if (id == R.id.recycler_view) {
            msg="This is a demo of wherein Linear and Grid both Recycler view are showcased.";
            tv.setText(msg);
            pkg="com.example.recycle_view";
            activity="com.example.recycle_view.MainActivity";
        } else if (id == R.id.fragment) {
            msg="This is a demo to show the usage of simple Fragments.";
            tv.setText(msg);
            pkg="com.example.fragment";
            activity="com.example.fragment.MainActivity";
        } else if (id == R.id.panorama_view) {
            msg="This is a demo which shows the Panorama View of an Image.";
            tv.setText(msg);
            pkg="com.example.panaroma_view";
            activity="com.example.panaroma_view.MainActivity";
        } else if (id == R.id.intent) {
            msg="This is a demo to showcase an Explicit intent.Here the app transots between mostly 3 activities.";
            tv.setText(msg);
            pkg="com.example.explicit_intent";
            activity="com.example.explicit_intent.MainActivity";
        } else if (id == R.id.text_over_image) {
            msg="Status : Needs modifications and improvements\nThis is a demo of Text Over Image.";
            tv.setText(msg);
            pkg="com.example.text_over_images";
            activity="com.example.text_over_images.MainActivity";
            startActivity(launchIntentx);
        } else if (id == R.id.product_view) {
            msg="This is a demo where images of Singke product from various angles are used to create a 360 view.";
            tv.setText(msg);
            pkg="com.example.a360productview";
            activity="com.example.a360productview.MainActivity";
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        launchIntentx = new Intent(Intent.ACTION_MAIN);
        launchIntentx.setComponent(new ComponentName(pkg,activity));
        startActivity(launchIntentx);
    }
}
