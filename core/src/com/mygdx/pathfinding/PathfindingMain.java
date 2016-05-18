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

	private void makeEndWayVisible(ArrayList<Field> fes){
		for(Field f : fes){
			f.setDrawable(new SpriteDrawable(new Sprite(new Texture("images/red.jpg"))));
		}
	}
	
	private ArrayList<Field> calculateWay(Field f, ArrayList<Field> rekList){
		ArrayList<Field> copyList = rekList;
		
		
		Vec2 pos = board.getFieldPos(f);
		Field curr = board.getAllFields()[pos.x][pos.y];
		copyList.add(curr);
		if(endFigur.getField().equals(f)){
			return copyList;
		}
		ArrayList<Field> tempList = new ArrayList<Field>();
		if ((pos.x + 1) < board.getAllFields()[1].length && board.getAllFields()[pos.x + 1][pos.y].getTurn() != -1 ) {
			tempList.add(board.getAllFields()[pos.x + 1][pos.y]);
		}
		if (pos.x > 0  && board.getAllFields()[pos.x - 1][pos.y].getTurn() != -1) {
			tempList.add(board.getAllFields()[pos.x -1][pos.y]);
		}
		if (pos.y > 0  && board.getAllFields()[pos.x][pos.y -1].getTurn() != -1) {
			tempList.add(board.getAllFields()[pos.x][pos.y - 1]);
		}
		if ((pos.y + 1 ) < board.getAllFields().length && board.getAllFields()[pos.x][pos.y + 1].getTurn() != -1) {
			tempList.add(board.getAllFields()[pos.x][pos.y + 1]);
		}
		Field nextField = Collections.max(tempList);
		
		if(copyList.contains(nextField)){
		Field fis = copyList.get(copyList.indexOf(f) - 1);
			copyList.remove(f);
			f.setTurn(-1);
			return calculateWay(nextField, copyList);
		}
		return calculateWay(nextField, copyList);
		
	}
	
	private void updateOpenSet() {
		// System.out.println(board.getFieldPos(startFigur.getBefore()).x);
		stage.getActors().removeValue(startFigur.getBefore().getLabel(), false);
		openSet.remove(startFigur.getField());
		startFigur.getField().setDrawable(new SpriteDrawable(new Sprite(new Texture("images/black.png"))));
		Vec2 pos = board.getFieldPos(startFigur.getField());
		
		if ((pos.x + 1) < board.getAllFields()[1].length && board.getAllFields()[pos.x + 1][pos.y].isMoveable()) {
			Field fi = board.getAllFields()[pos.x + 1][pos.y];
			openSet.add(fi);
			stage.addActor(fi.calculateValues(startFigur.getBefore(), endFigur));
		}
		if (pos.x > 0 && board.getAllFields()[pos.x - 1][pos.y].isMoveable()) {
			Field fir = board.getAllFields()[pos.x - 1][pos.y];
			openSet.add(fir);
			stage.addActor(fir.calculateValues(startFigur.getBefore(), endFigur));
		}
		if (pos.y > 0 && board.getAllFields()[pos.x ][pos.y - 1].isMoveable()) {
			Field fis = board.getAllFields()[pos.x][pos.y - 1];
			openSet.add(fis);
			stage.addActor(fis.calculateValues(startFigur.getBefore(), endFigur));
		}
		if ((pos.y + 1) < board.getAllFields().length && board.getAllFields()[pos.x][pos.y + 1].isMoveable()) {
			Field fus = board.getAllFields()[pos.x][pos.y + 1];
			openSet.add(fus);
			stage.addActor(fus.calculateValues(startFigur.getBefore(), endFigur));
		}
	}

	private Field calculateNextField() {
		Field maxField = null;
		int maxValue = Integer.MAX_VALUE;
		for (Field f : openSet) {
			if (maxValue > f.getF()) {
				maxField = f;
				maxValue = f.getF();
			}
		}

		return maxField; 	
	}

	private boolean atGoal(){
		if(startFigur.getField().equals(endFigur.getField())){
			return true;
		}else{
			return false;
		}
	}
	private void nextStep() {
		stepsCounter++;
		if(atGoal()){
			System.out.println("Algorithmus fertig");
			makeEndWayVisible(calculateWay(board.getAllFields()[0][0], new ArrayList<Field>()));
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
public void reset(){
	stepsCounter = 0;
	openSet = new OpenSet();
	create();
}
}
