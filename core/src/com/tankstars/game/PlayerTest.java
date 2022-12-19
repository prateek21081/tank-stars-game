package com.tankstars.game;

import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void getName() {
        Player player = new Player("raman", "boombox", true);
        assertEquals("raman", player.getName());
    }
}