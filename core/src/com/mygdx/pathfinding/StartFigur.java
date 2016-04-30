package com.mygdx.pathfinding;


public class StartFigur extends Figure {

	private Field before;
	
	public StartFigur(Field f){
		super("images/solaire.png", f);
		before = f;
	}
	
	public void move(Field f){
		before = this.getField();
		setX(f.getX());
		setY(f.getY());
		
	}
	
	public Field getBefore(){
		return before;
	}
	
}
