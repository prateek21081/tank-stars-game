package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tankstars.game.TankStarsGame;

public class MainMenuScreen implements Screen {
    private final TankStarsGame game;
    private Stage stage;
    private Table root;
    private Image logo;

    private TextButton buttonNewGame;
    private TextButton buttonLoadGame;
    private TextButton buttonExitGame;

    public MainMenuScreen (final TankStarsGame game) {
        this.game = game;

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/background.png"))));
        root.setFillParent(true);
        root.setDebug(true, true);
        stage.addActor(root);

        logo = new Image(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("logo.png")))));
        root.add(logo).row();

        buttonNewGame = new TextButton("New Game", game.skinCustom);
        buttonNewGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new NewGameSetupScreen(game));
                dispose();
            }
        });

        buttonExitGame = new TextButton("Exit", game.skinDefault);
        buttonExitGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        buttonLoadGame = new TextButton("Load Game", game.skinDefault);
        buttonLoadGame.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new LoadGameScreen(game));
                dispose();
            }
        });

        root.defaults().width(160).height(80).space(30);
        root.add(buttonNewGame).row();
        root.add(buttonLoadGame).row();
        root.add(buttonExitGame);
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
