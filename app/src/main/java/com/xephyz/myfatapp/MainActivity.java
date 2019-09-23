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
    // Button butUrlOk;
    // EditText inputTxtUrl;
    // WebView webView;

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

        // EditTexts
        // inputTxtUrl = findViewById(R.id.inputTxtUrl);

        // WebViews
        // webView = findViewById(R.id.webView);

        butMain.setText("Tap me!");
        butResetTime.setText("Reset time");
        // inputTxtUrl.setText("https://");
        // webView.loadUrl("https://javabog.dk");

        String timeText = "The time is:\n" + new Date();
        txtCurrentTime.setText(timeText);

        butMain.setOnClickListener(this);
        butResetTime.setOnClickListener(this);
        butLinkifyTxt.setOnClickListener(this);
        txtCurrentTime.setOnClickListener(this);
        // butUrlOk.setOnClickListener(this);
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
            txtChangeMe.setText("Linkify er cool: Mit telefonnummer er 20339944,\n" +
                                "min e-post er xephyzone@gmail.com og\n" +
                                "jeg har ikke en hjemmeside... Men jeg kan godt lide https://lambda.wtf.");
            Linkify.addLinks(txtChangeMe, Linkify.ALL);
        }
        // else if (v == butUrlOk) {
        //     webView.loadUrl(inputTxtUrl.getText().toString());
        // }
    }
}
