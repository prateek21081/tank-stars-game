package com.tankstars.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Weapon {
    private Integer damage;
    private Double radius;
    private Integer mass;
    private Body weaponBody;
    public Weapon(Integer positionX, Integer positionY, World world) {
        createWeaponBody(positionX, positionY, world);
    }

    public Weapon (int damage, double radius, int mass) {
        this.damage = damage;
        this.radius = radius;
        this.mass = mass;
    }

    public void createWeaponBody(Integer positionX, Integer positionY, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        weaponBody = world.createBody(bodyDef);

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(5);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circleShape;
        fixtureDef.density = 1f;

        weaponBody.createFixture(fixtureDef);
        circleShape.dispose();
    }

    private double convertToRadians(float angle) {
        return Math.PI * angle / 180.0;
    }

    public void fire(float power, float angle) {
        angle = (float) convertToRadians(angle);
        float x = power * (float) Math.cos((double) angle);
        float y = power * (float) Math.sin((double) angle);
        Vector2 impulse = new Vector2(x, y);
        weaponBody.applyLinearImpulse(impulse, weaponBody.getWorldCenter(), true);
    }

    public static class WorldContactListener {
    }
}
