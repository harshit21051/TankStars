package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final TankStars game;
    final GameSettings gameSettings;
    final OrthographicCamera camera;

    private Texture gameBackground;
    private Music backSound;
    private Music engineSound;
    private Sound attackSound;

    private Player player1;
    private Player player2;

    private Texture fuelBoardImg;
    private Rectangle fuelBoard;
    private final int fuelBoardPosY = 420;

    private Tank tank1;
    private Tank tank2;

    private Texture settingsBtnImg;
    private Rectangle settingsBtn;

    public GameScreen(final TankStars game, Player p1, Player p2) {
        this.game = game;
        this.gameSettings = new GameSettings(game, this); // GameScreen contains GameSettings
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowX(), game.getWindowY());
        this.player1 = p1;
        this.player2 = p2;
        this.player1.refuel();
        this.player2.refuel();
        this.tank1 = this.player1.getTank();
        this.tank2 = this.player2.getTank();

        setGameBackground();
        startBackSound();
        startEngineSound();
        setAttackSound();

        setFuelBoardImg();
        setFuelBoard();

        this.player1.setFuelMeter(110, fuelBoardPosY + 40);
        this.player2.setFuelMeter(
                this.game.getWindowX() - this.player1.getFuelMeter().x - this.player2.getFuelMeterImg().getWidth() / 2,
                this.player1.getFuelMeter().y);

        this.tank1.setTank(50, 79);
        this.tank2.setTank(this.game.getWindowX() - this.tank1.getTank().x - 10, this.tank1.getTank().y - 12);

        setSettingsBtnImg();
        setSettingsBtn();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        // Game background
        game.batch.begin();
        game.batch.draw(gameBackground, 0, 0, game.getWindowX(), game.getWindowY());
        game.batch.end();

        // Player 1's tank
        game.batch.begin();
        game.batch.draw(this.player1.getPlayerTankImg(), this.tank1.getTank().x, this.tank1.getTank().y,
                this.tank1.getTank().width, this.tank1.getTank().height);
        game.batch.end();

        // Player 2's tank
        game.batch.begin();
        game.batch.draw(this.player2.getPlayerTankImg(), this.tank2.getTank().x, this.tank2.getTank().y,
                -this.tank2.getTank().width, this.tank2.getTank().height);
        game.batch.end();

        // Fuel board on top of the screen
        game.batch.begin();
        game.batch.draw(fuelBoardImg, fuelBoard.x, fuelBoard.y, fuelBoard.width, fuelBoard.height);
        game.batch.end();

        // Fuel status of player 1
        game.batch.begin();
        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x, this.player1.getFuelMeter().y,
                this.player1.getFuelMeter().width, this.player1.getFuelMeter().height);
        game.batch.end();

        // Fuel status of player 2
        game.batch.begin();
        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x, this.player2.getFuelMeter().y,
                this.player2.getFuelMeter().width, this.player2.getFuelMeter().height);
        game.batch.end();

        // Settings
        game.batch.begin();
        game.batch.draw(settingsBtnImg, settingsBtn.x, settingsBtn.y, settingsBtn.width, settingsBtn.height);
        game.batch.end();

        tankController(1);
        tankController(2);
        goToSettingsScreen();
    }

    // Tank controllers
    // ------------------------------------------------------------------------------------------------

    public void tankController(int tno) {
        if (tno == 1) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                int p1fuel = this.player1.getFuel();
                if (p1fuel >= 0) {
                    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                        this.tank1.getTank().x -= 40 * Gdx.graphics.getDeltaTime();
                        this.tank1.getTank().y -= 4 * Gdx.graphics.getDeltaTime();
                    } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                        this.tank1.getTank().x += 40 * Gdx.graphics.getDeltaTime();
                        this.tank1.getTank().y += 4 * Gdx.graphics.getDeltaTime();
                    }
                    this.player1.decrFuel();
                    if (p1fuel > (0.75 * player1.maxFuel())) {
                        this.player1.setFuelMeterImg(100);
                        game.batch.begin();
                        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x,
                                this.player1.getFuelMeter().y, this.player1.getFuelMeter().width,
                                this.player1.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p1fuel <= (0.75 * player1.maxFuel()) && p1fuel > (0.5 * player1.maxFuel())) {
                        this.player1.setFuelMeterImg(75);
                        game.batch.begin();
                        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x,
                                this.player1.getFuelMeter().y, this.player1.getFuelMeter().width,
                                this.player1.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p1fuel <= (0.5 * player1.maxFuel()) && p1fuel > (0.25 * player1.maxFuel())) {
                        this.player1.setFuelMeterImg(50);
                        game.batch.begin();
                        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x,
                                this.player1.getFuelMeter().y, this.player1.getFuelMeter().width,
                                this.player1.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p1fuel <= (0.25 * player1.maxFuel()) && p1fuel > 0) {
                        this.player1.setFuelMeterImg(25);
                        game.batch.begin();
                        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x,
                                this.player1.getFuelMeter().y, this.player1.getFuelMeter().width,
                                this.player1.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p1fuel == 0) {
                        this.player1.setFuelMeterImg(0);
                        game.batch.begin();
                        game.batch.draw(this.player1.getFuelMeterImg(), this.player1.getFuelMeter().x,
                                this.player1.getFuelMeter().y, this.player1.getFuelMeter().width,
                                this.player1.getFuelMeter().height);
                        game.batch.end();
                    }
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                startAttackSound();
                this.tank1.getTank().x -= 10 * Gdx.graphics.getDeltaTime();
                this.tank1.getTank().y -= 1 * Gdx.graphics.getDeltaTime();
            }
        } else if (tno == 2) {
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                int p2fuel = this.player2.getFuel();
                if (p2fuel >= 0) {
                    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                        this.tank2.getTank().x -= 40 * Gdx.graphics.getDeltaTime();
                        this.tank2.getTank().y += 6 * Gdx.graphics.getDeltaTime();
                    }
                    else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                        this.tank2.getTank().x += 40 * Gdx.graphics.getDeltaTime();
                        this.tank2.getTank().y -= 6 * Gdx.graphics.getDeltaTime();
                    }
                    this.player2.decrFuel();
                    if (p2fuel > (0.75 * player2.maxFuel())) {
                        this.player2.setFuelMeterImg(100);
                        game.batch.begin();
                        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x,
                                this.player2.getFuelMeter().y, this.player2.getFuelMeter().width,
                                this.player2.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p2fuel <= (0.75 * player2.maxFuel()) && p2fuel > (0.5 * player2.maxFuel())) {
                        this.player2.setFuelMeterImg(75);
                        game.batch.begin();
                        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x,
                                this.player2.getFuelMeter().y, this.player2.getFuelMeter().width,
                                this.player2.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p2fuel <= (0.5 * player2.maxFuel()) && p2fuel > (0.25 * player2.maxFuel())) {
                        this.player2.setFuelMeterImg(50);
                        game.batch.begin();
                        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x,
                                this.player2.getFuelMeter().y, this.player2.getFuelMeter().width,
                                this.player2.getFuelMeter().height);
                        game.batch.end();

                    }
                    else if (p2fuel <= (0.25 * player2.maxFuel()) && p2fuel > 0) {
                        this.player2.setFuelMeterImg(25);
                        game.batch.begin();
                        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x,
                                this.player2.getFuelMeter().y, this.player2.getFuelMeter().width,
                                this.player2.getFuelMeter().height);
                        game.batch.end();
                    }
                    else if (p2fuel == 0) {
                        this.player2.setFuelMeterImg(0);
                        game.batch.begin();
                        game.batch.draw(this.player2.getFuelMeterImg(), this.player2.getFuelMeter().x,
                                this.player2.getFuelMeter().y, this.player2.getFuelMeter().width,
                                this.player2.getFuelMeter().height);
                        game.batch.end();
                    }
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.J)) {
                startAttackSound();
                this.tank2.getTank().x += 10 * Gdx.graphics.getDeltaTime();
                this.tank2.getTank().y -= 1.5 * Gdx.graphics.getDeltaTime();
            }
        }
    }

    public void goToSettingsScreen() {
        if (((Gdx.input.getX() < 60 && Gdx.input.getY() < game.getWindowY() / 2 - 190 && Gdx.input.isTouched())
                || Gdx.input.isKeyPressed(Input.Keys.ESCAPE))) {
            game.setScreen(this.gameSettings);
        }
    }

    // Getters & setters
    // -----------------------------------------------------------------------------------------------

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setGameBackground() {
        this.gameBackground = new Texture("backgrounds/game.png");
    }

    public Texture getFuelBoardImg() {
        return this.fuelBoardImg;
    }

    public void setFuelBoardImg() {
        this.fuelBoardImg = new Texture("fuelMeterElements/fuelBoard.png");
    }

    public void setFuelBoard() {
        this.fuelBoard = new Rectangle();
        this.fuelBoard.width = (float) getFuelBoardImg().getWidth() / 2;
        this.fuelBoard.height = (float) getFuelBoardImg().getHeight() / 2;
        this.fuelBoard.x = (this.game.getWindowX() - this.fuelBoard.width) / 2;
        this.fuelBoard.y = fuelBoardPosY;
    }

    // -------------------------------------------

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

    // -------------------------------------------

    public void startBackSound() {
        this.backSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/backSound.mp3"));
        this.backSound.setLooping(true);
        this.backSound.play();
    }

    public void stopBackSound() {
        this.backSound.stop();
    }

    public void startEngineSound() {
        this.engineSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/engineSound.mp3"));
        this.engineSound.setLooping(true);
        this.engineSound.play();
    }

    public void stopEngineSound() {
        this.engineSound.stop();
    }

    public void setAttackSound() {
        this.attackSound = Gdx.audio.newSound(Gdx.files.internal("sounds/attack.mp3"));
    }

    public void startAttackSound() {
        this.attackSound.play();
    }

    // Override methods
    // ------------------------------------------------------------------------------------------------

    @Override
    public void dispose() {
        gameBackground.dispose();
        backSound.dispose();
        engineSound.dispose();
        attackSound.dispose();
        fuelBoardImg.dispose();
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