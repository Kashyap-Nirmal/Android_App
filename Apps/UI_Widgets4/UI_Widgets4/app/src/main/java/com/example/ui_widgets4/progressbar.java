package com.example.ui_widgets4;

import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class progressbar extends AppCompatActivity implements Runnable{
    TextView tv;
    static String currentprogress="0% or Download cancelled";
    ProgressBar progressBar,progressBar1,progressBar2;
    Intent i = getIntent();
    static int flag=1;
    String progressBarSt="";
    private int progressBarStatus = 0;
    private long fileSize = 0;
    Handler hdlr=new Handler();
    Thread t;
    // checking how much file is downloaded and updating the filesize
    public int doOperation(){
        //The range of ProgressDialog starts from 0 to 10000
        while (fileSize <= 100) {
            fileSize++;
            return (int)fileSize;
        }//end of while
        currentprogress="File Downloaded successfully.";
        return 100;
    }//end of doOperation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        tv = findViewById(R.id.textView1);
        progressBar=findViewById(R.id.progressBar);
        //progressBar1=findViewById(R.id.progressBar1);
        progressBar2=findViewById(R.id.progressBar3);
        progressBarSt=progressBar.getProgress()+" / "+progressBar.getMax()+" %";
        tv.setText(progressBarSt);
        t=new Thread(this);
    }

    public void onBackPressed(){
        Intent i = new Intent(progressbar.this, MainActivity.class);
        i.putExtra("progress1",currentprogress);
        startActivity(i);
    }

    public void progress(View view) {
        flag=1;
        //reset progress bar and filesize status
        progressBarStatus = 0;
        fileSize = 0;
        progressBarStatus = progressBar.getProgress();
        t.start();
        /*try{
            if(flag==0)
                t.notify();
        }catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    public void run() {
        while (progressBarStatus <=100 & flag==1) {
            // performing operation
            progressBarStatus = doOperation();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*if(flag==0)
                try {
                    t.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            // Updating the progress bar
            hdlr.post(new Runnable() {
                public void run() {
                    progressBar.setProgress(progressBarStatus);
                    progressBar2.setProgress(progressBarStatus);
                    //progressBar1.setVisibility(View.VISIBLE);
                    progressBarSt=progressBar.getProgress()+" / "+progressBar.getMax()+" %";
                    tv.setText(progressBarSt);
                }
            });
        }
        // performing operation if file is downloaded,
        if (progressBarStatus >= 100) {
            // sleeping for 1 second after operation completed
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // close the progress bar dialog
            currentprogress="File Downloaded successfully";
        }
    }

    public void pause(View view) {
        flag=0;
        //progressBar1.setVisibility(View.INVISIBLE);
    }
}
