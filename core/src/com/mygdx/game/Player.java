package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    final TankStars game;
    Tank tank;

    private Texture playerTankImg;
    private Texture fuelMeterImg;
    private Rectangle fuelMeter;
    private int fuel;

    public Player(TankStars game) {
        this.game = game;
    }

    // Getters & setters
    // ----------------------------------------------------------------------------------------------

    // Tank -------------------------------------------------------------------

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    // Fuel meter --------------------------------------------------------------

    public int getFuel() {
        return this.fuel;
    }

    public int maxFuel(){
        return 500;
    }

    public void refuel() {
        this.fuel = this.maxFuel();
    }

    public void decrFuel() {
        this.fuel--;
    }

    public Texture getFuelMeterImg() {
        return this.fuelMeterImg;
    }

    public Rectangle getFuelMeter() {
        return this.fuelMeter;
    }

    public void setFuelMeterImg(int perc) {
        if (perc == 100) {
            this.fuelMeterImg = new Texture("fuelMeterElements/fuelBar100%.png");
        } else if (perc == 75) {
            this.fuelMeterImg = new Texture("fuelMeterElements/fuelBar75%.png");
        } else if (perc == 50) {
            this.fuelMeterImg = new Texture("fuelMeterElements/fuelBar50%.png");
        } else if (perc == 25) {
            this.fuelMeterImg = new Texture("fuelMeterElements/fuelBar25%.png");
        } else {
            this.fuelMeterImg = new Texture("fuelMeterElements/fuelBar0%.png");
        }
    }

    public void setFuelMeter(float x, float y) {
        this.fuelMeter = new Rectangle();
        this.fuelMeter.width = (float) getFuelMeterImg().getWidth() / 2;
        this.fuelMeter.height = (float) getFuelMeterImg().getHeight() / 2;
        this.fuelMeter.x = x;
        this.fuelMeter.y = y;
    }

    public Texture getPlayerTankImg() {
        return this.playerTankImg;
    }

    public void setPlayerTankImg(Texture image) {
        this.playerTankImg = image;
    }
}