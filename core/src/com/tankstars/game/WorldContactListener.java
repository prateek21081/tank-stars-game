package com.tankstars.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContactListener implements ContactListener {
    private Arena arena;

    public WorldContactListener(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if (fixtureA.getUserData().equals("weapon") || fixtureB.getUserData().equals("weapon")) {
            Fixture weapon = fixtureA.getUserData().equals("head") ? fixtureA : fixtureB;
            Fixture other = weapon == fixtureA ? fixtureB : fixtureA;

            if (other.getUserData().equals("tankA") || other.getUserData().equals("tankB")) {
                arena.setWeaponToDestroy(weapon.getBody());
                arena.handleDirectTankHit((String) other.getUserData());
            } else {
                Vector2 center = weapon.getBody().getWorldCenter();
                Vector2 position = new Vector2(center.x, center.y - weapon.getShape().getRadius());
                System.out.println("Weapon x: " + position.x);
                System.out.println("Weapon y: " + position.y);
                arena.setWeaponToDestroy(weapon.getBody());
                arena.handleGroundHit(position);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {}

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}
}
