package com.example.ui_widgets4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class progressdialog extends AppCompatActivity {
    TextView tv;
    static String currentprogress="0% or Download cancelled";
    ProgressDialog progressBar;
    Intent i = getIntent();
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;

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
        setContentView(R.layout.progressdialog);
        tv = findViewById(R.id.textView1);
    }

    public void onBackPressed(){
        Intent i = new Intent(progressdialog.this, MainActivity.class);
        i.putExtra("progress",currentprogress);
        startActivity(i);
    }

    public void progress(View view) {
        progressBar = new ProgressDialog(view.getContext());
        progressBar.setCancelable(true);
        progressBar.setMessage("File downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        //reset progress bar and filesize status
        progressBarStatus = 0;
        fileSize = 0;


        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {
                    // performing operation
                    progressBarStatus = doOperation();
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // Updating the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                // performing operation if file is downloaded,
                if (progressBarStatus >= 100) {
                    // sleeping for 1 second after operation completed
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // close the progress bar dialog
                    currentprogress="File Donwloaded successfully";
                    progressBar.dismiss();
                }
            }
        }).start();
    }
}
