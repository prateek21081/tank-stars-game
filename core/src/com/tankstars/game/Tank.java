package com.tankstars.game;

import java.awt.*;
import java.awt.geom.RectangularShape;
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

        this.positionX = this.player.isMain ? 600 : TankStarsGame.VIEWPORT_WIDTH - 600;
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

        PolygonShape polygonShape = new PolygonShape();
        Vector2[] tankVertices = new Vector2[4];
        tankVertices[0] = new Vector2(0, 0);
        tankVertices[1] = new Vector2(10, 0);
        tankVertices[2] = new Vector2(10, 10);
        tankVertices[3] = new Vector2(0, 10);

        polygonShape.set(tankVertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 150f;

        if (player.isMain) {
            tankBody.createFixture(fixtureDef).setUserData("tankA");
        } else {
            tankBody.createFixture(fixtureDef).setUserData("tankB");
        }
        polygonShape.dispose();
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
        positionX = (int) tankBody.getWorldCenter().x;
        return positionX;
    }

    public Integer getPositionY() {
        positionY = (int) tankBody.getWorldCenter().y;
        return positionY;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public void handleHit(int damage) {
       this.health -= damage;
        System.out.println("Health: " + this.health);
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

    public void applyImpulse(Vector2 impulse) {
        tankBody.applyLinearImpulse(impulse, tankBody.getWorldCenter(), true);
    }
}
