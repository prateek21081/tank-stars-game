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

   public PlayerState getPlayerStateA() {
      return playerStateA;
   }

   public PlayerState getPlayerStateB() {
      return playerStateB;
   }

   public List<Integer> getTerrain() {
      return terrain;
   }

   public GameState(TankStarsGame game) {
      this.game = game;

      this.playerStateA = new PlayerState();
      this.playerStateB = new PlayerState();
   }
   
   public void setState(PlayerState state, Player player) {
      state.name = player.getName();
      Tank tank = player.getTank();
      state.health = tank.getHealth();
      state.positionX = tank.getPositionX();
      state.positionY = tank.getPositionY();
      state.tankName = player.getTankName();
   }

   public void saveGame() throws IOException {
        Player playerA = this.game.arena.getPlayerA();
        Player playerB = this.game.arena.getPlayerB();
        setState(playerStateA, playerA);
        setState(playerStateB, playerB);
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
      setGame(gameState);
   }

   public void setGame(GameState gameState) {
      Player playerA = new Player(gameState.getPlayerStateA().name, gameState.getPlayerStateA().tankName, true);
      Player playerB = new Player(gameState.getPlayerStateB().name, gameState.getPlayerStateB().tankName, false);
      game.createArena(playerA, playerB);
      game.arena.getTerrain().setyCoordinates(gameState.getTerrain());
      playerA.getTank().setHealth(gameState.getPlayerStateA().health);
      playerB.getTank().setHealth(gameState.getPlayerStateB().health);
      playerA.getTank().setPositionX(gameState.getPlayerStateA().positionX);
      playerA.getTank().setPositionY(gameState.getPlayerStateA().positionX);
      playerB.getTank().setPositionX(gameState.getPlayerStateB().positionX);
      playerB.getTank().setPositionY(gameState.getPlayerStateB().positionY);
   }
}
