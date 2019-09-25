package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayIntentsActivity extends AppCompatActivity {
	// Variable declarations
	EditText inpMessage, inpNumber, inpEmail;
	TextView txtLinkify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_intents);

		inpMessage = findViewById(R.id.int_inputText);
		inpNumber = findViewById(R.id.int_inputNum);
		inpEmail = findViewById(R.id.int_inputEmail);
		txtLinkify = findViewById(R.id.int_txt_textLinkified);

		txtLinkify.setText("You can use Linkify to put intents into a TextView.\n" +
						   "phonenumber: 87847989,\n" +
						   "e-mail: example@lambdamail.wtf,\n" +
						   "website: https://c.lambda.wtf.");
		Linkify.addLinks(txtLinkify, Linkify.ALL);
	}

	public String createPhoneInfo() throws Exception {
		PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
		return "\nProgram: " + getPackageName() + " version " + pi.versionName
				+ "\nPhone model: " + Build.MODEL + "\n" + Build.PRODUCT
				+ "\nAndroid version " + Build.VERSION.RELEASE + "\nsdk: " + Build.VERSION.SDK_INT;
	}

	public boolean isNotNumberFieldEmpty() {
		String num = inpNumber.getText().toString();

		inpNumber.setError(null);
		if (num.isEmpty()) {
			inpNumber.setError("Type a valid phonenumber");
			Toast.makeText(this, "Type a valid phonenumber", Toast.LENGTH_LONG).show();
			return false;
		} else {
			return true;
		}
	}

	public boolean isInputFieldEmpty(EditText field) {
		String viewText = field.getText().toString();

		return viewText.isEmpty();
	}

	public void callNumber(View view) {
		String num = inpNumber.getText().toString();

		if (isNotNumberFieldEmpty()) {
			Intent callNum = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
			if (callNum.resolveActivity(getPackageManager()) != null)
				startActivity(callNum);
		}
	}

	public void sendSMS(View view) {
		String num = inpNumber.getText().toString();
		String msg = inpMessage.getText().toString();

		if (isNotNumberFieldEmpty()) {
			Intent sendSMS = new Intent(Intent.ACTION_SENDTO);
			sendSMS.setData(Uri.parse("smsto:" + num));
			// sendSMS.putExtra("address", num);
			if (!msg.isEmpty()) {
				sendSMS.putExtra("sms_body", msg);
			} else {
				sendSMS.putExtra("sms_body", "No message was given, so here's a default one! :)");
			}

			if (sendSMS.resolveActivity(getPackageManager()) != null)
				startActivity(sendSMS);
		}
	}

	public void sendEmail(View view) {
		String text = inpMessage.getText().toString();
		String num = inpNumber.getText().toString();
		String email = inpEmail.getText().toString();

		try {
			text = text + "\n\nPhone & App info:" + createPhoneInfo();
		} catch (Exception e) {
			Toast.makeText(this, "This phone lacks a function:\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			return;
		}

		Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
		sendEmail.setData(Uri.parse("mailto:"));	// Only mail apps should handle this

		if (!email.isEmpty()) {
			sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
		} else {
			sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@lambda.wtf"});
		}

		sendEmail.putExtra(Intent.EXTRA_TEXT, text);
		if (inpMessage.getText().toString().isEmpty())
			sendEmail.putExtra(Intent.EXTRA_TEXT, "You didn't even type a message... " +
													"So here is a default template message thingy!" + text);

		if (!isInputFieldEmpty(inpNumber)) {
			sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Something about this number: " + num);
		} else {
			sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Uhm... something something subject...?");
		}

		sendEmail.putExtra(Intent.EXTRA_CC, new String[]{"test@lambda.wtf"});

		if (sendEmail.resolveActivity(getPackageManager()) != null)
			startActivity(sendEmail);
	}

	public void notImplementedYet(View view) {
		Toast.makeText(this, "Atleast i've implemented a toast! ;)", Toast.LENGTH_SHORT).show();
	}
}
