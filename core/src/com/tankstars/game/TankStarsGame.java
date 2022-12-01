package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.Screens.MainMenuScreen;

public class TankStarsGame extends Game {
	private Player playerA = null;
	private Player playerB = null;

	public Skin skin;

	public OrthographicCamera camera;
	public Viewport viewport;

	public SpriteBatch batch;
	public TextureAtlas textureAtlas;
	public Animation<TextureRegion> animation;
	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin-craftacular/craftacular-ui.json"));

		textureAtlas = new TextureAtlas(Gdx.files.internal("background/background.atlas"));
		animation = new Animation(1/10f, textureAtlas.getRegions());

		camera =  new OrthographicCamera();
		viewport = new FitViewport(1600, 731, camera);
		viewport.apply();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		skin.dispose();
		skin.dispose();
		textureAtlas.dispose();
		batch.dispose();
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
