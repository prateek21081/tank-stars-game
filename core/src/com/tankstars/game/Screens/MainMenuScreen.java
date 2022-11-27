package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStarsGame;

public class MainMenuScreen implements Screen {
    private final TankStarsGame game;
    private Stage stage;
    private Table root;

    private TextButton buttonNewGame;
    private TextButton buttonLoadGame;
    private TextButton buttonExitGame;

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Animation<TextureRegion> animation;
    private float elapsedTime = 0f;

    public MainMenuScreen (final TankStarsGame game) {
        this.game = game;
        batch = new SpriteBatch();

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

//        sprite.setPosition(game.camera.viewportWidth / 2 - sprite.getWidth() / 2, game.camera.viewportHeight /2 - sprite.getHeight() / 2);
        textureAtlas = new TextureAtlas(Gdx.files.internal("background/background.atlas"));
        animation = new Animation(1/10f, textureAtlas.getRegions());

        root = new Table();
        root.setFillParent(true);
//        root.setDebug(true, true);
        stage.addActor(root);

        buttonNewGame = new TextButton("New Game", game.skin);
        buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new NewGameSetupScreen(game));
                dispose();
            }
        });

        buttonExitGame = new TextButton("Exit", game.skin);
        buttonExitGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonLoadGame = new TextButton("Load Game", game.skin);
        buttonLoadGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game));
                dispose();
            }
        });

        root.defaults().width(200).height(80).space(30);
        root.add(buttonNewGame).row();
        root.add(buttonLoadGame).row();
        root.add(buttonExitGame);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsedTime += delta;
        ScreenUtils.clear(0, 0, 0, 0);
        batch.setProjectionMatrix(game.camera.combined);
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsedTime, true), 0, 0);
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
        game.camera.position.set(game.camera.viewportWidth / 2, game.camera.viewportHeight / 2, 0);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
        stage.dispose();
    }
}
