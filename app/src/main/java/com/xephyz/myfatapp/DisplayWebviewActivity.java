package com.xephyz.myfatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class DisplayWebviewActivity extends AppCompatActivity {
	// Variable declarations
	EditText txtUrl;
	Button bUrlGo;
	WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_webview);

		txtUrl = findViewById(R.id.web_txt_url);
		bUrlGo = findViewById(R.id.web_b_urlGo);
		webView = findViewById(R.id.web_webview);
		webView.loadUrl("https://javabog.dk");
	}

	public void loadUrl(View view) {
		String urlString = txtUrl.getText().toString();

		System.out.println("This has been tapped: " + view);
		webView.loadUrl(urlString);
	}
}
