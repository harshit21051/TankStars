package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankStars extends Game {
    private final int windowX = 960;
    private final int windowY = 540;
    public SpriteBatch batch;

    // Singleton design pattern
    //----------------------------------------------------
    public static TankStars gen = null;

    public static TankStars getInstance() {
        if (gen == null) {
            gen = new TankStars();
        }
        return gen;
    }
    //-----------------------------------------------------

    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new HomeScreen(this));
    }

    public void render() {
        super.render();
    }

    public int getWindowX() {
        return this.windowX;
    }

    public int getWindowY() {
        return this.windowY;
    }

    public void dispose() {
        batch.dispose();
    }
}