package com.sbchaoa_application;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreen extends FragmentActivity {

	private Typeface font;
	private TextView text1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fregment_splash_screen);

		text1 = (TextView) findViewById(android.R.id.text1);

		font = Typeface.createFromAsset(getAssets(), "fonts/Lemon-Regular.ttf");
		text1.setTypeface(font);
		
		new Handler().postDelayed(new Runnable() {

			// Using handler with postDelayed called runnable run method

			@Override
			public void run() {
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				// close this activity
				SplashScreen.this.finish();
				overridePendingTransition(android.R.anim.fade_in,
						android.R.anim.fade_out);
			}
		}, 5 * 1000);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
