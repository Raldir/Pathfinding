package com.mygdx.pathfinding;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PathfindingMain extends Game {
	
	private OrthographicCamera camera;
	
	public static String TITLE = "pathfinding";
	public static int WIDTH = 400, HEIGHT = 400;
	public static int ONEFIELDSIZE = 50;
	
	private Stage stage;
	@Override
	public void create () {
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Image img = new Image(new Texture("images/green.png"));
		img.setBounds(0, 0, 100, 100);
		stage.addActor(img);
		new Board(stage, WIDTH, HEIGHT, ONEFIELDSIZE);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1F,1F,1F,1F);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();
	}
}
