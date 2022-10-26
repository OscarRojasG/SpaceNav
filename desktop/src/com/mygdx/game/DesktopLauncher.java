package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
public class DesktopLauncher {
	private final static int WIDTH = 1280, HEIGHT = 720;
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config =
				new Lwjgl3ApplicationConfiguration();
			config.setForegroundFPS(60);
			config.setTitle("Space Navigation");
			config.setWindowedMode(WIDTH, HEIGHT);
			config.setResizable(false);

		new Lwjgl3Application(new SpaceNav(), config);
	}
}