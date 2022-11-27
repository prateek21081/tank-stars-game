package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.Screens.MainMenuScreen;

public class TankStarsGame extends Game {
	private Player playerA = null;
	private Player playerB = null;

	public Skin skinCustom;
	public Skin skinDefault;

	OrthographicCamera camera;
	public Viewport viewport;
	@Override
	public void create () {
		skinCustom = new Skin(Gdx.files.internal("skin-custom/shimmer-ui.json"));
		skinDefault = new Skin(Gdx.files.internal("skin-default/shimmer-ui.json"));
		camera =  new OrthographicCamera();
		viewport = new FitViewport(1366, 768, camera);
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		skinCustom.dispose();
		skinDefault.dispose();
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
