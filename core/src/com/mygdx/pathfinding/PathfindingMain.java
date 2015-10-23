package com.mygdx.pathfinding;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PathfindingMain extends ApplicationAdapter {
	
	private OrthographicCamera camera;
	
	public static String TITLE = "pathfinding";
	public static int WITDH = 500;
	public static int HEIGHT = 600;
	
	private Stage stage;
	
	private final int XFieldNum = 13;
	private final int YFieldNum = 17;
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1920, 1080);
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Gdx.graphics.setContinuousRendering(false);
		Image img = new Image(new Texture("images/green.png"));
		img.setBounds(0, 0, (WITDH / XFieldNum), (HEIGHT / 17));
		stage.addActor(img);
		Field[][] fields = new Field[17][13];
		for(int i = 0; i < 17; i++){
			for(int j = 0; j < 13; j++){
				fields[i][j] = new Field(j * (WITDH / XFieldNum), (WITDH / XFieldNum) + j * (WITDH / XFieldNum), 
						i * (HEIGHT / YFieldNum), (HEIGHT / YFieldNum) + i * (HEIGHT / YFieldNum));
			}
		}
		Gdx.graphics.requestRendering();
	}

	public void Pathfinding(){
		
	}
	
	public void moveImgToField(Image img, Field f){
		img.setX(f.getX());
		img.setY(f.getY());
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
