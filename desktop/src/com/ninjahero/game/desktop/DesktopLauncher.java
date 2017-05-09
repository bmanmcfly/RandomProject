package com.ninjahero.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ninjahero.game.InitGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
//		This line removes the title bar
//		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.vSyncEnabled = true;
		config.foregroundFPS = 60;
		config.backgroundFPS = 0;
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new InitGame(), config);
	}
}
