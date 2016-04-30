package com.mygdx.pathfinding.desktop;

import java.awt.geom.GeneralPath;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.pathfinding.GrindScreen;
import com.mygdx.pathfinding.PathfindingMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = PathfindingMain.HEIGHT;
		config.width = PathfindingMain.WIDTH;
		config.title = PathfindingMain.TITLE;
		new LwjglApplication(new PathfindingMain(), config);
	}
}
