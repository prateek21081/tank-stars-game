package com.tankstars.game;

import java.util.ArrayList;

public abstract class Tank {
    private Integer fuel;
    private Integer health;
    // private Integer Position; // finalize how to handle position.
    private Double angle;
    private Integer impulse;
    private Integer mass;
    private ArrayList<Weapon> weapons;

    public void fire () {

    }

    public Integer getFuel() {
        return fuel;
    }

    public Integer getHealth() {
        return health;
    }

    public Double getAngle() {
        return angle;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }
}
