package com.android.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.android.checkers.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class FoodAndPowers {
	private static ArrayList<Bitmap> foodImages;
	private static ArrayList<PositionImage> generatedFood; 
	private int imgWidth,imgHeight,screenWidth,screenHeight;
	private FoodAndPowers(){
	}
	
	public static FoodAndPowers initialize(){
		foodImages = new ArrayList<Bitmap>();
		generatedFood = new ArrayList<PositionImage>();
		return new FoodAndPowers();
	}
	
	public FoodAndPowers loadResources(Resources res, int imgWidth, int imgHeight){
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		Bitmap tmp;
		tmp = BitmapFactory.decodeResource(res, R.drawable.watermellon);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		tmp = BitmapFactory.decodeResource(res, R.drawable.apple);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		tmp = BitmapFactory.decodeResource(res, R.drawable.banana);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		tmp = BitmapFactory.decodeResource(res, R.drawable.potato);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		tmp = BitmapFactory.decodeResource(res, R.drawable.peach);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		tmp = BitmapFactory.decodeResource(res, R.drawable.tomato);
		tmp = Bitmap.createScaledBitmap(tmp, imgWidth, imgHeight, false);
		foodImages.add(tmp);
		return this;
	}
	
	public FoodAndPowers generatePositions(int howMany, int screenWidth, int screenHeight){
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		int foodPosX, foodPosY, imageIndex;
		for (int i = 0; i < howMany; i++){
			Random randomGenerator = new Random();
			foodPosX = randomGenerator.nextInt(screenWidth-200);
			foodPosY = randomGenerator.nextInt(screenHeight-200);
			imageIndex = randomGenerator.nextInt(foodImages.size());
			generatedFood.add(new PositionImage(foodImages.get(imageIndex), foodPosX, foodPosY));
		}
		return this;
	}
	
	public ArrayList<PositionImage> getGeneratedFood(){
		return generatedFood;
	}
	
	public void checkFood(Snake snake){
		PairNumber snakePos = snake.getHeadPosition();
		Random randomGenerator = new Random();
		int foodPosX, foodPosY, imageIndex, nrEatenFood;
		int headPosX = snakePos.getPosX();
		int headPosY = snakePos.getPosY();
		nrEatenFood = 0;
		Iterator<PositionImage> it = generatedFood.iterator();
		while (it.hasNext()){
			PositionImage food = it.next();
			foodPosX = food.getPosX();
			foodPosY = food.getPosY();
			if (headPosX >= foodPosX - 70 && headPosX <= foodPosX + 100){
				if (headPosY >= foodPosY -70 && headPosY <= foodPosY + 100){
					it.remove();
					snake.growInLength();
					nrEatenFood++;
				}
			}
		}
		
		while(nrEatenFood > 0){
			foodPosX = randomGenerator.nextInt(screenWidth-200);
			foodPosY = randomGenerator.nextInt(screenHeight-200);
			imageIndex = randomGenerator.nextInt(foodImages.size());
			generatedFood.add(new PositionImage(foodImages.get(imageIndex), foodPosX, foodPosY));
			nrEatenFood--;
		}
		
	}
	
}
