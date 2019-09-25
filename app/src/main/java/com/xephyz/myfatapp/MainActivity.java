package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtWelcome, txtCurrentTime, txtChangeMe;
    ImageView imgWelcomeIcon;
    Button butMain, butResetTime, butLinkifyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextViews
        txtWelcome = findViewById(R.id.txtWelcome);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);
        txtChangeMe = findViewById(R.id.txtChangeMe);

        // Buttons
        butMain = findViewById(R.id.b1Main);
        butResetTime = findViewById(R.id.b2ResetTime);
        butLinkifyTxt = findViewById(R.id.b3Linkify);
        // butUrlOk = findViewById(R.id.butUrlOk);

        // ImageViews
        imgWelcomeIcon = findViewById(R.id.imgWelcome);

        butMain.setText("Tap me!");
        butResetTime.setText("Reset time");

        String timeText = "The time is:\n" + new Date();
        txtCurrentTime.setText(timeText);

        butMain.setOnClickListener(this);
        butResetTime.setOnClickListener(this);
        butLinkifyTxt.setOnClickListener(this);
        txtCurrentTime.setOnClickListener(this);
    }

    /**
     * Called when user taps 4th button labeled "Webview!"
     * @param view
     */
    public void gotoWebview(View view) {
        Intent intent = new Intent(this, DisplayWebviewActivity.class);
        startActivity(intent);
    }

    /**
     * Called when user taps 5th button labeled "Intents!"
     * @param view
     */
    public void gotoIntentsView(View view) {
        Intent intent = new Intent(this, DisplayIntentsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == txtCurrentTime) {
            txtCurrentTime.setText("You... tapped the text?");
        } else if (v == butMain) {
            txtCurrentTime.setText("Yay! You tapped the button!");
        } else if (v == butResetTime) {
            txtCurrentTime.setText("The time is:\n" + new Date());
        } else if (v == butLinkifyTxt) {
            txtChangeMe.setText("Linkify is actually pretty cool:\n" +
                    "My number is 20339944,\n" +
                    "My email is xephyzone@gmail.com\n" +
                    "I don't have a website, but it like: https://c.lambda.wtf.");
            Linkify.addLinks(txtChangeMe, Linkify.ALL);
        }
    }
}
