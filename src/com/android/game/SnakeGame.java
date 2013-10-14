package com.android.game;

import java.util.ArrayList;
import java.util.Random;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

public class SnakeGame {
	private AndroidGameCanvas gameCanvas;
	private Snake snake;
	private FoodAndPowers food;
	private int screenWidth,screenHeight;
	private boolean updateNeeded,foodGenerated;
	private ArrayList<Collision> collisions;
	public SnakeGame(Resources gameResources, int screenWidth, int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.gameCanvas = new AndroidGameCanvas(gameResources, screenWidth, screenHeight); 
		snake = Snake.create().loadResource(gameResources).setBodyDistance(40)
				.setLength(4).setSize(100, 100).setInitialDirection(Movement.UP);
		snake.setStartingPoint(200, 200);
		food = FoodAndPowers.initialize().loadResources(gameResources,100, 100).generatePositions(2, screenWidth, screenHeight);
		initCollision();
	}
	private void initCollision(){
		collisions = new ArrayList<Collision>();
		collisions.add(new Collision(0, 150, 0, screenHeight));
		collisions.add(new Collision(screenWidth, screenWidth - 150, 0, screenHeight));
		collisions.add(new Collision(0, screenWidth, 0, 150));
		collisions.add(new Collision(0, screenWidth, screenHeight, screenHeight-150));
		
	}
	public boolean requiresUpdate(){
		return updateNeeded;
	}
	
	public void draw(Canvas screenCanvas){
		food.checkFood(snake);
		gameCanvas.drawGame(screenCanvas,snake,food);
		updateNeeded = false;
	}
	
	public void updateContinue(){
		snake.updateMove(Movement.CONTINUE);
		updateNeeded = true;
	}
	
	
	
	public void processEvent(MotionEvent event){
		int action = MotionEventCompat.getActionMasked(event);
        int touchPosX = (int) event.getX();
        int touchPosY = (int) event.getY();
	    switch(action) {
	        case (MotionEvent.ACTION_DOWN) :{
	        	if (touchPosX > 0 && touchPosX < 100 && touchPosY > 50 && touchPosY < 150){
	        		snake.updateMove(Movement.DOWN);
	        		updateNeeded = true;
	        	}
	        	if (touchPosX > 110 && touchPosX < 210 && touchPosY > 50 && touchPosY < 150){
	        		snake.updateMove(Movement.UP);
	        		updateNeeded = true;
	        	}
	        	if (touchPosX > 20 && touchPosX < 120 && touchPosY > screenHeight - 300 && touchPosY < screenHeight - 200){
	        		snake.updateMove(Movement.LEFT);
	        		updateNeeded = true;
		        }
	        	if (touchPosX > 20 && touchPosX < 120 && touchPosY > screenHeight - 150 && touchPosY < screenHeight - 50){
	        		snake.updateMove(Movement.RIGHT);
	        		updateNeeded = true;
	        	}
	        }
	    }      
	}
}
