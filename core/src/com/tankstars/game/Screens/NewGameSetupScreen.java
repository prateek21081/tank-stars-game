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
import com.kotcrab.vis.ui.widget.*;
import com.tankstars.game.Player;
import com.tankstars.game.TankStarsGame;

public class NewGameSetupScreen implements Screen {
    private TankStarsGame game;
    private Stage stage;
    private VisTable root;

    List<String> tankList;
    SpriteDrawable tankCoalition = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition.png"))));
    SpriteDrawable tankHelios = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios.png"))));
    SpriteDrawable tankMark1 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1.png"))));

    VisTextField playerName;

    VisTextButton buttonBack;
    VisTextButton buttonNext;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        root = new VisTable();
        root.setDebug(true, true);
        root.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("background/purple-stripes-dark.jpg"))));
        root.setFillParent(true);
        stage.addActor(root);

        VisTable rowOne = new VisTable();
        VisTable rowTwo = new VisTable();
        VisTable navElements = new VisTable();
        rowOne.setDebug(true, true);
        rowTwo.setDebug(true, true);
        navElements.setDebug(true, true);

        root.add(rowOne).grow();
        root.row();
        root.add(rowTwo).grow();
        root.row();
        root.add(navElements).grow();

        VisLabel nameLabel = new VisLabel("Enter name: ");
        rowOne.add(nameLabel).spaceRight(10);
        playerName = new VisTextField("Player");
        rowOne.add(playerName).prefWidth(400f);

        tankList = new VisList();
        tankList.setItems(new String[] {"Coalition", "Helios", "Mark 1"});
        rowTwo.add(tankList).expandX().fillX().padLeft(30);

        final Image image = new Image(tankCoalition);
        rowTwo.add(image).expandX();

        // Back and Next Buttons
        navElements.defaults().expand().pad(30f);
        buttonBack = new VisTextButton("Back");
        buttonBack.getLabel();
        buttonNext = new VisTextButton("Next");
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
                Player player = new Player(playerName.getText(), tankList.getSelected());
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
