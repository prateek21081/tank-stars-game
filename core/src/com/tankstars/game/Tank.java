package com.tankstars.game;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.math.Vector2;
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

        this.positionX = this.player.isMain ? 200 : TankStarsGame.VIEWPORT_WIDTH - 200;
        this.positionY = TankStarsGame.VIEWPORT_HEIGHT / 2;

        this.createTankBody();
    }

    public void createTankBody() {
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

        if (player.isMain) {
            tankBody.createFixture(fixtureDef).setUserData("tankA");
        } else {
            tankBody.createFixture(fixtureDef).setUserData("tankB");
        }
        circle.dispose();
    }

    public void fire (float power, float angle) {
        Vector2 tankPosition = tankBody.getPosition();
        Integer positionX = (int) tankPosition.x;
        Integer positionY = (int) tankPosition.y + 20;
        Weapon weapon = new Weapon(positionX, positionY, world);
        weapon.fire(power, angle);
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public void handleHit() {
       this.health -= 100;
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
