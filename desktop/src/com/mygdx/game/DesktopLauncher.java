package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		TankStars newGame = TankStars.getInstance();
		config.setTitle("Tank Stars");
		config.setWindowedMode(newGame.getWindowX(),newGame.getWindowY());
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(newGame, config);
	}
}