package com.allactivity.L11_Graphics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.allactivity.R;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void testTuPian(View view) {
		startActivity(new Intent(this, TuPianTestActivity.class));
	}

	public void testDraw(View view) {
		startActivity(new Intent(this, DrawTestActivity.class));
	}
}
