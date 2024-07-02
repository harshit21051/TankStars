package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class ChooseTank implements Screen {
    final TankStars game;
    final HomeScreen homeScreen;
    final OrthographicCamera camera;

    private Texture background;
    private Player player1;
    private Player player2;

    private Texture chooseP1Img;
    private Rectangle chooseP1;
    private Texture chooseP2Img;
    private Rectangle chooseP2;

    private Tank abramsForP1;
    private Tank frostForP1;
    private Tank atomicForP1;
    private Tank heliosForP1;
    private int selectedTankForP1;

    private Tank abramsForP2;
    private Tank frostForP2;
    private Tank atomicForP2;
    private Tank heliosForP2;
    private int selectedTankForP2;

    private Texture startBtnImg;
    private Rectangle startBtn;

    private Texture backBtnImg;
    private Rectangle backBtn;

    public ChooseTank(TankStars game, HomeScreen home) {
        this.game = game;
        this.homeScreen = home;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWindowX(), game.getWindowY());

        setBackground();

        this.player1 = new Player(game);
        this.player2 = new Player(game);

        this.player1.setFuelMeterImg(100);
        this.player2.setFuelMeterImg(100);

        this.chooseP1Img = new Texture("backgrounds/chooseP1.png");
        this.chooseP1 = new Rectangle();
        setChooseP1();

        this.chooseP2Img = new Texture("backgrounds/chooseP2.png");
        this.chooseP2 = new Rectangle();
        setChooseP2();

        // Tanks for player 1

        abramsForP1 = Tank.getInstance(player1, new Texture("tanks/options/abrams.png"));
        abramsForP1.setTank(100, 300);

        frostForP1 = Tank.getInstance(player1, new Texture("tanks/options/frost.png"));
        frostForP1.setTank(this.chooseP1.width - abramsForP1.getTank().width, this.abramsForP1.getTank().y);

        atomicForP1 = Tank.getInstance(player1, new Texture("tanks/options/atomic.png"));
        atomicForP1.setTank(this.abramsForP1.getTank().x, 130);

        heliosForP1 = Tank.getInstance(player1, new Texture("tanks/options/helios.png"));
        heliosForP1.setTank(this.chooseP1.width - abramsForP1.getTank().width, this.atomicForP1.getTank().y);

        // Tanks for player 2

        abramsForP2 = Tank.getInstance(player2, new Texture("tanks/options/abrams.png"));
        abramsForP2.setTank(this.chooseP2.x + 50, 300);

        frostForP2 = Tank.getInstance(player2, new Texture("tanks/options/frost.png"));
        frostForP2.setTank(this.game.getWindowX() - 243, this.abramsForP2.getTank().y);

        atomicForP2 = Tank.getInstance(player2, new Texture("tanks/options/atomic.png"));
        atomicForP2.setTank(this.abramsForP2.getTank().x, this.atomicForP1.getTank().y);

        heliosForP2 = Tank.getInstance(player2, new Texture("tanks/options/helios.png"));
        heliosForP2.setTank(this.frostForP2.getTank().x, this.atomicForP2.getTank().y);

        setStartBtnImg();
        setStartBtn();

        setBackBtnImg();
        setBackBtn();
    }

    @Override
    public void render(float delta) {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        // Background
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(background, 0, 0, game.getWindowX(), game.getWindowY());
        game.batch.end();

        // Chooose tank for player 1
        game.batch.begin();
        game.batch.draw(chooseP1Img, chooseP1.x, chooseP1.y, chooseP1.width, chooseP1.height);
        game.batch.end();

        // Chooose tank for player 2
        game.batch.begin();
        game.batch.draw(chooseP2Img, chooseP2.x, chooseP2.y, chooseP2.width, chooseP2.height);
        game.batch.end();

        // Start
        game.batch.begin();
        game.batch.draw(startBtnImg, startBtn.x, startBtn.y, startBtn.width, startBtn.height);
        game.batch.end();

        // Back
        game.batch.begin();
        game.batch.draw(backBtnImg, backBtn.x, backBtn.y, backBtn.width, backBtn.height);
        game.batch.end();

        // Choose tanks
        drawTank(abramsForP1);
        drawTank(frostForP1);
        drawTank(atomicForP1);
        drawTank(heliosForP1);
        drawTank(abramsForP2);
        drawTank(frostForP2);
        drawTank(atomicForP2);
        drawTank(heliosForP2);

        select(1);
        select(2);
        startGame();
        goBack();
    }

    public void drawTank(Tank t) {
        game.batch.begin();
        game.batch.draw(t.getTankImg(), t.getTank().x, t.getTank().y, t.getTank().width, t.getTank().height);
        game.batch.end();
    }

    // Controllers ------------------------------

    public void select(int pno) {
        if (pno == 1) {
            if ((Gdx.input.getX() < 200 && Gdx.input.getX() > 120 && Gdx.input.getY() < 200 && Gdx.input.getY() > 120
                    && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.NUM_1))) {
                selectedTankForP1 = 1;
            } else if ((Gdx.input.getX() < 400 && Gdx.input.getX() > 320 && Gdx.input.getY() < 200
                    && Gdx.input.getY() > 120 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.NUM_2))) {
                selectedTankForP1 = 2;
            } else if ((Gdx.input.getX() < 200 && Gdx.input.getX() > 120 && Gdx.input.getY() < 400
                    && Gdx.input.getY() > 320 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.NUM_3))) {
                selectedTankForP1 = 3;
            } else if ((Gdx.input.getX() < 400 && Gdx.input.getX() > 320 && Gdx.input.getY() < 400
                    && Gdx.input.getY() > 320 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.LEFT) && Gdx.input.isKeyPressed(Input.Keys.NUM_4))) {
                selectedTankForP1 = 4;
            }

            if (selectedTankForP1 == 1) {
                this.player1.setTank(abramsForP1);
                this.player1.setPlayerTankImg(new Texture("tanks/player1/abrams.png"));

                this.abramsForP1.setTankImg(new Texture("tanks/selected/abrams.png"));
                this.frostForP1.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP1.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP1.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP1 == 2) {
                this.player1.setTank(frostForP1);
                this.player1.setPlayerTankImg(new Texture("tanks/player1/frost.png"));

                this.abramsForP1.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP1.setTankImg(new Texture("tanks/selected/frost.png"));
                this.atomicForP1.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP1.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP1 == 3) {
                this.player1.setTank(atomicForP1);
                this.player1.setPlayerTankImg(new Texture("tanks/player1/atomic.png"));

                this.abramsForP1.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP1.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP1.setTankImg(new Texture("tanks/selected/atomic.png"));
                this.heliosForP1.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP1 == 4) {
                this.player1.setTank(heliosForP1);
                this.player1.setPlayerTankImg(new Texture("tanks/player1/helios.png"));

                this.abramsForP1.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP1.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP1.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP1.setTankImg(new Texture("tanks/selected/helios.png"));
            }
            drawTank(abramsForP1);
            drawTank(frostForP1);
            drawTank(atomicForP1);
            drawTank(frostForP1);
        } else if (pno == 2) {
            if ((Gdx.input.getX() < (200 + 450) && Gdx.input.getX() > (120 + 450) && Gdx.input.getY() < 200
                    && Gdx.input.getY() > 120 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUM_1))) {
                selectedTankForP2 = 1;
            } else if ((Gdx.input.getX() < (400 + 450) && Gdx.input.getX() > (320 + 450) && Gdx.input.getY() < 200
                    && Gdx.input.getY() > 120 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUM_2))) {
                selectedTankForP2 = 2;
            } else if ((Gdx.input.getX() < (200 + 450) && Gdx.input.getX() > (120 + 450) && Gdx.input.getY() < 400
                    && Gdx.input.getY() > 320 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUM_3))) {
                selectedTankForP2 = 3;
            } else if ((Gdx.input.getX() < (400 + 450) && Gdx.input.getX() > (320 + 450) && Gdx.input.getY() < 400
                    && Gdx.input.getY() > 320 && Gdx.input.isTouched())
                    || (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.NUM_4))) {
                selectedTankForP2 = 4;
            }

            if (selectedTankForP2 == 1) {
                this.player2.setTank(abramsForP2);
                this.player2.setPlayerTankImg(new Texture("tanks/player2/abrams.png"));

                this.abramsForP2.setTankImg(new Texture("tanks/selected/abrams.png"));
                this.frostForP2.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP2.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP2.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP2 == 2) {
                this.player2.setTank(frostForP2);
                this.player2.setPlayerTankImg(new Texture("tanks/player2/frost.png"));

                this.abramsForP2.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP2.setTankImg(new Texture("tanks/selected/frost.png"));
                this.atomicForP2.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP2.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP2 == 3) {
                this.player2.setTank(atomicForP2);
                this.player2.setPlayerTankImg(new Texture("tanks/player2/atomic.png"));

                this.abramsForP2.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP2.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP2.setTankImg(new Texture("tanks/selected/atomic.png"));
                this.heliosForP2.setTankImg(new Texture("tanks/options/helios.png"));
            } else if (selectedTankForP2 == 4) {
                this.player2.setTank(heliosForP2);
                this.player2.setPlayerTankImg(new Texture("tanks/player2/helios.png"));

                this.abramsForP2.setTankImg(new Texture("tanks/options/abrams.png"));
                this.frostForP2.setTankImg(new Texture("tanks/options/frost.png"));
                this.atomicForP2.setTankImg(new Texture("tanks/options/atomic.png"));
                this.heliosForP2.setTankImg(new Texture("tanks/selected/helios.png"));
            }
            drawTank(abramsForP2);
            drawTank(frostForP2);
            drawTank(atomicForP2);
            drawTank(frostForP2);
        }
    }

    public void startGame() {
        float lowerX = startBtn.x * 1.04f;
        float upperX = (startBtn.x + startBtn.width) * 0.96f;
        float lowerY = 450;
        float upperY = 500;
        if (((Gdx.input.getX() < upperX && Gdx.input.getX() > lowerX && Gdx.input.getY() < upperY
                && Gdx.input.getY() > lowerY && Gdx.input.isTouched()) || Gdx.input.isKeyPressed(Input.Keys.ENTER))
                && this.player1.getTank() != null && this.player2.getTank() != null) {
            this.homeScreen.stopBackSound();
            game.setScreen(new GameScreen(game, player1, player2));
            dispose();
        }
    }

    public void goBack() {
        if ((Gdx.input.getX() < 60 && Gdx.input.getY() < game.getWindowY() / 2 - 190 && Gdx.input.isTouched())
                || (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))) {
            this.homeScreen.stopBackSound();
            game.setScreen(new HomeScreen(game));
        }
    }

    // Getters & setters ------------------------------

    public Texture getBackground() {
        return this.background;
    }

    public void setBackground() {
        this.background = new Texture("backgrounds/choose.png");
    }

    // Start ---------------------------------

    public Texture getStartBtnImg() {
        return this.startBtnImg;
    }

    public void setStartBtnImg() {
        this.startBtnImg = new Texture("buttons/start.png");
    }

    public void setStartBtn() {
        this.startBtn = new Rectangle();
        this.startBtn.width = (float) getStartBtnImg().getWidth() / 3;
        this.startBtn.height = (float) getStartBtnImg().getHeight() / 3;
        this.startBtn.x = (game.getWindowX() - this.startBtn.width) / 2;
        this.startBtn.y = 20;
    }

    // Back ---------------------------------

    public Texture getBackBtnImg() {
        return this.backBtnImg;
    }

    public void setBackBtnImg() {
        this.backBtnImg = new Texture("buttons/back.png");
    }

    public void setBackBtn() {
        this.backBtn = new Rectangle();
        this.backBtn.width = (float) getBackBtnImg().getWidth() / 4;
        this.backBtn.height = (float) getBackBtnImg().getHeight() / 4;
        this.backBtn.x = 0;
        this.backBtn.y = this.game.getWindowY() - 80;
    }

    // Choose ---------------------------------

    public Texture getChooseP1Img() {
        return this.chooseP1Img;
    }

    public void setChooseP1() {
        this.chooseP1 = new Rectangle();
        this.chooseP1.width = (float) getChooseP1Img().getWidth() / 2;
        this.chooseP1.height = (float) getChooseP1Img().getHeight() / 2;
        this.chooseP1.x = 50;
        this.chooseP1.y = 80;
    }

    public Texture getChooseP2Img() {
        return this.chooseP2Img;
    }

    public void setChooseP2() {
        this.chooseP2 = new Rectangle();
        this.chooseP2.width = (float) getChooseP2Img().getWidth() / 2;
        this.chooseP2.height = (float) getChooseP2Img().getHeight() / 2;
        this.chooseP2.x = this.game.getWindowX() - this.chooseP2.width - this.chooseP1.x;
        this.chooseP2.y = this.chooseP1.y;
    }

    // Players --------------------------------

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }

    // Override methods ------------------------------

    @Override
    public void dispose() {
        background.dispose();
        chooseP1Img.dispose();
        chooseP2Img.dispose();
        startBtnImg.dispose();
        backBtnImg.dispose();
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