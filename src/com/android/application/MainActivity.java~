package com.android.application;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.android.game.AndroidGameView;

public class MainActivity extends Activity {
	private AndroidGameView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.v("test", "Started Main Activity.");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int height = dm.heightPixels;
        final int width = dm.widthPixels; 
		view = new AndroidGameView(this,height,width);
		this.setContentView(view);
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        view.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        view.pause();
    }
}
