package com.example.video_view_with_controls;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
    VideoView vv;
    String v1,v2,v3,v4;
    Uri uri;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v1 = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback1;
        v2 = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback2;
        v3 = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback3;
        v4 = "android.resource://" + getPackageName() + "/" + R.raw.videoplayback0;
        uri = Uri.parse(v1);
        vv = findViewById(R.id.videoView);
        final MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(vv);
        mediaController.setPrevNextListeners(new View.OnClickListener() {
            public void onClick(View v) {
                if(uri.equals(Uri.parse(v1)))
                    uri=Uri.parse(v2);
                else if(uri.equals(Uri.parse(v2)))
                    uri = Uri.parse(v3);
                else if(uri.equals(Uri.parse(v3)))
                    uri = Uri.parse(v4);
                else if(uri.equals(Uri.parse(v4)))
                    uri = Uri.parse(v1);
                vv.setVideoURI(uri);
                vv.requestFocus();
                vv.start();
            }
        }, new View.OnClickListener() {
            public void onClick(View v) {
                if(uri.equals(Uri.parse(v1)))
                    uri=Uri.parse(v4);
                else if(uri.equals(Uri.parse(v2)))
                    uri = Uri.parse(v1);
                else if(uri.equals(Uri.parse(v3)))
                    uri = Uri.parse(v2);
                else if(uri.equals(Uri.parse(v4)))
                    uri = Uri.parse(v3);
                vv.setVideoURI(uri);
                vv.requestFocus();
                vv.start();
            }
        });

        vv.setMediaController(mediaController);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();
    }
}