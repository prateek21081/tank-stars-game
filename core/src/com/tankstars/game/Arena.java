package com.tankstars.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;

public class Arena {
    public Body weaponToDestroy;
    private Terrain terrain;
   	private Body terrainBody;
    private Player playerB = null;
	private Player playerA = null;
    private boolean turn;

    public World world;
    public Box2DDebugRenderer debugRenderer;
    public boolean isGroundHit;
    public boolean isGameOver;

    public Arena() {
        turn = true;
        isGroundHit = false;
        isGameOver = false;
        world = new World(new Vector2(0, -20), true);
        world.setContactListener(new WorldContactListener(this));
        debugRenderer = new Box2DDebugRenderer();
        terrain = new Terrain(TankStarsGame.VIEWPORT_WIDTH, TankStarsGame.VIEWPORT_HEIGHT);
        createTerrainBody();
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
        this.playerA.setWorld(this.world);
        this.playerA.createTank();
    }

    public Player getPlayerB() {
        return playerB;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
        this.playerB.setWorld(this.world);
        this.playerB.createTank();
    }

    public void setWeaponToDestroy(Body weaponToDestroy) {
        this.weaponToDestroy = weaponToDestroy;
    }

    private void createTerrainBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        terrainBody = world.createBody(bodyDef);

        Vector2[] vertices = new Vector2[TankStarsGame.VIEWPORT_WIDTH + 2];
        vertices[0] = new Vector2(0, 0);
        vertices[TankStarsGame.VIEWPORT_WIDTH + 1] = new Vector2(TankStarsGame.VIEWPORT_WIDTH - 1, 0);
        for (int i = 1; i <= TankStarsGame.VIEWPORT_WIDTH; i++) {
            vertices[i] = new Vector2(i - 1, terrain.getyCoordinates().get(i - 1));
        }

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.density = 1f;
        terrainBody.createFixture(fixtureDef).setUserData("ground");
        chainShape.dispose();
    }

    public void handleFire(float power, float angle) {
        if (turn) {
            playerA.fire(power, angle);
            turn = false;
        } else {
            playerB.fire(power, angle);
            turn = true;
        }
    }

    public void handleTankHit(String userData) {
        switch (userData) {
            case "tankA":
                playerA.handleHit();
                break;
            case "tankB":
                playerB.handleHit();
                break;
        }
        checkGameOver();
    }

    private void checkGameOver() {
        if (playerA.checkDead()) {
            isGameOver = true;
        } else if (playerB.checkDead()) {
            isGameOver = true;
        }
    }

    public void handleGroundHit(Vector2 position) {
        terrain.deformAt((int) position.x, (int) position.y, 20);
        isGroundHit = true;
    }

    public void updateTerrain() {
        world.destroyBody(terrainBody);
        createTerrainBody();
        isGroundHit = false;
    }
}
