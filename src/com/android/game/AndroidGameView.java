package com.android.game;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidGameView extends SurfaceView implements Runnable {
	private int viewHeight;
	private int viewWidth;
	private SnakeGame snakeGame;

	public AndroidGameView(Context context, int viewHeight, int viewWidth) {
		super(context);
		this.viewHeight = viewHeight;
		this.viewWidth = viewWidth;
		snakeGame = new SnakeGame(getResources(), viewWidth, viewHeight);
	}

	public void run() {
		SurfaceHolder holder = this.getHolder();
		long startTime = System.currentTimeMillis();
		long delayTime;
		while (true) {
			if (!holder.getSurface().isValid())
				continue;
			if (snakeGame.requiresUpdate()) {
				Canvas screenCanvas = holder.lockCanvas();
				snakeGame.draw(screenCanvas);
				holder.unlockCanvasAndPost(screenCanvas);
			}
			delayTime = System.currentTimeMillis();
			if (delayTime -startTime  > 100) {
				snakeGame.updateContinue();
				startTime = System.currentTimeMillis();
			}
		}
	}

	public void resume() {
		Thread t = new Thread(this);
		t.start();
	}

	public void pause() {

	}

	public boolean onTouchEvent(MotionEvent event) {
		snakeGame.processEvent(event);
		return super.onTouchEvent(event);
	}

}
