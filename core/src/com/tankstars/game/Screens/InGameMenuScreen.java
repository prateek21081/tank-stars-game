package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tankstars.game.TankStarsGame;

public class InGameMenuScreen implements Screen {
    private TankStarsGame game;
    private Skin skin;
    private Stage stage;
    private Table table;

    public InGameMenuScreen (final TankStarsGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantumhorizonui/quantum-horizon-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("tank-stars-pause-menu-bg.jpg"))));
        table.setFillParent(true);
        stage.addActor(table);

        TextButton buttonRestartGame = new TextButton("Restart Game", skin);
        TextButton buttonMainMenu = new TextButton("Main Menu", skin);
        buttonRestartGame.getLabel();
        buttonMainMenu.getLabel();

        table.add(buttonRestartGame)
                .width(200)
                .height(100)
                .space(10);
        table.add(buttonMainMenu)
                .width(200)
                .height(100)
                .space(10);

        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
