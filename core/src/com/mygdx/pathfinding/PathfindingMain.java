package com.mygdx.pathfinding;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PathfindingMain extends Game {

	public static int WIDTH = 400, HEIGHT = 400;
	public static int ONEFIELDSIZE = 50;
	public static String TITLE = "pathfinding";

	private Stage stage;

	private Board board;

	private OpenList openList = new OpenList();
//	private ArrayList<Field> closedList = new ArrayList<Field>();

	private StartFigur startFigur;
	private Figure endFigur;

	@Override
	public void create() {
		stage = new Stage();
		setScreen(new GrindScreen(stage));
		Gdx.input.setInputProcessor(stage);
		board = new Board(stage, WIDTH, HEIGHT, ONEFIELDSIZE);

		endFigur = new Figure("images/sonne.jpg", board.getAllFields()[5][5]);
		startFigur = new StartFigur(board.getAllFields()[0][0]);

		stage.addActor(endFigur);
		stage.addActor(startFigur);
		placeInOpenList();
		for(Field fi : openList){
			stage.addActor(fi.calculateValues(startFigur.getBefore(), endFigur));
		}
		createButton();

	}

	private void placeInOpenList() {
		startFigur.getBefore().setMoveable(false);
		stage.getActors().removeValue(startFigur.getBefore().getLabel(), true);
		Vec2 pos = board.getFieldPos(startFigur.getField());
		if(pos.x < WIDTH)
		openList.add(board.getAllFields()[pos.x + 1][pos.y]);
		if(pos.x > 0){
		openList.add(board.getAllFields()[pos.x - 1][pos.y]);
		}
		if(pos.y > 0){
		openList.add(board.getAllFields()[pos.x][pos.y - 1]);
		}
		if(pos.y < HEIGHT){
		openList.add(board.getAllFields()[pos.x][pos.y + 1]);
		}
	}
	

	private Field calculateNextField() {
		Field maxField = null;
		int maxValue = 0;
		for (Field f : openList) {
			if (maxValue < f.getF()) {
				maxField = f;
				maxValue = f.getF();
			}
		}

		return maxField;
	}

	private void nextStep() {
		Field f = calculateNextField();
		startFigur.move(f);
		placeInOpenList();
		for(Field fi : openList){
			fi.calculateValues(startFigur.getBefore(), endFigur);
		}

	}

	private TextButton createButton() {
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("images/Button.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("black");
		textButtonStyle.down = skin.getDrawable("black");
		textButtonStyle.checked = skin.getDrawable("black");
		TextButton button = new TextButton("Step", textButtonStyle);
		stage.addActor(button);
		button.setBounds(WIDTH - ONEFIELDSIZE, 0, ONEFIELDSIZE, ONEFIELDSIZE / 2);
		button.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nextStep();

			}
		});
		return button;
	}

}
