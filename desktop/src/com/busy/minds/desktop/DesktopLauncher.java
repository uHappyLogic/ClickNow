package com.busy.minds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.busy.minds.ClickNowGame;
/**klasa uruchamiająca grę*/
public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ClickNowGame(), config);
		config.height=480;
		config.width=800;

	}
}
