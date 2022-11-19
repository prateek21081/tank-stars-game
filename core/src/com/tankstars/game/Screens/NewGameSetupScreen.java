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
import org.w3c.dom.Text;

public class NewGameSetupScreen implements Screen {
    private TankStarsGame game;
    private Skin skin;
    private Stage stage;
    private Table table;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("shadeui/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("purple-stripes-dark.jpg"))));
        table.setFillParent(true);
        stage.addActor(table);

        TextField playerName = new TextField("Player One", skin);
        table.add(playerName).pad(50);

        List<String> tankList = new List(skin);
        tankList.setItems(new String[] {"tankA", "tankB", "tankC", "tankD"});
        table.add(tankList).grow();

        table.row();

        TextButton buttonBack = new TextButton("Back", skin);
        buttonBack.getLabel().setFontScale(2, 2);
        table.add(buttonBack).left().pad(20);

        TextButton buttonNext = new TextButton("Next", skin);
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

    }
}
