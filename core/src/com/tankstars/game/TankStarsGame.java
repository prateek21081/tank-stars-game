package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import com.tankstars.game.Screens.MainMenuScreen;

public class TankStarsGame extends Game {
	private Player playerA = null;
	private Player playerB = null;
	@Override
	public void create () {
		VisUI.load(VisUI.SkinScale.X2);
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		VisUI.dispose();
	}

	public Player getPlayerA() {
		return playerA;
	}

	public Player getPlayerB() {
		return playerB;
	}

	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}

	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
}
