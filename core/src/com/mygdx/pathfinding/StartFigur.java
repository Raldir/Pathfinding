package com.mygdx.pathfinding;


public class StartFigur extends Figure {

	private Field before;
	
	public StartFigur(Field f){
		super("images/solaire.png", f);
		move(f);
	}
	
	public void move(Field f){
		before = this.getField();
		before.setMoveable(false);
		setField(f);
		f.setMoveable(false);
		setX(f.getX());
		setY(f.getY());
		
	}
	
	public Field getBefore(){
		return before;
	}
	
}
