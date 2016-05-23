package com.mygdx.pathfinding;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class Field extends Image implements Comparable<Field> {

	private boolean isMoveable;

	private Field beforeField;

	public Field getBeforeField() {
		return beforeField;
	}

	public void setBeforeField(Field beforeField) {
		this.beforeField = beforeField;
	}

	private int g, h, f;

	private Label text;
	LabelStyle textStyle;
	private BitmapFont font = new BitmapFont();

	private int turn = -1;

	public Field(int x, int y, int size) {
		super(new Texture("images/green.png"));
		super.setBounds(x, y, size, size);
		isMoveable = true;
		makeValuesVisible();
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Field(int x, int y, int size, String texture) {
		super(new Texture(texture));
		super.setBounds(x, y, size, size);
		isMoveable = true;
		makeValuesVisible();
	}

	public void setMoveable(boolean isMoveable) {
		this.isMoveable = isMoveable;
	}

	public boolean isMoveable() {
		return isMoveable;
	}

	public Label makeValuesVisible() {
		textStyle = new LabelStyle();
		textStyle.font = font;

		text = new Label(g  + " " + h + "  " + f, textStyle);
		text.setBounds(getX() + getWidth() / 10, getY() + getHeight() / 1.3f, getWidth(), getHeight() / 5);
		text.setFontScale(1f, 1f);

		return text;
	}

	public Label getLabel() {
		return text;
	}

	public boolean calculateValues(Field before, Figure end) {
		int g = before.getG() + PathfindingMain.ONEFIELDSIZE;
//		System.out.println(Math.pow(Math.abs(getX() - end.getX()), 2) + " " + Math.pow(Math.abs(getY() - end.getY()), 2) + " " + Math.pow(Math.pow(Math.abs(getX() - end.getX()), 2) + Math.pow(Math.abs(getY() - end.getY()), 2), 1/2));
		int h = (int) Math.sqrt(Math.pow(Math.abs(getX() - end.getX()), 2) + Math.pow(Math.abs(getY() - end.getY()), 2));
		int f = g + h;
		if(this.f < f){
			this.g = g;
			this.h = h;
			this.f = f;
			return true;
		}
		return false;
	}

	public int getG() {
		return g;
	}

	public int getF() {
		return f;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Field) {
			Field f = (Field) o;
			return getX() == f.getX() && getY() == f.getY();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) (getX() + getY());
	}

	@Override
	public int compareTo(Field o) {
		if (turn > o.getTurn()) {
			return 1;
		} else {
			return 0;
		}
	}

}
