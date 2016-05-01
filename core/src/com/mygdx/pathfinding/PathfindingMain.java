package com.mygdx.pathfinding;


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

	public static int WIDTH = 800, HEIGHT = 800;
	public static int ONEFIELDSIZE = 100;
	public static String TITLE = "pathfinding";

	private Stage stage;

	private Board board;

	private OpenSet openSet = new OpenSet();
//	private HashSetyFeld> closedList = new HashSet<Field>();

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
		for(Field fi : openSet){
			stage.addActor(fi.calculateValues(startFigur.getField(), endFigur));
		}
		createButton();

	}
	
	private void updateOpenSet() {
//		System.out.println(board.getFieldPos(startFigur.getBefore()).x);
		stage.getActors().removeValue(startFigur.getBefore().getLabel(), true);
		openSet.remove(startFigur.getField());
		Vec2 pos = board.getFieldPos(startFigur.getField());
		if((pos.x + 1) < board.getAllFields()[1].length)
		openSet.add(board.getAllFields()[pos.x + 1][pos.y]);
		if(pos.x > 0){
		openSet.add(board.getAllFields()[pos.x - 1][pos.y]);
		}
		if(pos.y > 0){
		openSet.add(board.getAllFields()[pos.x][pos.y - 1]);
		}
		if((pos.y + 1) < board.getAllFields().length){
		openSet.add(board.getAllFields()[pos.x][pos.y + 1]);
		}		
		for(Field fi : openSet){
			stage.addActor(fi.calculateValues(startFigur.getField(), endFigur));
			
		}
//		for(Field f : openSet){
//			if(!f.isMoveable()){
//				openSet.remove(f);
//			}
//		}
	}
	

	private Field calculateNextField() {
		Field maxField = null;
		int maxValue = 0;
		for (Field f : openSet) {
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
		updateOpenSet();
		for(Field fi : openSet){
			System.out.println(board.getFieldPos(fi).x + " " + board.getFieldPos(fi).x + " " + fi.isMoveable());
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
