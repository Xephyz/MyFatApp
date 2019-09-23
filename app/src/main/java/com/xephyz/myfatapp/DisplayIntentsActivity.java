package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayIntentsActivity extends AppCompatActivity {
	// Variable declarations
	EditText inpMessage, inpNumber, inpEmail;
	Button bCallNum, bSendSMS, bSendEmail, bShareApp, bSettings, bWebIntents;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_intents);

		inpMessage = findViewById(R.id.int_inputText);
		inpNumber = findViewById(R.id.int_inputNum);
		inpEmail = findViewById(R.id.int_inputEmail);
	}

	public String createPhoneInfo() throws Exception {
		PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_ACTIVITIES);
		return "\nProgram: " + getPackageName() + " version " + pi.versionName
				+ "\nPhone model: " + Build.MODEL + "\n" + Build.PRODUCT
				+ "\nAndroid version " + Build.VERSION.RELEASE + "\nsdk: " + Build.VERSION.SDK_INT;
	}

	public String[] getInputFields() {
		return new String[]{
				inpMessage.getText().toString(),
				inpNumber.getText().toString(),
				inpEmail.getText().toString()
		};
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
}
