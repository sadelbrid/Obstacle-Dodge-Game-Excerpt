package com.blinkideacompany.pop.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MyGdxGame.DESKTOP_WIDTH;
		config.height = MyGdxGame.DESKTOP_HEIGHT;
		config.title = "Pop";
		new LwjglApplication(new MyGdxGame(), config);
}
}

