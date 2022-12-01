package com.tankstars.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Arena {
   	private final int WORLD_WIDTH = 1600;
   	private final int WORLD_HEIGHT = 731;
   	private SpriteBatch batch;
   	private Texture img;
   	private Texture pixmaptex;
   	private Terrain terrain;
   	private ChainShape chainShape;
   	private Body terrainBody;

    public World world;
    public Box2DDebugRenderer debugRenderer;

    public Arena() {
        world = new World(new Vector2(0, -10), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    private void createTerrainBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        terrainBody = world.createBody(bodyDef);

        Vector2[] vertices = new Vector2[WORLD_WIDTH + 2];
        vertices[0] = new Vector2(0, 0);
        vertices[WORLD_WIDTH + 1] = new Vector2(WORLD_WIDTH - 1, 0);
        for (int i = 1; i <= WORLD_WIDTH; i++) {
            vertices[i] = new Vector2(i - 1, terrain.convertY(terrain.getyCoordinates().get(i - 1)));
        }

        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(vertices);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = chainShape;
        fixtureDef.density = 1f;
        terrainBody.createFixture(fixtureDef);
        chainShape.dispose();
    }
}
