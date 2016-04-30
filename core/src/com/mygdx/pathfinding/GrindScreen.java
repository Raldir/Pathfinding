package com.mygdx.pathfinding;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GrindScreen implements Screen {
	
	

	private OrthographicCamera camera;
	
	private Stage stage;
	
	public GrindScreen(Stage s) {
		stage = s;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		
	}

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1F,1F,1F,1F);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		camera.update();
		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
