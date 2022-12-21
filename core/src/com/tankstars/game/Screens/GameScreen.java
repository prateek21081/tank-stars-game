package com.tankstars.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.tankstars.game.TankStarsGame;

public class GameScreen implements Screen {
    private final TankStarsGame game;
    private final Stage stage;
    private final Table root;
    private final ProgressBar healthPlayerA;
    private final TextButton pauseButton;
    private final ProgressBar healthPlayerB;
    private final Label angleLabel;
    private final Slider angleSlider;
    private final TextButton fireButton;
    private final Slider powerSlider;
    private final Label powerLabel;
    private float power;
    private float angle;
    private int explosionX;
    private int explosionY;
    private Sprite weaponSprite;
    private TextureAtlas textureAtlas;
    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean explosion;
    private float animationDuration;


    public GameScreen (final TankStarsGame game) {
        this.game = game;

        elapsedTime = 0f;
        animationDuration = 1.5f;
        textureAtlas = new TextureAtlas("explosion/explosion.atlas");
        weaponSprite = new Sprite(textureAtlas.findRegion("Explosion"));
        Array<TextureRegion> keyFrames = new Array<>();
        for (int i = 0; i < 12; i++) {
            keyFrames.add(new TextureRegion(weaponSprite.getTexture(), i * 96, 0, 96, 96));
        }
        System.out.println("Keyframes" + keyFrames);
        animation = new Animation(animationDuration / 12f, keyFrames);
        explosion = false;

        stage = new Stage(game.viewport);
        Gdx.input.setInputProcessor(stage);

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        Table topHUD = new Table();
        Table btmHUD = new Table();

        //topHUD.setDebug(true, true);
        //btmHUD.setDebug(true, true);

        healthPlayerA = new ProgressBar(0, 100, 1, false, game.skin);
        topHUD.add(healthPlayerA).expand().right().top().space(30).padTop(30);
        healthPlayerA.setValue(100);

        pauseButton = new TextButton("||", game.skin);
        topHUD.add(pauseButton).top().space(30).padTop(30);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InGameMenuScreen(game));
                dispose();
            }
        });

        healthPlayerB = new ProgressBar(0, 100, 1, false, game.skin);
        topHUD.add(healthPlayerB).expand().left().top().space(30).padTop(30);
        healthPlayerB.setValue(100);

        angleLabel = new Label("Angle : XXX", game.skin);
        btmHUD.add(angleLabel).expandY().right().bottom().space(30).padBottom(30);

        angleSlider = new Slider(0, 179, 1, false, game.skin);
        btmHUD.add(angleSlider).expandY().right().bottom().space(30).padBottom(30);
        angleSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                angle = slider.getValue();
                angleLabel.setText(String.format("Angle : %3d", (int) angle));
            }
        });

        fireButton = new TextButton("Fire", game.skin);
        btmHUD.add(fireButton).bottom().space(30).padBottom(30);
        fireButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Tank fire
                float scaledPower = (float) Math.pow(power, 2.0);
                System.out.println("Power: " + power);
                System.out.println("Scaled Power: " + scaledPower);
                System.out.println("Angle: " + angle);
                game.arena.handleFire(scaledPower, angle);
            }
        });

        powerLabel = new Label("Power : XXX", game.skin);
        powerSlider = new Slider(0, 100, 1, false, game.skin);
        powerSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Slider slider = (Slider) actor;
                power = slider.getValue();
                powerLabel.setText(String.format("Power : %3d", (int) power));
            }
        });

        btmHUD.add(powerSlider).expandY().left().bottom().space(30).padBottom(30);
        btmHUD.add(powerLabel).expandY().left().bottom().space(30).padBottom(30);

        root.add(topHUD).grow().row();
        root.add(btmHUD).grow();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.renderBackground(delta);
        game.arena.world.step(1/60f, 6, 2);
        healthPlayerA.setValue(game.arena.getPlayerA().getTank().getHealth());
        healthPlayerB.setValue(game.arena.getPlayerB().getTank().getHealth());

        if (game.arena.isGroundHit) game.arena.updateTerrain();
        if (game.arena.weaponToDestroy != null) {
            explosionX = (int) game.arena.weaponToDestroy.getWorldCenter().x - 48;
            explosionY = (int) game.arena.weaponToDestroy.getWorldCenter().y - 48;
            game.arena.world.destroyBody(game.arena.weaponToDestroy);
            game.arena.setWeaponToDestroy(null);
            game.arena.getPlayerA().getTank().setCurrentWeapon(null);
            game.arena.getPlayerB().getTank().setCurrentWeapon(null);
            explosion = true;
        }
        if (game.arena.isGameOver) game.setScreen(new MainMenuScreen(game));

        game.arena.debugRenderer.render(game.arena.world, game.camera.combined);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(game.arena.getTerrain().getTexture(), 0, 0);
        if (explosion) {
            animateExplosion(delta);
            if (Math.abs(elapsedTime - animationDuration) <= 0.1f) {
                elapsedTime = 0;
                explosion = false;
            }
        }
        game.arena.update(game.batch);
        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void animateExplosion(float delta) {
        elapsedTime += delta;
        System.out.println("Inside animation");
        game.batch.draw(animation.getKeyFrame(elapsedTime, false), explosionX,  explosionY);
    }
}
