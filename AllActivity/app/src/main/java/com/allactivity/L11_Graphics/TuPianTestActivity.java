package com.allactivity.L11_Graphics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.allactivity.R;

/*
 * 测试操作图片的Activity
 */
public class TuPianTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tupian_test);
	}

	public void testBD(View v) {
		startActivity(new Intent(this, BitmapTestActivity.class));
	}

	public void testMatrix(View v) {
		startActivity(new Intent(this, MatrixTestActivity.class));
	}
	
	public void clickIV(View v) {
		Toast.makeText(this, "点击了selector", Toast.LENGTH_LONG).show();
	}
}
