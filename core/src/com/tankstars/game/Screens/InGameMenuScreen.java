package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tankstars.game.TankStarsGame;

import java.io.IOException;

public class InGameMenuScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;

    private final TextButton buttonRestartGame;
    private final TextButton buttonSaveGame;
    private final TextButton buttonMainMenu;

    public InGameMenuScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        buttonRestartGame = new TextButton("Restart Game", game.skin);
        buttonRestartGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        buttonSaveGame = new TextButton("Save game", game.skin);
        buttonSaveGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                try {
                    game.gameState.saveGame();
                } catch (IOException e) {
//                    throw new RuntimeException(e);
                }
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });


        buttonMainMenu = new TextButton("Main Menu", game.skin);
        buttonMainMenu.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        root.defaults().width(200).height(100).space(10);
        root.add(buttonRestartGame);
        root.add(buttonSaveGame);
        root.add(buttonMainMenu);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.renderBackground(delta);

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

