package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameSettings extends Settings {
    final GameScreen gameScreen;

    private Texture resumeImg;
    private Rectangle resume;

    private Texture restartImg;
    private Rectangle restart;

    private Texture mainMenuImg;
    private Rectangle mainMenu;

    public GameSettings(TankStars game, GameScreen newGame) {
        super(game);
        this.gameScreen = newGame;

        setSettingsBackground();
        setResumeImg();
        setResume(350);

        setRestartImg();
        setRestart(280);

        setSoundImg(soundStatus);
        setSound(210);

        setMusicImg(musicStatus);
        setMusic(140);

        setMainMenuImg();
        setMainMenu(70);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        // Game background
        game.batch.begin();
        game.batch.draw(settingsBackground, 0, 0, game.getWindowX(), game.getWindowY());
        game.batch.end();

        // Resume
        game.batch.begin();
        game.batch.draw(resumeImg, resume.x, resume.y, resume.width, resume.height);
        game.batch.end();

        // Restart
        game.batch.begin();
        game.batch.draw(restartImg, restart.x, restart.y, restart.width, restart.height);
        game.batch.end();

        // Exit
        game.batch.begin();
        game.batch.draw(mainMenuImg, mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
        game.batch.end();

        float lowerX = this.resume.x * 1.04f;
        float upperX = (this.resume.x + this.resume.width) * 0.96f;

        resumeMode(lowerX, upperX, game.getWindowY() * 0.25f, game.getWindowY() * (0.25f + 0.08f));
        restartMode(lowerX, upperX, game.getWindowY() * 0.38f, game.getWindowY() * (0.38f + 0.08f));
        soundMode(lowerX, upperX, game.getWindowY() * 0.51f, game.getWindowY() * (0.51f + 0.08f));
        musicMode(lowerX, upperX, game.getWindowY() * 0.64f, game.getWindowY() * (0.64f + 0.08f));
        goToMainMenu(lowerX, upperX, game.getWindowY() * 0.77f, game.getWindowY() * (0.77f + 0.08f));
    }

    // Controllers
    // -----------------------------------------------------------------------------------------------------

    public void resumeMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.R))) {
            game.setScreen(this.gameScreen);
        }
    }

    public void restartMode(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.X))) {
            this.gameScreen.stopBackSound();
            this.gameScreen.stopEngineSound();
            Player p1 = this.gameScreen.getPlayer1();
            Player p2 = this.gameScreen.getPlayer2();
            p1.refuel();
            p2.refuel();
            game.setScreen(new GameScreen(game, p1, p2));
        }
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
        if (soundStatus == false) {
            this.gameScreen.stopEngineSound();
        }
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
            this.gameScreen.stopBackSound();
        }
    }

    public void goToMainMenu(float lowerX, float upperX, float lowerY, float upperY) {
        if ((Gdx.input.getX() > lowerX && Gdx.input.getX() < upperX && Gdx.input.getY() > lowerY
                && Gdx.input.getY() < upperY && Gdx.input.isTouched()) || (Gdx.input.isKeyPressed(Input.Keys.E))) {
            this.gameScreen.stopBackSound();
            this.gameScreen.stopEngineSound();
            game.setScreen(new HomeScreen(game));
            dispose();
        }
    }

    // Getters & setters
    // -----------------------------------------------------------------------------------------------

    public void setSettingsBackground() {
        this.settingsBackground = new Texture("backgrounds/settings.png");
    }

    public Texture getResumeImg() {
        return this.resumeImg;
    }

    public void setResumeImg() {
        this.resumeImg = new Texture("buttons/resume.png");
    }

    public void setResume(int y) {
        this.resume = new Rectangle();
        this.resume.width = (float) getResumeImg().getWidth() / 3;
        this.resume.height = (float) getResumeImg().getHeight() / 3;
        this.resume.x = (this.game.getWindowX() - this.resume.width) / 2;
        this.resume.y = y;
    }

    // restart -----------------------------

    public Texture getRestartImg() {
        return this.restartImg;
    }

    public void setRestartImg() {
        this.restartImg = new Texture("buttons/restart.png");
    }

    public void setRestart(int y) {
        this.restart = new Rectangle();
        this.restart.width = (float) getRestartImg().getWidth() / 3;
        this.restart.height = (float) getRestartImg().getHeight() / 3;
        this.restart.x = (this.game.getWindowX() - this.restart.width) / 2;
        this.restart.y = y;
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

    // Main menu-----------------------------

    public Texture getMainMenuImg() {
        return this.mainMenuImg;
    }

    public void setMainMenuImg() {
        this.mainMenuImg = new Texture("buttons/mainMenu.png");
    }

    public void setMainMenu(int y) {
        this.mainMenu = new Rectangle();
        this.mainMenu.width = (float) getMainMenuImg().getWidth() / 3;
        this.mainMenu.height = (float) getMainMenuImg().getHeight() / 3;
        this.mainMenu.x = (this.game.getWindowX() - this.mainMenu.width) / 2;
        this.mainMenu.y = y;
    }

    @Override
    public void dispose() {
        settingsBackground.dispose();
        resumeImg.dispose();
        restartImg.dispose();
        soundImg.dispose();
        musicImg.dispose();
        mainMenuImg.dispose();
    }
}