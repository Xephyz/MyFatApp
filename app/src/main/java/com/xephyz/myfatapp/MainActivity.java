package com.xephyz.myfatapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	EditText inpSomeText, inpNum;
	Button butCallNum, butSMS, butSendEmail, butShareApp, butSettings, butWebIntents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TableLayout tl = new TableLayout(this);

		inpSomeText = new EditText(this);
		inpSomeText.setHint("Type some text here");
		tl.addView(inpSomeText);

		inpNum = new EditText(this);
		inpNum.setHint("Type phonenumber here... If ya feel like it");
		inpNum.setInputType(InputType.TYPE_CLASS_PHONE);
		tl.addView(inpNum);

		butCallNum = new Button(this);
		butCallNum.setText("Call number");
		tl.addView(butCallNum);

		butSMS = new Button(this);
		butSMS.setText("Open send SMS");
		tl.addView(butSMS);

		butSendEmail = new Button(this);
		butSendEmail.setText("Open E-mail app and send E-mail");
		tl.addView(butSendEmail);

		butShareApp = new Button(this);
		butShareApp.setText("Share this app");
		tl.addView(butShareApp);

		butSettings = new Button(this);
		butSettings.setText("Open settings (e.g. Display)");
		tl.addView(butSettings);

		butWebIntents = new Button(this);
		butWebIntents.setText("Opens website about intents in browser app");
		tl.addView(butWebIntents);

		TextView txtLinkify = new TextView(this);
		txtLinkify.setText("Man kan også bruge klassen Linkify til at putte intents ind i tekst.\n" + "Mit telefonnummer er 20339944, min e-post er xephyzone@gmail.com og " + "jeg har ikke en hjemmeside... Men jeg elsker https://lambda.wtf.");

        ScrollView sv = new ScrollView(this);
        sv.addView(tl);
        setContentView(sv);


        // setOnClickListener's :)
        butCallNum.setOnClickListener(this);
        butSMS.setOnClickListener(this);
        butShareApp.setOnClickListener(this);
        butSendEmail.setOnClickListener(this);
        butSettings.setOnClickListener(this);
        butWebIntents.setOnClickListener(this);
    }

    /**
     * Ofte har man som udvikler brug for info om den telefon brugeren har.
     * Denne metode giver telefonmodel, Androidversion og programversion etc.
     */
    public String createPhoneInfo() throws Exception {
        PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
        return "\nProgram: " + getPackageName() + " version " + pi.versionName
                + "\nTelefonmodel: " + Build.MODEL + "\n" + Build.PRODUCT
                + "\nAndroid version " + Build.VERSION.RELEASE + "\nsdk: " + Build.VERSION.SDK_INT;
    }

    @Override
    public void onClick(View v) {
    }
}
