package com.tankstars.game;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    protected Sprite tankSprite;
    private boolean isReset;
    private Weapon currentWeapon;

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
        createTankBody(this.positionX, this.positionY);
    }

    public void createTankBody(int positionX, int positionY) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        tankBody = world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        Vector2[] tankVertices = new Vector2[4];
        tankVertices[0] = new Vector2(0, 0);
        tankVertices[1] = new Vector2(30, 0);
        tankVertices[2] = new Vector2(30, 30);
        tankVertices[3] = new Vector2(0, 30);

        polygonShape.set(tankVertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 5f;
        fixtureDef.friction = 0.25f;

        if (player.isMain) {
            tankBody.createFixture(fixtureDef).setUserData("tankA");
        } else {
            tankBody.createFixture(fixtureDef).setUserData("tankB");
        }
        polygonShape.dispose();
    }

    public void updateTankBody() {
        world.destroyBody(tankBody);
        createTankBody(this.positionX, this.positionY);
    }

    public void fire (float power, float angle) {
        Vector2 tankPosition = tankBody.getWorldCenter();
        Integer positionX = (int) tankPosition.x;
        Integer positionY = (int) tankPosition.y + 40;
        currentWeapon = new Weapon(positionX, positionY, world);
        currentWeapon.fire(power, angle);
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

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void updateWeaponImage(SpriteBatch batch) {
        if (currentWeapon != null) {
            currentWeapon.updateImage(batch);
        }
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

    public void updateImage(SpriteBatch batch) {
        boolean isMoving = (Math.abs(tankBody.getLinearVelocity().x) >= 0.25f || Math.abs(tankBody.getLinearVelocity().y) >= 0.25f);
        if (isMoving) {
            Vector2 center = tankBody.getWorldCenter();
            float box2dAngle = (float) Math.toDegrees(tankBody.getAngle());
            float spriteAngle = tankSprite.getRotation();
            if (Math.abs(box2dAngle - spriteAngle) > 0.1f) ;
            tankSprite.rotate(box2dAngle - spriteAngle);
            tankSprite.setPosition(tankBody.getWorldCenter().x - 32, tankBody.getWorldCenter().y - 32);
            tankSprite.draw(batch);
            this.isReset = false;
        } else if (!isReset){
            world.destroyBody(tankBody);
            System.out.println("reset");
            createTankBody(getPositionX() - 15, getPositionY() - 15);
            tankSprite.setPosition(tankBody.getWorldCenter().x - 32, tankBody.getWorldCenter().y - 32);
            tankSprite.rotate(-1 * tankSprite.getRotation());
            tankSprite.draw(batch);
            this.isReset = true;
        } else {
            tankSprite.draw(batch);
        }
    }

    public void move(boolean left) {
        if (left) {
            System.out.println("impulse left");
            tankBody.applyLinearImpulse(new Vector2(-100000, 0), tankBody.getWorldCenter(), true);
        } else {
            System.out.println("impulse right");
            tankBody.applyLinearImpulse(new Vector2(100000, 0), tankBody.getWorldCenter(), true);
        }
    }
}