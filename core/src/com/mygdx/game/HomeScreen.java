package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class HomeScreen implements Screen {
    final TankStars game;
    final HomeSettings homeSettings;
    final OrthographicCamera camera;
    private Texture homeBackground;
    private Music backSound;

    private Texture playImg;
    private Rectangle playBtn;

    private Texture exitImg;
    private Rectangle exitBtn;

    private Texture settingsBtnImg;
    private Rectangle settingsBtn;

    public HomeScreen(final TankStars game) {
        this.game = game;
        this.homeSettings = new HomeSettings(game, this);   // HomeScreen contains HomeSettings
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowX(), game.getWindowY());

        setHomeBackground();
        startBackSound();

        setPlayImg();
        setPlayBtn();

        setExitImg();
        setExitBtn();

        setSettingsBtnImg();
        setSettingsBtn();
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        ScreenUtils.clear(0, 0, 0, 1); // sets the background color after clearing screen
        game.batch.begin();
        game.batch.draw(homeBackground, 0, 0, game.getWindowX(), game.getWindowY());
        game.batch.end();

        // Play
        game.batch.begin();
        game.batch.draw(playImg, playBtn.x, playBtn.y, playBtn.width, playBtn.height);
        game.batch.end();

        // Exit
        game.batch.begin();
        game.batch.draw(exitImg, exitBtn.x, exitBtn.y, exitBtn.width, exitBtn.height);
        game.batch.end();

        // Settings
        game.batch.begin();
        game.batch.draw(settingsBtnImg, settingsBtn.x, settingsBtn.y, settingsBtn.width, settingsBtn.height);
        game.batch.end();

        chooseTanks();
        goToSettingsScreen();
        exitGame();
    }

    // Controllers ------------------------------

    public void chooseTanks() {
        float lowerX = playBtn.x * 1.04f;
        float upperX = (playBtn.x + playBtn.width) * 0.96f;
        float lowerY = game.getWindowY() * 0.6f;
        float upperY = lowerY + game.getWindowY() * 0.11f;
        if ((Gdx.input.getX() < upperX && Gdx.input.getX() > lowerX && Gdx.input.getY() < upperY
                && Gdx.input.getY() > lowerY && Gdx.input.isTouched()) || Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            ChooseTank choose = new ChooseTank(game, this);
            game.setScreen(choose);
            dispose();
        }
    }

    public void goToSettingsScreen() {
        if (((Gdx.input.getX() < 60 && Gdx.input.getY() < game.getWindowY() / 2 - 190 && Gdx.input.isTouched())
                || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))) {
            game.setScreen(this.homeSettings);
        }
    }

    public void exitGame() {
        float lowerX = exitBtn.x;
        float upperX = lowerX + exitBtn.width;
        float lowerY = 420;
        float upperY = 445;
        if ((Gdx.input.getX() < upperX && Gdx.input.getX() > lowerX && Gdx.input.getY() < upperY
                && Gdx.input.getY() > lowerY && Gdx.input.isTouched())
                || Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            Gdx.app.exit();
            dispose();
        }
    }

    // Getters & setters ------------------------------

    public void setHomeBackground() {
        this.homeBackground = new Texture("backgrounds/home.png");
    }

    public void startBackSound() {
        this.backSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/backSound.mp3"));
        this.backSound.setLooping(true);
        this.backSound.play();
    }

    public void stopBackSound() {
        this.backSound.stop();
    }

    // Play button

    public Texture getPlayImg() {
        return playImg;
    }

    public void setPlayImg() {
        this.playImg = new Texture("buttons/play.png");
    }

    public void setPlayBtn() {
        this.playBtn = new Rectangle();
        this.playBtn.width = (float) getPlayImg().getWidth() / 3;
        this.playBtn.height = (float) getPlayImg().getHeight() / 3;
        this.playBtn.x = (game.getWindowX() - this.playBtn.width) / 2;
        this.playBtn.y = 100;
    }

    // Exit button

    public Texture getExitImg() {
        return exitImg;
    }

    public void setExitImg() {
        this.exitImg = new Texture("buttons/exit.png");
    }

    public void setExitBtn() {
        this.exitBtn = new Rectangle();
        this.exitBtn.width = (float) getExitImg().getWidth() / 3;
        this.exitBtn.height = (float) getExitImg().getHeight() / 3;
        this.exitBtn.x = (game.getWindowX() - this.exitBtn.width) / 2;
        this.exitBtn.y = 70;
    }

    // Settings button

    public Texture getSettingsBtnImg() {
        return this.settingsBtnImg;
    }

    public void setSettingsBtnImg() {
        this.settingsBtnImg = new Texture("buttons/settings.png");
    }

    public void setSettingsBtn() {
        this.settingsBtn = new Rectangle();
        this.settingsBtn.width = (float) getSettingsBtnImg().getWidth() / 4;
        this.settingsBtn.height = (float) getSettingsBtnImg().getHeight() / 4;
        this.settingsBtn.x = 0;
        this.settingsBtn.y = this.game.getWindowY() - 80;
    }

    // Override methods ------------------------------

    @Override
    public void dispose() {
        homeBackground.dispose();
        playImg.dispose();
        exitImg.dispose();
        settingsBtnImg.dispose();
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