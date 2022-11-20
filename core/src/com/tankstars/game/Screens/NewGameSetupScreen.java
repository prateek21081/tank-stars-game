package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tankstars.game.TankStarsGame;

public class NewGameSetupScreen implements Screen {
    private TankStarsGame game;
    private Skin skin;
    private Stage stage;
    private Table table;
    private Table rowOne;
    private Table rowTwo;
    private Table rowThree;

    List<String> tankList;
    SpriteDrawable tankCoalition = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank-coalition.png"))));
    SpriteDrawable tankHelios = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank-helios.png"))));
    SpriteDrawable tankMark1 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank-mark-1.png"))));

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
        table.setDebug(true);
        stage.addActor(table);

        rowOne = new Table();
//        rowOne.setDebug(true);
        rowTwo = new Table();
//        rowTwo.setDebug(true);
        rowThree = new Table();
//        rowThree.setDebug(true);

        table.add(rowOne).grow();
        table.row();
        table.add(rowTwo).grow();
        table.row();
        table.add(rowThree).grow();

        Label nameLabel = new Label("Enter name: ", skin);
        rowOne.add(nameLabel).spaceRight(10);
        playerName = new TextField("Player One", skin);
        rowOne.add(playerName).prefWidth(400f);

        tankList = new List(skin);
        tankList.setItems(new String[] {"Coalition", "Helios", "Mark 1"});
        rowTwo.add(tankList).expandX().fillX().padLeft(30);

        final Image image = new Image(tankCoalition);
        rowTwo.add(image).expandX();

        buttonBack = new TextButton("Back", skin);
        buttonBack.getLabel();
        rowThree.add(buttonBack).expandX().left().padLeft(20);

        buttonNext = new TextButton("Next", skin);
        buttonNext.getLabel();
        rowThree.add(buttonNext).expandX().right().padRight(20);

        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        tankList.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String tankName = tankList.getSelected();
                switch (tankName) {
                    case "Coalition":
                        image.setDrawable(tankCoalition);
                        break;
                    case "Helios":
                        image.setDrawable(tankHelios);
                        break;
                    case "Mark 1":
                        image.setDrawable(tankMark1);
                        break;
                }
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
