package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class HomeSettings extends Settings {
    final HomeScreen mainScreen;

    Texture vibrationImg;
    Rectangle vibration;
    boolean vibrStatus = true;

    private Texture notificationImg;
    private Rectangle notification;
    boolean notificationStatus = true;

    private Texture closeImg;
    private Rectangle close;

    public HomeSettings(TankStars game, HomeScreen home) {
        super(game);
        this.mainScreen = home;

        setSettingsBackground();

        setVibrationImg(vibrStatus);
        setVibration(350);

        setSoundImg(soundStatus);
        setSound(280);

        setMusicImg(musicStatus);
        setMusic(210);

        setNotificationImg(notificationStatus);
        setNotification(140);

        setCloseImg();
        setClose(70);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        // Game background
        game.batch.begin();
        game.batch.draw(settingsBackground, 0, 0, game.getWindowX(), game.getWindowY());
        game.batch.end();

        game.batch.begin();
        game.batch.draw(closeImg, close.x, close.y, close.width, close.height);
        game.batch.end();

        float lowerX = this.close.x * 1.04f;
        float upperX = (this.close.x + this.close.width) * 0.96f;

        vibrationMode(lowerX, upperX, game.getWindowY() * 0.25f, game.getWindowY() * (0.25f + 0.08f));
        soundMode(lowerX, upperX, game.getWindowY() * 0.38f, game.getWindowY() * (0.38f + 0.08f));
        musicMode(lowerX, upperX, game.getWindowY() * 0.51f, game.getWindowY() * (0.51f + 0.08f));
        notificationMode(lowerX, upperX, game.getWindowY() * 0.64f, game.getWindowY() * (0.64f + 0.08f));
        closeMode(lowerX, upperX, game.getWindowY() * 0.77f, game.getWindowY() * (0.77f + 0.08f));
    }

    // Controllers
    // -----------------------------------------------------------------------------------------------------

    public void vibrationMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.V))) {
            vibrStatus = !vibrStatus;
        }
        setVibrationImg(vibrStatus);
        game.batch.begin();
        game.batch.draw(vibrationImg, vibration.x, vibration.y, vibration.width, vibration.height);
        game.batch.end();
    }

    public void soundMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.S))) {
            soundStatus = !soundStatus;
        }
        setSoundImg(soundStatus);
        game.batch.begin();
        game.batch.draw(soundImg, sound.x, sound.y, sound.width, sound.height);
        game.batch.end();
    }

    public void musicMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.M))) {
            musicStatus = !musicStatus;
        }
        setMusicImg(musicStatus);
        game.batch.begin();
        game.batch.draw(musicImg, music.x, music.y, music.width, music.height);
        game.batch.end();
        if (musicStatus == false) {
            this.mainScreen.stopBackSound();
        }
    }

    public void notificationMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.N))) {
            notificationStatus = !notificationStatus;
        }
        setNotificationImg(notificationStatus);
        game.batch.begin();
        game.batch.draw(notificationImg, notification.x, notification.y, notification.width, notification.height);
        game.batch.end();
    }

    public void closeMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.C))) {
            game.setScreen(this.mainScreen);
        }
    }

    // Getters & setters
    // -----------------------------------------------------------------------------------------------

    public void setSettingsBackground() {
        this.settingsBackground = new Texture("backgrounds/settings.png");
    }

    // Vibration -----------------------------

    public Texture getVibrationImg() {
        return this.vibrationImg;
    }

    public void setVibrationImg(boolean status) {
        if (status == true) {
            this.vibrationImg = new Texture("buttons/on/vibrationON.png");
        } else {
            this.vibrationImg = new Texture("buttons/off/vibrationOFF.png");
        }
    }

    public void setVibration(int y) {
        this.vibration = new Rectangle();
        this.vibration.width = (float) getVibrationImg().getWidth() / 3;
        this.vibration.height = (float) getVibrationImg().getHeight() / 3;
        this.vibration.x = (this.game.getWindowX() - this.vibration.width) / 2;
        this.vibration.y = y;
    }

    // Sound ---------------------------------

    public Texture getSoundImg() {
        return this.soundImg;
    }

    public void setSoundImg(boolean status) {
        if (status == true) {
            this.soundImg = new Texture("buttons/on/soundON.png");
        } else {
            this.soundImg = new Texture("buttons/off/soundOFF.png");
        }
    }

    public void setSound(int y) {
        this.sound = new Rectangle();
        this.sound.width = (float) getSoundImg().getWidth() / 3;
        this.sound.height = (float) getSoundImg().getHeight() / 3;
        this.sound.x = (this.game.getWindowX() - this.sound.width) / 2;
        this.sound.y = y;
    }

    // Music ---------------------------------

    public Texture getMusicImg() {
        return this.musicImg;
    }

    public void setMusicImg(boolean status) {
        if (status == true) {
            this.musicImg = new Texture("buttons/on/musicON.png");
        } else {
            this.musicImg = new Texture("buttons/off/musicOFF.png");
        }
    }

    public void setMusic(int y) {
        this.music = new Rectangle();
        this.music.width = (float) getMusicImg().getWidth() / 3;
        this.music.height = (float) getMusicImg().getHeight() / 3;
        this.music.x = (this.game.getWindowX() - this.music.width) / 2;
        this.music.y = y;
    }

    // Notification ---------------------------------

    public Texture getNotificationImg() {
        return this.notificationImg;
    }

    public void setNotificationImg(boolean status) {
        if (status == true) {
            this.notificationImg = new Texture("buttons/on/notificationON.png");
        } else {
            this.notificationImg = new Texture("buttons/off/notificationOFF.png");
        }
    }

    public void setNotification(int y) {
        this.notification = new Rectangle();
        this.notification.width = (float) getNotificationImg().getWidth() / 3;
        this.notification.height = (float) getNotificationImg().getHeight() / 3;
        this.notification.x = (this.game.getWindowX() - this.notification.width) / 2;
        this.notification.y = y;
    }

    // Close ------------------------------------------

    public Texture getCloseImg() {
        return this.closeImg;
    }

    public void setCloseImg() {
        this.closeImg = new Texture("buttons/close.png");
    }

    public void setClose(int y) {
        this.close = new Rectangle();
        this.close.width = (float) getCloseImg().getWidth() / 3;
        this.close.height = (float) getCloseImg().getHeight() / 3;
        this.close.x = (this.game.getWindowX() - this.close.width) / 2;
        this.close.y = y;
    }

    @Override
    public void dispose() {
        settingsBackground.dispose();
        vibrationImg.dispose();
        soundImg.dispose();
        musicImg.dispose();
        notificationImg.dispose();
        closeImg.dispose();
    }
}