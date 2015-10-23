package com.mygdx.pathfinding;

public class Field{

	int x1, x2, y1, y2;
	boolean isMoveable;
	
	public Field(int x1, int x2, int y1, int y2){
		isMoveable = true;
		this.x1 = x1;
		this.x2 = x2 ;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public Field(int x1, int x2, int y1, int y2, boolean isMoveable){
		this.isMoveable = isMoveable;
		this.x1 = x1;
		this.x2 = x2 - 1;
		this.y1 = y1;
		this.y1 = y2 - 1;
	}
	
	public void setMoveable(boolean isMoveable){
		this.isMoveable = isMoveable;
	}
	
	public boolean getMoveable(){
		return isMoveable;
	}
	
	public void setX(int x1, int x2){
		this.x1 = x1;
		this.x2 = x2 - 1;
		
	}
	
	public void setY(int y1, int y2){
		this.y1 = y1;
		this.y2 = y2 - 1;
	}
	
	public int getX(){
		return x1;
	}
	
	public int getY(){
		return y1;
	}
	
	public int getHeight(){
		return Math.abs(y2 - y1);
	}
	
	public int getWidth(){
		return Math.abs(x2 - x1);
	}
}
