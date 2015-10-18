package com.mygdx.pathfinding;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class PathfindingMain extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	
	public static String TITLE = "pathfinding";
	public static int WITDH = 500;
	public static int HEIGHT = 600;
	
	private Stage stage;
	
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.graphics.setContinuousRendering(false);
		
		Field[][] fields = new Field[17][13];
		for(int i = 0; i < 17; i++){
			for(int j = 0; j < 13; j++){
				fields[i][j] = new Field(j, j * (WITDH / 13), i, i * (HEIGHT / 17));
			}
		}
		Gdx.graphics.requestRendering();
		System.out.println(fields[13][0].getX() + ("   ") + fields[13][0].getWidth() +
				("   ")+ fields[13][0].getY() + ("   ")+ fields[13][0].getHeight());
	}

	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
	}
}
