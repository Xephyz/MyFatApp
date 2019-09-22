package com.xephyz.myfatapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	EditText inpSomeText, inpNum, inpEmail;
	Button butCallNum, butSMS, butSendEmail, butShareApp, butSettings, butWebIntents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TableLayout tl = new TableLayout(this);

		inpSomeText = new EditText(this);
		inpSomeText.setText("Replace me!");
		tl.addView(inpSomeText);

		inpNum = new EditText(this);
		inpNum.setHint("Type phonenumber here... If ya feel like it");
		// inpNum.setText("11223344"); /** TODO: REMOVE THIS WHEN NOT TESTING!!!**/
		inpNum.setInputType(InputType.TYPE_CLASS_PHONE);
		tl.addView(inpNum);

		inpEmail = new EditText(this);
		inpEmail.setHint("Type email address here (optional)");
		inpEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
		tl.addView(inpEmail);

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
		String text = inpSomeText.getText().toString();
		String num = inpNum.getText().toString();
		String email = inpEmail.getText().toString();

		inpNum.setError(null);
		if (num.length() == 0 && v == butCallNum) {
			inpNum.setError("Type a valid phonenumber");
			Toast.makeText(this, "Type a valid phonenumber", Toast.LENGTH_LONG).show();
			return;
		}

		try {
			if (v == butCallNum) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
				if (callIntent.resolveActivity(getPackageManager()) != null) {
					startActivity(callIntent);
				}
			}
			else if (v == butSMS) {
				text = text + createPhoneInfo();
				Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
				sendIntent.setData(Uri.parse("smsto:"));
				// Intent sendIntent = new Intent(Intent.ACTION_VIEW);
				// sendIntent.setType("vnd.android-dir/mms-sms");
				sendIntent.putExtra("address", num);
				sendIntent.putExtra("sms_body", text);
				if (sendIntent.resolveActivity(getPackageManager()) != null) {
					startActivity(sendIntent);
				}
			}
			else if (v == butSendEmail) {
				text = text + "\n\nPhone & App info:" + createPhoneInfo();
				Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

				emailIntent.setData(Uri.parse("mailto:"));	// only mail apps should handle this

				if (!email.isEmpty()) {
					emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
					emailIntent.putExtra(Intent.EXTRA_TEXT, text);
				} else {
					emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{text});
					emailIntent.putExtra(Intent.EXTRA_TEXT, createPhoneInfo());
				}
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, num);
				emailIntent.putExtra(Intent.EXTRA_CC, new String[]{"xephyzone@gmail.com"});
				if (emailIntent.resolveActivity(getPackageManager()) != null)
					startActivity(emailIntent);

			}

		} catch (Exception e) {
			Toast.makeText(this, "This phone lacks a function:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

		/*
		try {
			if (v == sendEpost) {
				tekst = tekst + lavTelefoninfo();
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL, new String[]{tekst});
				i.putExtra(Intent.EXTRA_SUBJECT, nummer);
				i.putExtra(Intent.EXTRA_TEXT, lavTelefoninfo());
				i.putExtra(Intent.EXTRA_CC, new String[]{"jacob.nordfalk@gmail.com"});
				startActivity(Intent.createChooser(i, "Send e-post..."));
			} else if (v == delApp) {
				Intent i = new Intent(Intent.ACTION_SEND).putExtra(Intent.EXTRA_SUBJECT, "Prøv AndroidElementer").putExtra(Intent.EXTRA_TEXT, "Hej!\n\n" +
						"Hvis du programmerer til Android så prøv denne her eksempelsamling\n" +
						"AndroidElementer\n" +
						"https://play.google.com/store/apps/details?id=dk.nordfalk.android.elementer").setType("text/plain");
				startActivity(Intent.createChooser(i, "Del via"));
			} else if (v == wifiIndstillinger) {
				startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			} else if (v == webadresse) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/components/intents-common.html"));
				startActivity(intent);
			}
		} catch (Exception e) {
			Toast.makeText(this, "Denne telefon mangler en funktion:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		*/
    }
}
