package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView currentTime;
    TextView timeIs;
    Button butt, webBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime = findViewById(R.id.currentTime);
        timeIs = findViewById(R.id.timeIs);
        butt = findViewById(R.id.john);

        String timeText = "" + new Date();
        currentTime.setText(timeText);

        butt.setText("Tap me!");

        butt.setOnClickListener(this);
        currentTime.setOnClickListener(this);
        timeIs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == currentTime) {
            currentTime.setText("You... tapped the text?");
        } else if (v == timeIs) {
            currentTime.setText("" + new Date());
        } else {
            currentTime.setText("Yay! You tapped the button!");
        }
    }
}
