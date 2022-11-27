package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tankstars.game.TankStarsGame;

public class NewGameSetupScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;

//    private final SelectBox<String> tankSelectBoxA;
//    private final SelectBox<String> tankSelectBoxB;

    private final SpriteDrawable tankCoalitionA;
    private final SpriteDrawable tankCoalitionB;
    private final SpriteDrawable tankHeliosA;
    private final SpriteDrawable tankHeliosB;
    private final SpriteDrawable tankMarkOneA;
    private final SpriteDrawable tankMarkOneB;

    private final TextField player1Name;
    private final TextField player2Name;

    private final TextButton buttonNext;
    private final TextButton buttonBack;

    public NewGameSetupScreen (final TankStarsGame game) {
        this.game = game;
        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
        root.padTop(50);

        Table rowOne = new Table();
        Table rowTwo = new Table();
        Table rowThree = new Table();
        Table rowFour = new Table();

        rowOne.setDebug(true, true);
        rowTwo.setDebug(true, true);
        rowThree.setDebug(true, true);
        rowFour.setDebug(true, true);

        root.add(rowOne).grow().row();
        root.add(rowTwo).grow().row();
        root.add(rowThree).grow().row();
        root.add(rowFour).grow();

        Label nameLabel1 = new Label("Player One", game.skin);
        player1Name = new TextField("Player A", game.skin);
        rowOne.add(nameLabel1).spaceRight(10);
        rowOne.add(player1Name).spaceRight(400).prefWidth(200f);

        Label nameLabel2 = new Label("Player Two", game.skin);
        player2Name = new TextField("Player B", game.skin);
        rowOne.add(nameLabel2).spaceRight(10).spaceLeft(400);
        rowOne.add(player2Name).prefWidth(200f);

//        tankSelectBoxA = new SelectBox<String>(game.skinCustom);
//        tankSelectBoxA.setItems("Coalition", "Helios", "Mark 1");
//        rowTwo.add(tankSelectBoxA).growX().padLeft(200).padRight(200);
//
//        tankSelectBoxB = new SelectBox<String>(game.skinCustom);
//        tankSelectBoxB.setItems("Helios", "Mark 1", "Coalition");
//        rowTwo.add(tankSelectBoxB).growX().padLeft(200).padRight(200);

        tankCoalitionA = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition.png"))));
        tankCoalitionB = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/coalition-mirrored.png"))));
        tankHeliosA = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios.png"))));
        tankHeliosB = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/helios-mirrored.png"))));
        tankMarkOneA = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1.png"))));
        tankMarkOneB = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("tank/mark-1-mirrored.png"))));
        final Image imageA = new Image(tankCoalitionA);
        final Image imageB = new Image(tankHeliosB);
        rowThree.add(imageA).expandX();
        rowThree.add(imageB).expandX();

//        tankSelectBoxA.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                String tankName = tankSelectBoxA.getSelected();
//                switch (tankName) {
//                    case "Coalition":
//                        imageA.setDrawable(tankCoalitionA);
//                        break;
//                    case "Helios":
//                        imageA.setDrawable(tankHeliosA);
//                        break;
//                    case "Mark 1":
//                        imageA.setDrawable(tankMarkOneA);
//                        break;
//                }
//            }
//        });
//
//        tankSelectBoxB.addListener(new ChangeListener() {
//            @Override
//            public void changed(ChangeEvent event, Actor actor) {
//                String tankName = tankSelectBoxB.getSelected();
//                switch (tankName) {
//                    case "Coalition":
//                        imageB.setDrawable(tankCoalitionB);
//                        break;
//                    case "Helios":
//                        imageB.setDrawable(tankHeliosB);
//                        break;
//                    case "Mark 1":
//                        imageB.setDrawable(tankMarkOneB);
//                        break;
//                }
//            }
//        });

        buttonNext = new TextButton("Next", game.skin);
        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
//                game.setPlayerA(new Player(player1Name.getText(), tankSelectBoxA.getSelected()));
//                game.setPlayerB(new Player(player2Name.getText(), tankSelectBoxB.getSelected()));
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        buttonBack = new TextButton("Back", game.skin);
        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });

        rowFour.defaults().expand().pad(30f);
        rowFour.add(buttonBack).left();
        rowFour.add(buttonNext).right();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
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
