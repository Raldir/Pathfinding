package com.mygdx.pathfinding;


public class StartFigur extends Figure {

	private Field before;
	
	public StartFigur(Field f){
		super("images/roboter.jpg", f);
		move(f);
		before.setTurn(0);
	}
	
	public void move(Field f){
		before = this.getField();
		before.setMoveable(false);
		setField(f);
		f.setTurn(PathfindingMain.stepsCounter);
		f.setMoveable(false);
		setX(f.getX());
		setY(f.getY());
		
	}
	
	public Field getBefore(){
		return before;
	}
	
}
