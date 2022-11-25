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
import com.tankstars.game.Player;
import com.tankstars.game.TankStarsGame;

public class NewGameSetupScreen implements Screen {
    private TankStarsGame game;
    private Stage stage;
    private Table root;

    SelectBox<String> tankSelectA;
    SelectBox<String> tankSelectB;
    SpriteDrawable tankCoalitionA = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition.png"))));
    SpriteDrawable tankHeliosA = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios.png"))));
    SpriteDrawable tankMark1A = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1.png"))));

    SpriteDrawable tankCoalitionB = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition-mirrored.png"))));
    SpriteDrawable tankHeliosB = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios-mirrored.png"))));
    SpriteDrawable tankMark1B = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1-mirrored.png"))));
    TextField player1Name;
    TextField player2Name;

    TextButton buttonBack;
    TextButton buttonNext;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        //root.setDebug(true, true);
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/background.png"))));
        root.setFillParent(true);
        stage.addActor(root);
        root.padTop(50);

        Table rowOne = new Table();
        Table rowTwo = new Table();

        Table navElements = new Table();
//         rowOne.setDebug(true, true);
//         rowTwo.setDebug(true, true);
//         navElements.setDebug(true, true);

        root.add(rowOne).grow();
        root.row();
        root.add(rowTwo).grow();
        root.row();
        root.add(navElements).grow();

        Label nameLabel1 = new Label("Player One", game.skinCustom);
        rowOne.add(nameLabel1).spaceRight(10);
        player1Name = new TextField("Player A", game.skinCustom);
        rowOne.add(player1Name).spaceRight(400).prefWidth(200f);

        Label nameLabel2 = new Label("Player Two", game.skinCustom);
        rowOne.add(nameLabel2).spaceRight(10).spaceLeft(400);
        player2Name = new TextField("Player B", game.skinCustom);
        rowOne.add(player2Name).prefWidth(200f);

        //tankList = new List(skin);
        tankSelectA = new SelectBox<String>(game.skinCustom);
        tankSelectA.setItems(new String[] {"Coalition", "Helios", "Mark 1"});
        rowTwo.add(tankSelectA).growX().padLeft(200).padRight(200);

        tankSelectB = new SelectBox<String>(game.skinCustom);
        tankSelectB.setItems(new String[] {"Helios", "Coalition", "Mark 1"});
        rowTwo.add(tankSelectB).growX().padLeft(200).padRight(200);

        rowTwo.row();
        final Image imageA = new Image(tankCoalitionA);
        rowTwo.add(imageA).expandX();

        final Image imageB = new Image(tankHeliosB);
        rowTwo.add(imageB).expandX();

        // Back and Next Buttons
        navElements.defaults().expand().pad(30f);
        buttonBack = new TextButton("Back", game.skinCustom);
        buttonBack.getLabel();
        buttonNext = new TextButton("Next", game.skinCustom);
        buttonNext.getLabel();
        navElements.add(buttonBack).left();
        navElements.add(buttonNext).right();

        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setPlayerA(new Player(player1Name.getText(), tankSelectA.getSelected()));
                game.setPlayerB(new Player(player2Name.getText(), tankSelectB.getSelected()));
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        tankSelectA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String tankName = tankSelectA.getSelected();
                switch (tankName) {
                    case "Coalition":
                        imageA.setDrawable(tankCoalitionA);
                        break;
                    case "Helios":
                        imageA.setDrawable(tankHeliosA);
                        break;
                    case "Mark 1":
                        imageA.setDrawable(tankMark1A);
                        break;
                }
            }
        });

        tankSelectB.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String tankName = tankSelectB.getSelected();
                switch (tankName) {
                    case "Coalition":
                        imageB.setDrawable(tankCoalitionB);
                        break;
                    case "Helios":
                        imageB.setDrawable(tankHeliosB);
                        break;
                    case "Mark 1":
                        imageB.setDrawable(tankMark1B);
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
