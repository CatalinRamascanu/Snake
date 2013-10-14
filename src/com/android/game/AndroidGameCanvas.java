package com.android.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.android.checkers.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.util.Pair;

public class AndroidGameCanvas {
	private Bitmap frameBuffer;
	private Canvas canvas;
	private Resources gameResources;
	private int screenHeight, screenWidth;
	private Bitmap upArrow,downArrow,leftArrow,rightArrow,track,watermellon;
	
	public AndroidGameCanvas(Resources resources, int viewWidth, int viewHeight){
		frameBuffer = Bitmap.createBitmap(viewWidth,viewHeight, Bitmap.Config.RGB_565);
		screenHeight = frameBuffer.getHeight();
		screenWidth = frameBuffer.getWidth();
		canvas = new Canvas(frameBuffer);
		gameResources = resources;
		loadResources();
	}
	
	public Resources getResources(){
		return gameResources;
	}
	private void loadResources() {
		upArrow = decodeBitmapFromResource(gameResources, R.drawable.up_arrow);
		downArrow = decodeBitmapFromResource(gameResources,	R.drawable.down_arrow);
		leftArrow = decodeBitmapFromResource(gameResources,R.drawable.left_arrow);
		rightArrow = decodeBitmapFromResource(gameResources,R.drawable.right_arrow);
		track = decodeBitmapFromResource(gameResources, R.drawable.snake_map);
		watermellon = decodeBitmapFromResource(gameResources, R.drawable.snake_map);
		
		upArrow = Bitmap.createScaledBitmap(upArrow, 100, 100, false);
		downArrow = Bitmap.createScaledBitmap(downArrow, 100, 100, false);
		leftArrow = Bitmap.createScaledBitmap(leftArrow, 100, 100, false);
		rightArrow = Bitmap.createScaledBitmap(rightArrow, 100, 100, false);
		track = Bitmap.createScaledBitmap(track, screenWidth, screenHeight, false);
		watermellon = Bitmap.createScaledBitmap(watermellon, 100, 100, false);
		
	}
	public void clearScreen() {
        int color = 0xff000000;
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }
	private void drawTrack() {
		canvas.drawBitmap(track,0,0,null);
	}
	
	private void drawButtons(){
		canvas.drawBitmap(upArrow, 110, 50,null);
		canvas.drawBitmap(downArrow,0 , 50,null);
		canvas.drawBitmap(leftArrow, 20, screenHeight - 300,null);
		canvas.drawBitmap(rightArrow, 20, screenHeight - 150,null);
	}
	
	private void drawFood(FoodAndPowers food){
		ArrayList<PositionImage> generatedFood = food.getGeneratedFood(); 
		Bitmap tmp;
		for (PositionImage foodData : generatedFood){
			canvas.drawBitmap(foodData.getImg(),  foodData.getPosX(),  foodData.getPosY(),null);
		}
	}
	
	private void drawSnake(Snake snake){
		snake.drawSnake(canvas);
	}
	public void drawGame(Canvas screenCanvas, Snake snake, FoodAndPowers food){
		clearScreen();
		drawTrack();
		drawFood(food);
		snake.drawSnake(canvas);
		drawButtons();
		screenCanvas.drawBitmap(frameBuffer, 0, 0, null);
	}

	private static Bitmap decodeBitmapFromResource(Resources res, int resId) {
		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}
}
