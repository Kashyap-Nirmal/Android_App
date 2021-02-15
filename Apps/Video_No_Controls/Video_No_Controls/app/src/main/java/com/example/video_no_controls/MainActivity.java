package com.example.video_no_controls;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity extends Activity {
    VideoView vv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv =findViewById(R.id.videoView);

        //Video Loop
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.video_clip);
        vv.setVideoURI(uri);
        vv.requestFocus();
        vv.start();
    }
    protected void onDestroy(){
        super.onDestroy();
        vv=findViewById(R.id.videoView);;
        vv.stopPlayback();
    }
}