package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.tankstars.game.TankStarsGame;

public class GameScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;
    private final ProgressBar healthPlayerA;
    private final TextButton pauseButton;
    private final ProgressBar healthPlayerB;
    private final Label angleLabel;
    private final Slider angleSlider;
    private final TextButton fireButton;
    private final Slider powerSlider;
    private final Label powerLabel;

    public GameScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/game-background.png"))));
        root.setFillParent(true);
        stage.addActor(root);

        Table topHUD = new Table();
        Table btmHUD = new Table();

        topHUD.setDebug(true, true);
        btmHUD.setDebug(true, true);

        healthPlayerA = new ProgressBar(0, 100, 1, false, game.skinCustom);
        topHUD.add(healthPlayerA).expand().right().top().space(30).padTop(30);
        healthPlayerA.setValue(100);

        pauseButton = new TextButton("||", game.skinDefault);
        topHUD.add(pauseButton).top().space(30).padTop(30);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InGameMenuScreen(game));
                dispose();
            }
        });

        healthPlayerB = new ProgressBar(0, 100, 1, false, game.skinCustom);
        topHUD.add(healthPlayerB).expand().left().top().space(30).padTop(30);
        healthPlayerB.setValue(100);

        angleLabel = new Label("Angle : XX", game.skinCustom);
        btmHUD.add(angleLabel).expandY().right().bottom().space(30).padBottom(30);

        angleSlider = new Slider(0, 359, 1, false, game.skinCustom);
        btmHUD.add(angleSlider).expandY().right().bottom().space(30).padBottom(30);
        angleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                //System.out.println(slider.getValue());
            }
        });

        fireButton = new TextButton("Fire", game.skinCustom);
        btmHUD.add(fireButton).bottom().space(30).padBottom(30);
        fireButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Tank fire
            }
        });

        powerSlider = new Slider(0, 100, 1, false, game.skinCustom);
        btmHUD.add(powerSlider).expandY().left().bottom().space(30).padBottom(30);
        powerSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                //System.out.println(slider.getValue());
            }
        });

        powerLabel = new Label("Power : XX", game.skinCustom);
        btmHUD.add(powerLabel).expandY().left().bottom().space(30).padBottom(30);

        root.add(topHUD).grow().row();
        root.add(btmHUD).grow();
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
