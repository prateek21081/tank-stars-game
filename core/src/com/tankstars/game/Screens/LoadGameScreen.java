package com.tankstars.game.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.tankstars.game.Player;
import com.tankstars.game.TankStarsGame;
import java.io.File;
import java.io.IOException;

public class LoadGameScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;
    private final TextButton buttonNext;
    private final TextButton buttonBack;
    private List<String> savedGames;
    public LoadGameScreen (final TankStarsGame game) {
        this.game = game;

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
//        root.setDebug(true, true);
        stage.addActor(root);

        Table rowOne = new Table();
        Table rowTwo = new Table();
        root.add(rowOne).grow();
        root.row();
        root.add(rowTwo).growX();

        File folder = new File("savedgames/");
        File[] listOfFiles = folder.listFiles();

        String[] fileNames = new String[listOfFiles.length];
        for (int i = 0; i < listOfFiles.length; i++) {
            fileNames[i] = listOfFiles[i].getName();
        }

        savedGames = new List<String>(game.skin);
        savedGames.setItems(fileNames);
        rowOne.add(savedGames).padTop(100f).padBottom(100f).padRight(400f).padLeft(400f).grow();
        rowOne.row();

        buttonNext = new TextButton("Play", game.skin);
        buttonNext.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                // Load player data.
                try {
                    game.gameState.loadGame(savedGames.getSelected());
                } catch (IOException e) {
//                    throw new IOException(e);
                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
                }
                Player playerA = new Player(game.gameState.getPlayerStateA().name, game.gameState.getPlayerStateA().tankName, true);
                Player playerB = new Player(game.gameState.getPlayerStateB().name, game.gameState.getPlayerStateB().tankName, false);
                game.createArena(playerA, playerB);
                game.arena.getTerrain().setyCoordinates(game.gameState.getTerrain());
                game.gameState.setState(game.gameState.getPlayerStateA(), playerA);
                game.gameState.setState(game.gameState.getPlayerStateB(), playerB);
                game.arena.getTerrain().generateTexture();
                game.arena.updateTerrain();
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

        rowTwo.add(buttonBack).expandX().left().pad(30f);
        rowTwo.add(buttonNext).expandX().right().pad(30f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.renderBackground(delta);
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
