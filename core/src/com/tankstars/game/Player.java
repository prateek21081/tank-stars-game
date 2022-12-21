package com.tankstars.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
    public Boolean isMain;
    private String name;
    private String tankName;
    private Tank tank;
    private World world;

    public Player(String playerName, String tankName, Boolean isMain) {
        this.name = playerName;
        this.tankName = tankName;
        this.isMain = isMain;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getTankName() {
        return tankName;
    }

    public Tank getTank() {
        return tank;
    }

    public void createTank () {
        this.tank = TankFactory.getTank(this, this.tankName);
    }

    public void play () {

    }

    public void fire(float power, float angle) {
        this.tank.fire(power, angle);
    }

    public void handleHit(int damage) {
        tank.handleHit(damage);
    }

    public boolean checkDead() {
        if (tank.getHealth() <= 0) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public void update(SpriteBatch batch) {
        tank.updateImage(batch);
        tank.updateWeaponImage(batch);
    }
}
