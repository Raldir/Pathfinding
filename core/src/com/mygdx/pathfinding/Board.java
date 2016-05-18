package com.mygdx.pathfinding;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Board {
	
	private static final double OBSTACLEPROPABILITY = 0.7f;
	private Field[][] fields;
	
	public Board(Stage i, int width, int heigth, int singleFieldSize){
		init(i, width, heigth, singleFieldSize);
	}

	
	
	private void init(Stage stage, int x , int y, int singleFieldSize){
		fields = new Field[x/singleFieldSize][y/singleFieldSize];
		for(int i = 0;  i < fields.length; i++){
			for(int j = 0; j < fields[i].length; j++){
				if(Math.random() >= OBSTACLEPROPABILITY){
					fields[i][j] = new Obsctacle(i * singleFieldSize, j * singleFieldSize, singleFieldSize);
					stage.addActor(fields[i][j]);
					continue;
				}
				fields[i][j] = new Field(i * singleFieldSize, j * singleFieldSize, singleFieldSize);
				stage.addActor(fields[i][j]);
			}
		}
	}
	
	public Field[][] getAllFields(){
		return fields;
	}
	
	public Vec2 getFieldPos(Field f){
		for(int i = 0; i < fields.length; i++){
			for(int j = 0; j < fields[i].length; j++){
				if(fields[j][i].equals(f)){
					return new Vec2(j, i);
				}
			}
		}
		return null;
	}
}
