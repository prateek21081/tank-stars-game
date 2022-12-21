package com.tankstars.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeaponTest {

    @Test
    public void convertToRadians() {
        Weapon weapon = new Weapon();
        int angle = (int) weapon.convertToRadians(180);
        assertEquals(3, angle);
    }
}