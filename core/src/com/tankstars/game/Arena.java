package com.tankstars.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

        int length = (TankStarsGame.VIEWPORT_WIDTH / 2) + 2;
        int index = 1;
        Vector2[] vertices = new Vector2[length];
        vertices[0] = new Vector2(0, 0);
        vertices[length - 1] = new Vector2(TankStarsGame.VIEWPORT_WIDTH - 1, 0);
        for (int i = 1; i <= TankStarsGame.VIEWPORT_WIDTH; i += 2) {
            vertices[index++] = new Vector2(i - 1, terrain.getyCoordinates().get(i - 1));
        }

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 1f;
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

    public void handleDirectTankHit(String userData) {
        switch (userData) {
            case "tankA":
                playerA.handleHit(30);
                break;
            case "tankB":
                playerB.handleHit(30);
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

    private Vector2 getUnitVector(Vector2 vector) {
        int mod = (int) Math.sqrt(Math.pow((vector.x), 2) + Math.pow(vector.y, 2));
        Vector2 unitVector = new Vector2(vector.x / mod, vector.y / mod);
        return unitVector;
    }

    private void handleIndirectTankHit(Player player, Vector2 position) {
        int hitDamage = 200;
        Tank tank = player.getTank();
        System.out.println("Tank coordinates " + tank.getPositionX() + ", " + tank.getPositionY());
        System.out.println("Weapon coordinates " + position.x + ", " + position.y);
        int distance = (int) Math.sqrt(Math.pow((tank.getPositionX() - position.x), 2) + Math.pow((tank.getPositionY() - position.y), 2));
        System.out.println("Distance from " + player.getName() + ": " + distance);
        hitDamage -= distance;
        hitDamage /= 4;
        if (hitDamage < 0) hitDamage = 0;
        System.out.println("Hit damage: " + hitDamage);
        tank.handleHit(hitDamage);
        Vector2 impulse = new Vector2(tank.getPositionX() - position.x, tank.getPositionY() - position.y);
        impulse = getUnitVector(impulse);
        System.out.println("Unit impulse" + impulse);
        impulse.set(impulse.x * (int) Math.pow(hitDamage, 3) * 5, impulse.y * (int) Math.pow(hitDamage, 3) * 5);
        System.out.println("Hit impulse" + impulse);
        tank.applyImpulse(impulse);
        checkGameOver();
    }

    public void handleGroundHit(Vector2 position) {
        handleIndirectTankHit(playerA, position);
        handleIndirectTankHit(playerB, position);
        terrain.deformAt((int) position.x, (int) position.y, 20);
        isGroundHit = true;
    }

    public void updateTerrain() {
        world.destroyBody(terrainBody);
        createTerrainBody();
        isGroundHit = false;
    }

    public void update(SpriteBatch batch) {
        playerA.update(batch);
        playerB.update(batch);
    }
}
