package com.tankstars.game;

import java.util.ArrayList;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Tank {
    private Integer fuel;
    private Integer health;
    private Integer positionX;
    private Integer positionY;
    private Double angle;
    private Integer impulse;
    private Integer mass;
    private ArrayList<Weapon> weapons;
    private Body tankBody;
    private World world;
    private Player player;
    
    public Tank (Player player, World world) {
        this.world = world;

        this.fuel = 100;
        this.health = 100;
        this.angle = 60d;
        this.impulse = 1;
        this.mass = 100;

        this.player = player;

        this.positionX = this.player.isMain ? 20 : TankStarsGame.VIEWPORT_WIDTH - 20;
        this.positionY = TankStarsGame.VIEWPORT_HEIGHT / 2;

        this.createTankBody();
    }

    public void createTankBody() {
        System.out.println("Tank body created");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        tankBody = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(10);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;

        tankBody.createFixture(fixtureDef);
        circle.dispose();
    }

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
