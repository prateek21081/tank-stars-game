package com.tankstars.game;

import com.badlogic.gdx.physics.box2d.World;

public class TankFactory {
    public static Tank getTank(Player player, String tankName) {
        Tank tank = null;
        World world = player.getWorld();
        switch (tankName) {
            case "Helios":
                tank = new TankHelios(player, world);
                break;
            case "Coalition":
                tank = new TankCoalition(player, world);
                break;
            case "MarkOne":
                tank = new TankMarkOne(player, world);
                break;
        }
        return tank;
    }
}
