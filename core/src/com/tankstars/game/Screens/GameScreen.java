package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisProgressBar;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.tankstars.game.TankStarsGame;

public class GameScreen implements Screen {
    private final TankStarsGame game;
    private Stage stage;
    private VisTable root;

    public GameScreen (final TankStarsGame game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new VisTable();
        //root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/tank-stars-pause-menu-bg.jpg"))));
        root.setFillParent(true);
        stage.addActor(root);

        VisTable topHUD = new VisTable();
        VisTable btmHUD = new VisTable();
        VisTable gameView = new VisTable();

        VisProgressBar healthPlayerA = new VisProgressBar(70, 100, 1, false);
        topHUD.add(healthPlayerA).expand().right().top().space(30).padTop(30);
        VisTextButton pauseButton = new VisTextButton("||");
        topHUD.add(pauseButton).top().space(30).padTop(30);
        VisProgressBar healthPlayerB = new VisProgressBar(25, 100, 1, false);
        topHUD.add(healthPlayerB).expand().left().top().space(30).padTop(30);

        root.add(topHUD).grow();
        root.row();
        root.add(gameView).grow();
        root.row();
        root.add(btmHUD).grow();

        VisSlider angleSlider = new VisSlider(0, 359, 1, false);
        btmHUD.add(angleSlider).expand().right().bottom().space(30).padBottom(30);
        VisTextButton fireButton = new VisTextButton("Fire");
        btmHUD.add(fireButton).bottom().space(30).padBottom(30);
        VisSlider powerSlider = new VisSlider(0, 100, 1, false);
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
