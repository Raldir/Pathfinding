package com.mygdx.pathfinding;


public class StartFigur extends Figure {

	private Field before;
	
	public StartFigur(Field f){
		super("images/solaire.png", f);
		before = f;
		f.setMoveable(false);
	}
	
	public void move(Field f){
		before = this.getField();
		setField(f);
		f.setMoveable(false);
		setX(f.getX());
		setY(f.getY());
		
	}
	
	public Field getBefore(){
		return before;
	}
	
}
