package com.example.ui_widgets4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ratingbar extends AppCompatActivity {
    TextView tv;
    static String currentRating="0";
    RatingBar ratingbar1;
    Intent i = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratingbar);
        tv = findViewById(R.id.textView1);
        tv.setText("Current rating is : "+currentRating);
        ratingbar1 = findViewById(R.id.ratingBar);
        ratingbar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentRating=String.valueOf(ratingbar1.getRating());
                tv.setText("Current rating is : "+currentRating);
            }
        });
    }
    public void onBackPressed(){
        Intent i = new Intent(ratingbar.this, MainActivity.class);
        i.putExtra("rating","Current rating is : "+currentRating);
        startActivity(i);
    }
}
