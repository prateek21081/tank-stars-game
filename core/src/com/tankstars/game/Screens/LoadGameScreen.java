package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tankstars.game.TankStarsGame;

public class LoadGameScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;
    private final TextButton buttonNext;
    private final TextButton buttonBack;
    public LoadGameScreen (final TankStarsGame game) {
        this.game = game;

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/background.png"))));
        root.setFillParent(true);
        root.setDebug(true, true);
        stage.addActor(root);

        buttonNext = new TextButton("Next", game.skinCustom);
        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                // Load player data.
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        buttonBack = new TextButton("Back", game.skinCustom);
        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        root.defaults().expand().pad(30f);
        root.add(buttonBack).left();
        root.add(buttonNext).right();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
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
        stage.dispose();
    }
}
