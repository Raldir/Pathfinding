package com.mygdx.pathfinding;


public class Vec2 {
	
	public int x, y = 0;
	
	/** returns a new vector */
	public static Vec2 vec2(int x, int y){
		return new Vec2(x, y);
	}
	
	public Vec2(int x, int y){
		this.x = x;
		this.y = y;
	}
		
}
