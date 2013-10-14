package com.android.game;

public class Collision {
	private int startX,endX;
	private int startY,endY;
	public Collision(int startX, int endX, int startY, int endY) {
		super();
		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
	}
	
	public int getStartX(){
		return startX;
	}
	public int getEndX(){
		return endX;
	}
	
	public int getStartY(){
		return startY;
	}
	
	public int getEndY(){
		return endY;
	}
}
