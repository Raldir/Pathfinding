package com.mygdx.pathfinding;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Field extends Image{

	private boolean isMoveable;
	
	private int g, h, f;

	private Label text;
	LabelStyle textStyle;
	private BitmapFont font = new BitmapFont();
	
	
	public Field(int x, int y, int size){
		super(new Texture("images/green.png"));
		super.setBounds(x, y, size, size);
		isMoveable = true;
		makeValuesVisible();
	}
	
	public void setMoveable(boolean isMoveable){
		this.isMoveable = isMoveable;
	}
	
	public boolean isMoveable(){
		return isMoveable;
	}
	
	private Label makeValuesVisible(){
		textStyle = new LabelStyle();
		textStyle.font = font;

		text = new Label(g + " " + " " + h + " " + f, textStyle);
		text.setBounds(getX() + getWidth() / 10, getY() +  getHeight() / 1.3f , getWidth(), getHeight() / 5);
		text.setFontScale(1f,1f);
		
		return text;
	}
	
	public Label getLabel(){
		return text;
	}
	public Label calculateValues(Field before, Figure end){
	 g = before.getG();
	 h = (int) (end.getX() + end.getY());
	 f = g + h;
	 return makeValuesVisible();
	}
	
	public int getG() {
		return g;
	}
	
	public int getF() {
		return f;
	}
	
}
