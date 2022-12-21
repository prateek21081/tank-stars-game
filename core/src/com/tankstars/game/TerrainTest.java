package com.tankstars.game;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TerrainTest {
    @Test
    public void getyCoordinates() {
        Terrain terrain = new Terrain();
        List<Integer> c = terrain.getyCoordinates();
        int height = c.get(0);
        assertEquals(height, 200);
    }

    @Test
    public void setyCoordinates() {
        Terrain terrain = new Terrain();
        List<Integer> c = new ArrayList<Integer>();
        c.add(0);
        terrain.setyCoordinates(c);
        assertSame(c, terrain.getyCoordinates());
    }

    @Test
    public void convertY() {
        Terrain terrain = new Terrain();
        assertEquals(terrain.convertY(12), 800 - 12);
    }
}