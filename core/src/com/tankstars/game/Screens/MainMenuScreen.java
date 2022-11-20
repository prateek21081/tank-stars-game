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
import org.w3c.dom.Text;

public class MainMenuScreen implements Screen {
    private final TankStarsGame game;
    private Skin skin;
    private Stage stage;
    private Table table;

    TextButton buttonNewGame;
    TextButton buttonLoadGame;
    TextButton buttonExitGame;

    public MainMenuScreen (final TankStarsGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantumhorizonui/quantum-horizon-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("tank-stars-banner.jpg"))));
        table.setFillParent(true);
        stage.addActor(table);

        buttonNewGame = new TextButton("New Game", skin);
        buttonLoadGame = new TextButton("Load Game", skin);
        buttonExitGame = new TextButton("Exit", skin);
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
