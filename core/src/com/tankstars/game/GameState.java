package com.tankstars.game;

import java.io.*;
import java.util.List;

public class GameState implements Serializable {
    private transient static GameState gameState;
    private String gameID;
    private List<Integer> terrain;
    private PlayerState playerStateA;
    private PlayerState playerStateB;

    public List<Integer> getTerrain() {
        return terrain;
    }

    public PlayerState getPlayerStateA() {
        return playerStateA;
    }

    public PlayerState getPlayerStateB() {
        return playerStateB;
    }

   private GameState() {

      this.playerStateA = new PlayerState();
      this.playerStateB = new PlayerState();
   }

   public static GameState getInstance() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
   }
   
   public void getState(PlayerState state, Player player) {
      state.name = player.getName();
      state.tankName = player.getTankName();

      Tank tank = player.getTank();
      state.health = tank.getHealth();
      state.positionX = tank.getPositionX();
      state.positionY = tank.getPositionY();
   }

   public void setState(PlayerState playerState, Player player) {
      Tank tank = player.getTank();
      tank.setHealth(playerState.health);
      tank.setPositionX(playerState.positionX);
      tank.setPositionY(playerState.positionY);
   }

   public void saveGame(TankStarsGame game) throws IOException {
        Player playerA = game.arena.getPlayerA();
        Player playerB = game.arena.getPlayerB();
        getState(this.playerStateA, playerA);
        getState(this.playerStateB, playerB);

        this.gameID = "testSaveGame.xyz";
        this.terrain = game.arena.getTerrain().getyCoordinates();

        FileOutputStream fileOutputStream = new FileOutputStream("savedgames/" + this.gameID);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
   }

   public void loadGame(String gameID) throws IOException, ClassNotFoundException {
      FileInputStream fileInputStream = new FileInputStream("savedgames/" + gameID);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      GameState gameState = (GameState) objectInputStream.readObject();
      objectInputStream.close();
      fileInputStream.close();

      this.gameID = gameState.gameID;
      this.terrain = gameState.terrain;
      this.playerStateA = gameState.playerStateA;
      this.playerStateB = gameState.playerStateB;
   }

}
