package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.tankstars.game.Screens.MainMenuScreen;

public class TankStarsGame extends Game {
	@Override
	public void create () {
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
