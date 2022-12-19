package com.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class TankHelios extends Tank {
    public TankHelios (Player player, World world) {
        super(player, world);
        if (player.isMain) {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("tank/helios.png")));
        } else {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("tank/helios-mirrored.png")));
        }

    }
}
