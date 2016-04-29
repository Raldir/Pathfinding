package com.mygdx.pathfinding;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Field extends Image{

	private boolean isMoveable;
	
	public Field(int x, int y, int size){
		super(new Texture("images/green.png"));
		super.setBounds(x, y, size, size);
		isMoveable = true;
	}
	
//	public Field(int x1, int x2, int y1, int y2, boolean isMoveable){
//		isMoveable = true;
//		this.x = x;
//		this.y = y;
//		this.size = size;
//	}
	
	public void setMoveable(boolean isMoveable){
		this.isMoveable = isMoveable;
	}
	
	public boolean getMoveable(){
		return isMoveable;
	}
}
