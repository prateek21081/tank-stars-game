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
    private Skin skin;

    SelectBox<String> tankListA;
    SelectBox<String> tankListB;
    SpriteDrawable tankCoalition = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition.png"))));
    SpriteDrawable tankHelios = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios.png"))));
    SpriteDrawable tankMark1 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1.png"))));

    TextField player1Name;
    TextField player2Name;

    TextButton buttonBack;
    TextButton buttonNext;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/shimmer-ui.json"));
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setDebug(true, true);
        //root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/purple-stripes-dark.jpg"))));
        root.setFillParent(true);
        stage.addActor(root);

        Table rowOne = new Table();
        Table rowOneColOne = new Table();
        Table rowTwo = new Table();
        Table rowOneColTwo = new Table();

        Table navElements = new Table();
        rowOne.setDebug(true, true);
        rowTwo.setDebug(true, true);
        navElements.setDebug(true, true);

        root.add(rowOne).grow();
        root.row();
        root.add(rowTwo).grow();
        root.row();
        root.add(navElements).grow();

        Label nameLabel1 = new Label("Enter player 1 name: ", skin);
        rowOne.add(nameLabel1).spaceRight(10);
        player1Name = new TextField("Player", skin);
        rowOne.add(player1Name).prefWidth(400f);

        Label nameLabel2 = new Label("Enter player 2 name: ", skin);
        rowOne.add(nameLabel2).spaceRight(10);
        player2Name = new TextField("Player", skin);
        rowOne.add(player2Name).prefWidth(400f);

        //tankList = new List(skin);
        tankListA = new SelectBox<String>(skin);
        tankListA.setItems(new String[] {"Coalition", "Helios", "Mark 1"});
        rowTwo.add(tankListA).expandX().fillX().padLeft(30);

        tankListB = new SelectBox<String>(skin);
        tankListB.setItems(new String[] {"Coalition", "Helios", "Mark 1"});
        rowTwo.add(tankListB).expandX().fillX().padLeft(30);

        rowTwo.row();
        final Image imageA = new Image(tankCoalition);
        rowTwo.add(imageA).expandX();

        final Image imageB = new Image(tankCoalition);
        rowTwo.add(imageB).expandX();

        // Back and Next Buttons
        navElements.defaults().expand().pad(30f);
        buttonBack = new TextButton("Back", skin);
        buttonBack.getLabel();
        buttonNext = new TextButton("Next", skin);
        buttonNext.getLabel();
        navElements.add(buttonBack).left();
        navElements.add(buttonNext).right();

        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                if (game.getPlayerA() != null) {
                    game.setPlayerA(null);
                    game.setScreen(new NewGameSetupScreen(game));
                } else {
                    game.setScreen(new MainMenuScreen(game));
                }
                dispose();
            }
        });

        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                Player player = new Player(player1Name.getText(), tankListA.getSelected());
                if (game.getPlayerA() == null) {
                    game.setPlayerA(player);
                    game.setScreen(new NewGameSetupScreen(game));
                } else if (game.getPlayerB() == null) {
                    game.setPlayerB(player);
                    game.setScreen(new GameScreen(game));
                }
                dispose();
            }
        });

        tankListA.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String tankName = tankListA.getSelected();
                switch (tankName) {
                    case "Coalition":
                        imageA.setDrawable(tankCoalition);
                        break;
                    case "Helios":
                        imageA.setDrawable(tankHelios);
                        break;
                    case "Mark 1":
                        imageA.setDrawable(tankMark1);
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
