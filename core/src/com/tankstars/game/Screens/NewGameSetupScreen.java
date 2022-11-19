package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tankstars.game.TankStarsGame;

public class NewGameSetupScreen implements Screen {
    private TankStarsGame game;
    private Skin skin;
    private Stage stage;
    private Table table;

    List<String> tankList;
    TextField playerName;

    TextButton buttonBack;
    TextButton buttonNext;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("quantumhorizonui/quantum-horizon-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("purple-stripes-dark.jpg"))));
        table.setFillParent(true);
        stage.addActor(table);

        playerName = new TextField("Player One", skin);
        table.add(playerName).pad(50);

        tankList = new List(skin);
        tankList.setItems(new String[] {"tankA", "tankB", "tankC", "tankD"});
        table.add(tankList).grow();

        table.row();

        buttonBack = new TextButton("Back", skin);
        buttonBack.getLabel().setFontScale(2, 2);
        table.add(buttonBack).left().pad(20);

        buttonNext = new TextButton("Next", skin);
        buttonNext.getLabel().setFontScale(2, 2);
        table.add(buttonNext).right().pad(20);

        buttonBack.addListener(new ClickListener() {
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
