package com.tankstars.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Arena {
   	private Terrain terrain;
   	private Body terrainBody;
    private Player playerB = null;
	private Player playerA = null;
    private boolean turn;

    public World world;
    public Box2DDebugRenderer debugRenderer;

    public Arena() {
        turn = true;
        world = new World(new Vector2(0, -20), true);
        world.setContactFilter(new WorldContactListener());
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
        playerB = playerB;
        playerB.setWorld(this.world);
        playerB.createTank();
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
        terrainBody.createFixture(fixtureDef);
        chainShape.dispose();
    }

    public void handleFire(float power, float angle) {
        if (turn) {
            playerA.fire(power, angle);
        } else {
            playerB.fire(power, angle);
        }
    }
}
