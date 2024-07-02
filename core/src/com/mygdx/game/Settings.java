package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Settings implements Screen {
    final TankStars game;
    final OrthographicCamera camera;

    Texture settingsBackground;

    Texture soundImg;
    Rectangle sound;
    boolean soundStatus = true;

    Texture musicImg;
    Rectangle music;
    boolean musicStatus = true;

    public Settings(TankStars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowX(), game.getWindowY());
    }

    public abstract void soundMode(float lowerX, float upperX, float lowerY, float upperY);

    public abstract void musicMode(float lowerX, float upperX, float lowerY, float upperY);

    // Override methods
    // ------------------------------------------------------------------------------------------------

    @Override
    public void dispose() {
        settingsBackground.dispose();
        soundImg.dispose();
        musicImg.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
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
}