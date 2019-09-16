package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView welcomeText, currentTime, changeMeText;
    ImageView welcomeIcon;
    Button mainButt, butt2, butt3, urlOk;
    EditText urlText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextViews
        welcomeText = findViewById(R.id.welcome);
        currentTime = findViewById(R.id.currentTime);
        changeMeText = findViewById(R.id.changeMe);

        // Buttons
        mainButt = findViewById(R.id.john);
        butt2 = findViewById(R.id.button2);
        butt3 = findViewById(R.id.button3);
        urlOk = findViewById(R.id.urlOk);

        // ImageViews
        welcomeIcon = findViewById(R.id.welcomeIcon);

        // EditTexts
        urlText = findViewById(R.id.urlText);

        // WebViews
        webView = findViewById(R.id.webView);

        mainButt.setText("Tap me!");
        butt2.setText("Reset time");
        urlText.setText("https://");
        webView.loadUrl("https://javabog.dk");

        String timeText = "The time is:\n" + new Date();
        currentTime.setText(timeText);

        mainButt.setOnClickListener(this);
        butt2.setOnClickListener(this);
        currentTime.setOnClickListener(this);
        urlOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == currentTime) {
            currentTime.setText("You... tapped the text?");
        } else if (v == mainButt) {
            currentTime.setText("Yay! You tapped the button!");
        } else if (v == butt2) {
            currentTime.setText("The time is:\n" + new Date());
        } else if (v == urlOk) {
            webView.loadUrl(urlText.getText().toString());
        }
    }
}
