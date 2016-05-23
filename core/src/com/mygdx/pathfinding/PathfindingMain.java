package com.mygdx.pathfinding;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class PathfindingMain extends Game {

	public static int WIDTH = 1000, HEIGHT = 1000;
	public static int ONEFIELDSIZE = 100;
	public static String TITLE = "pathfinding";

	private Stage stage;

	private Board board;

	private OpenSet openSet = new OpenSet();

	public static int stepsCounter = 0;

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
		updateOpenSet();
		createButton();
		createButtonforRestart();
	}

	private void makeEndWayVisible(Field f) {
		System.out.println(board.getFieldPos(f).x + " " + board.getFieldPos(f).y);
		if (!f.equals(board.getAllFields()[0][0])) {
			f.setDrawable(new SpriteDrawable(new Sprite(new Texture("images/red.jpg"))));
			makeEndWayVisible(f.getBeforeField());
		} else {
			f.setDrawable(new SpriteDrawable(new Sprite(new Texture("images/red.jpg"))));
			return;
		}
	}

	private void checkSingleField(Field fi) {
		stage.getActors().removeValue(fi.getLabel(), false);
		if (fi.calculateValues(startFigur.getField(), endFigur) && (openSet.contains(fi))) {
			fi.setBeforeField(startFigur.getField());
		} else if (!openSet.contains(fi) && fi.isMoveable()) {
			openSet.add(fi);
			fi.setBeforeField(startFigur.getField());
		}
		stage.addActor(fi.makeValuesVisible());
	}

	private void updateOpenSet() {
		stage.getActors().removeValue(startFigur.getBefore().getLabel(), false);
		openSet.remove(startFigur.getField());
		startFigur.getField().setDrawable(new SpriteDrawable(new Sprite(new Texture("images/black.png"))));
		Vec2 pos = board.getFieldPos(startFigur.getField());

		if ((pos.x + 1) < board.getAllFields()[1].length) {
			Field fi = board.getAllFields()[pos.x + 1][pos.y];
			checkSingleField(fi);
		}
		if (pos.x > 0) {
			Field fir = board.getAllFields()[pos.x - 1][pos.y];
			checkSingleField(fir);

		}
		if (pos.y > 0) {
			Field fis = board.getAllFields()[pos.x][pos.y - 1];
			checkSingleField(fis);
		}
		if ((pos.y + 1) < board.getAllFields().length) {
			Field fus = board.getAllFields()[pos.x][pos.y + 1];
			checkSingleField(fus);
		}
	}

	private Field calculateNextField() {
		Field minField = null;
		int minValue = Integer.MAX_VALUE;
		for (Field f : openSet) {
			if (minValue > f.getF()) {
				minField = f;
				minValue = f.getF();
			}
		}
		return minField;
	}

	private boolean atGoal() {
		if (startFigur.getField().equals(endFigur.getField())) {
			return true;
		} else {
			return false;
		}
	}

	private void nextStep() {
		stepsCounter++;
		if (atGoal()) {
			System.out.println("Algorithmus fertig");
			// makeEndWayVisible(calculateWay(board.getAllFields()[0][0], new
			// ArrayList<Field>()));
			makeEndWayVisible(endFigur.getField());
			return;
		}
		Field f = calculateNextField();
		System.out.println(startFigur.getBefore());
		startFigur.move(f);
		updateOpenSet();
	}

	private TextButton createButtonforRestart() {
		BitmapFont font = new BitmapFont();
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("images/Button.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("black");
		textButtonStyle.down = skin.getDrawable("black");
		textButtonStyle.checked = skin.getDrawable("black");
		TextButton button = new TextButton("Recreate", textButtonStyle);
		stage.addActor(button);
		button.setBounds(WIDTH - 2 * ONEFIELDSIZE, 0, ONEFIELDSIZE, ONEFIELDSIZE / 2);
		button.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				reset();

			}
		});
		return button;
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

	public void reset() {
		stepsCounter = 0;
		openSet = new OpenSet();
		create();
	}
}
