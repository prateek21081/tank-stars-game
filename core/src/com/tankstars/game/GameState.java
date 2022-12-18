package com.tankstars.game;

import com.badlogic.gdx.Game;

import java.io.*;
import java.util.List;

public class GameState implements Serializable {

   class PlayerState implements Serializable {
      public String name;
      public Integer health;
      public Integer positionX;
      public Integer positionY;
      public String tankName;
   }
   private PlayerState playerStateA;
   private PlayerState playerStateB;

   private String gameID;
   private TankStarsGame game;
   private List<Integer> terrain;

   public GameState(TankStarsGame game) {
      this.game = game;

      this.playerStateA = new PlayerState();
      this.playerStateB = new PlayerState();
   }
   
   public void getState(PlayerState state, Player player) {
      state.name = player.getName();
      Tank tank = player.getTank();
      state.health = tank.getHealth();
      state.positionX = tank.getPositionX();
      state.positionY = tank.getPositionY();
      state.tankName = player.getTankName();
   }

   public void setState(PlayerState playerState, Player player) {
      Tank tank = player.getTank();
      tank.setHealth(playerState.health);
      tank.setPositionX(playerState.positionX);
      tank.setPositionY(playerState.positionY);
   }

   public void saveGame() throws IOException {
        Player playerA = this.game.arena.getPlayerA();
        Player playerB = this.game.arena.getPlayerB();
        getState(playerStateA, playerA);
        getState(playerStateB, playerB);

        this.gameID = "testSaveGame.xyz";
        this.terrain = game.arena.getTerrain().getyCoordinates();

        FileOutputStream fileOutputStream = new FileOutputStream("savedgames/" + this.gameID);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
   }

   public void loadGame(String gameID) throws IOException, ClassNotFoundException {
      FileInputStream fileInputStream = new FileInputStream("savedgames/" + gameID);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      GameState gameState = (GameState) objectInputStream.readObject();

      Player playerA = new Player(this.playerStateA.name, this.playerStateA.tankName, true);
      Player playerB = new Player(this.playerStateB.name, this.playerStateB.tankName, false);

      game.arena = new Arena();
      game.arena.setPlayerA(playerA);
      game.arena.setPlayerA(playerB);
      game.arena.getTerrain().setyCoordinates(this.terrain);

      setState(this.playerStateA, playerA);
      setState(this.playerStateB, playerB);
   }

}
