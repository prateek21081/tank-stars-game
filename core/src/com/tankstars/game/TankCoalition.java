package com.tankstars.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class TankCoalition extends Tank {
    public TankCoalition (Player player, World world) {
        super(player, world);
        if (player.isMain) {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("tank/coalition.png")));
        } else {
            tankSprite = new Sprite(new Texture(Gdx.files.internal("tank/coalition-mirrored.png")));
        }
    }
}
