package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tankstars.game.TankStarsGame;

public class GameScreen implements Screen {
    private final TankStarsGame game;
    private Stage stage;
    private Table root;

    public GameScreen (final TankStarsGame game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/background.png"))));

        Table topHUD = new Table();
        Table btmHUD = new Table();
        Table gameView = new Table();

        ProgressBar healthPlayerA = new ProgressBar(0, 100, 1, false, game.skinCustom);
        healthPlayerA.setValue(100);
        topHUD.add(healthPlayerA).expand().right().top().space(30).padTop(30);
        TextButton pauseButton = new TextButton("||", game.skinDefault);
        topHUD.add(pauseButton).top().space(30).padTop(30);
        ProgressBar healthPlayerB = new ProgressBar(0, 100, 1, false, game.skinCustom);
        healthPlayerB.setValue(100);
        topHUD.add(healthPlayerB).expand().left().top().space(30).padTop(30);

        root.add(topHUD).grow();
        root.row();
        root.add(gameView).grow();
        root.row();
        root.add(btmHUD).grow();

        Slider angleSlider = new Slider(0, 359, 1, false, game.skinCustom);
        btmHUD.add(angleSlider).expand().right().bottom().space(30).padBottom(30);
        TextButton fireButton = new TextButton("Fire", game.skinCustom);
        btmHUD.add(fireButton).bottom().space(30).padBottom(30);
        Slider powerSlider = new Slider(0, 100, 1, false, game.skinCustom);
        btmHUD.add(powerSlider).expand().left().bottom().space(30).padBottom(30);


        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InGameMenuScreen(game));
                dispose();
            }
        });

        fireButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Tank fire
            }
        });

        angleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                //System.out.println(slider.getValue());
            }
        });

        powerSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                //System.out.println(slider.getValue());
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

    }
}
