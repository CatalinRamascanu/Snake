package com.android.game;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.android.checkers.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path.Direction;
import android.util.Log;


public class Snake {
	private int length;
	private Bitmap bodyImage;
	private static HashMap<Movement, Bitmap> headImage;
	private int bodyDistance;
	private int posX,posY;
	private static LinkedList<bodyData> bodySet;
	private Movement direction;
	private Snake(){
	}
	public static Snake create(){
		bodySet = new LinkedList<bodyData>();
		headImage = new HashMap<Movement, Bitmap>();
		return new Snake();
	}
	public Snake loadResource(Resources resources){
		bodyImage = BitmapFactory.decodeResource(resources, R.drawable.ball);
		headImage.put(Movement.UP, BitmapFactory.decodeResource(resources, R.drawable.head_right));
		headImage.put(Movement.DOWN, BitmapFactory.decodeResource(resources, R.drawable.head_left));
		headImage.put(Movement.LEFT, BitmapFactory.decodeResource(resources, R.drawable.head_up));
		headImage.put(Movement.RIGHT, BitmapFactory.decodeResource(resources, R.drawable.head_down));
		return this;
	}
	
	public Snake setSize(int snakeHeight, int snakeWidth){
		bodyImage = Bitmap.createScaledBitmap(bodyImage, snakeHeight, snakeWidth, false);
		Bitmap tmp;
		tmp = headImage.get(Movement.UP);
		tmp = Bitmap.createScaledBitmap(tmp, snakeWidth, snakeHeight, false);
		headImage.put(Movement.UP, tmp);
		tmp = headImage.get(Movement.DOWN);
		tmp = Bitmap.createScaledBitmap(tmp, snakeWidth, snakeHeight, false);
		headImage.put(Movement.DOWN, tmp);
		tmp = headImage.get(Movement.RIGHT);
		tmp = Bitmap.createScaledBitmap(tmp, snakeWidth, snakeHeight, false);
		headImage.put(Movement.RIGHT, tmp);
		tmp = headImage.get(Movement.LEFT);
		tmp = Bitmap.createScaledBitmap(tmp, snakeWidth, snakeHeight, false);
		headImage.put(Movement.LEFT, tmp);
		return this;
	}
	
	public Snake setBodyDistance(int bodyDistance){
		this.bodyDistance = bodyDistance;
		return this;
	}	
	public Snake setLength(int length){
		this.length = length;
		return this;
	}
	
	public Snake setInitialDirection(Movement direction){
		this.direction = direction;
		return this;
	}
	
	public void growInLength(){
		length++;
		bodySet.addFirst(bodySet.getFirst());
	}
	
	public void setStartingPoint(int x, int y){
		posX = x;
		posY = y;
		int distance = 0;
		for (int i = 0; i < length; i++){
			bodySet.add(new bodyData(posX + distance, posY));
			distance += bodyDistance;
		}
	}
	public Movement getDirection(){
		return direction;
	}
	public PairNumber getHeadPosition(){
		return new PairNumber(bodySet.getLast().posX, bodySet.getLast().posY);
	}
	
	public int getBodyDistance(){
		return bodyDistance;
	}
	public void drawSnake(Canvas canvas){
		for (int i = 0; i < bodySet.size(); i++){
			if (i < bodySet.size() - 1){
				canvas.drawBitmap(bodyImage, bodySet.get(i).posX,bodySet.get(i).posY, null);
			}
			else{
				canvas.drawBitmap(headImage.get(direction), bodySet.get(i).posX,bodySet.get(i).posY, null);
			}
		}
	}
	
	public void updateMove(Movement move){
		if (move == Movement.CONTINUE){
			updateMove(direction);
		}
		if (move == Movement.UP){
			if (direction != Movement.DOWN){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX + bodyDistance, head.posY));
				direction = Movement.UP;
			}
		}
		if (move == Movement.DOWN){
			if (direction != Movement.UP){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX - bodyDistance, head.posY));
				direction = Movement.DOWN;
			}
		}
		if (move == Movement.LEFT){
			if (direction != Movement.RIGHT){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX, head.posY - bodyDistance));
				direction = Movement.LEFT;
			}
		}
		if (move == Movement.RIGHT){
			if (direction != Movement.LEFT){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX, head.posY + bodyDistance));
				direction = Movement.RIGHT;
			}
		}
		if (move == Movement.UPRIGHT){
			if (direction != Movement.DOWNLEFT){
				Log.v("D","ACTION: Snake has moved UPRIGHT");
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX + bodyDistance, head.posY + bodyDistance));
				direction = Movement.UPRIGHT;
			}
		}
		if (move == Movement.UPLEFT){
			if (direction != Movement.DOWNRIGHT){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX + bodyDistance, head.posY - bodyDistance));
				direction = Movement.UPLEFT;
			}
		}
		if (move == Movement.DOWNRIGHT){
			if (direction != Movement.UPLEFT){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX - bodyDistance, head.posY + bodyDistance));
				direction = Movement.DOWNRIGHT;
			}
		}
		if (move == Movement.DOWNLEFT){
			if (direction != Movement.UPRIGHT){
				bodySet.remove();
				bodyData head = bodySet.getLast();
				bodySet.add(new bodyData(head.posX - bodyDistance, head.posY - bodyDistance));
				direction = Movement.DOWNLEFT;
			}
		}		
	}
	
	private static class bodyData{
		private int posX, posY;
		public bodyData(int posX, int posY) {
			super();
			this.posX = posX;
			this.posY = posY;
		}
		
	}

}
