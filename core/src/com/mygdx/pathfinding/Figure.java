package com.mygdx.pathfinding;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Figure extends Image {

	private Field f;

	public Figure(String path, Field f) {
		super(new Texture(path));
		super.setBounds(f.getX(), f.getY(), PathfindingMain.ONEFIELDSIZE, PathfindingMain.ONEFIELDSIZE / 1.5f);
		setField(f);
	}

	public Field getField() {
		return f;
	}
	
	public void setField(Field f){
		this.f = f;
	}
}
