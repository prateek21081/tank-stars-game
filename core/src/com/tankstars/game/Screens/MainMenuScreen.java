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
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.tankstars.game.TankStarsGame;
import org.w3c.dom.Text;

public class MainMenuScreen implements Screen {
    private final TankStarsGame game;
    private Stage stage;
    private VisTable table;

    VisTextButton buttonNewGame;
    VisTextButton buttonLoadGame;
    VisTextButton buttonExitGame;

    public MainMenuScreen (final TankStarsGame game) {
        this.game = game;

        // reset player status here for now.
        game.setPlayerA(null);
        game.setPlayerB(null);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new VisTable();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/tank-stars-banner.jpg"))));
        table.setFillParent(true);
        stage.addActor(table);

        buttonNewGame = new VisTextButton("New Game");
        buttonLoadGame = new VisTextButton("Load Game");
        buttonExitGame = new VisTextButton("Exit");
        buttonNewGame.getLabel();
        buttonLoadGame.getLabel();
        buttonExitGame.getLabel();

        buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new NewGameSetupScreen(game));
                dispose();
            }
        });
        buttonExitGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonLoadGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game));
                dispose();
            }
        });

        table.defaults()
                .width(200)
                .height(100)
                .space(10);

        table.add(buttonNewGame);
        table.add(buttonLoadGame);
        table.add(buttonExitGame);
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
