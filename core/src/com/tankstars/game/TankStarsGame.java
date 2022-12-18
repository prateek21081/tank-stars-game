package com.tankstars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tankstars.game.Screens.MainMenuScreen;

public class TankStarsGame extends Game {
	public static final int VIEWPORT_WIDTH = 997;
	public static final int VIEWPORT_HEIGHT = 731;
	public Skin skin;
	public OrthographicCamera camera;
	public Viewport viewport;
	public SpriteBatch batch;
	public TextureAtlas textureAtlas;
	public Animation<TextureRegion> animation;
	private float elapsedTime;
	public GameState gameState;
	public Arena arena;

	@Override
	public void create () {
		batch = new SpriteBatch();
		skin = new Skin(Gdx.files.internal("skin-cloud/skin/cloud-form-ui.json"));

		textureAtlas = new TextureAtlas(Gdx.files.internal("background/background.atlas"));
		animation = new Animation(1/5f, textureAtlas.getRegions());
		elapsedTime = 0f;

		gameState = new GameState(this);

		camera =  new OrthographicCamera();
		viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
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

	public void createArena(Player playerA, Player playerB) {
		this.arena = new Arena();
		this.arena.setPlayerA(playerA);
		this.arena.setPlayerB(playerB);
	}

	public void renderBackground(float delta) {
		elapsedTime += delta;
		ScreenUtils.clear(0, 0, 0, 0);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);
		batch.end();
	}
}
