package com.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class TankMarkOne extends Tank {
    public TankMarkOne (Player player, World world) {
        super(player, world);
        if (player.isMain) {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("assets/tank/mark-one.png")));
        } else {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("assets/tank/mark-one-mirrored.png")));
        }
    }
}
