package com.android.game;

import android.graphics.Bitmap;

public class PositionImage {
	private Bitmap img;
	private int posX,posY;
	
	public PositionImage(Bitmap img , int posX, int posY){
		this.img = img;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Bitmap getImg(){
		return img;
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
}
