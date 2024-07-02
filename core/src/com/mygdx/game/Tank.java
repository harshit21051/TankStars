package com.mygdx.game;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Tank {
    private static Map<String, Tank> instances = new HashMap<String, Tank>();

    // Flyweight design pattern
    //----------------------------------------------------

    public static Tank getInstance(Player p, Texture tankImg) {
        String key = p + "," + tankImg;
        if (!instances.containsKey(key)) {
            instances.put(key, new Tank(p, tankImg));
        }
        return instances.get(key);
    }

    //----------------------------------------------------

    private final Player player;
    private Texture tankImg;
    private Rectangle tank;

    private Tank(Player p, Texture tankImg) {
        this.player = p;
        this.tankImg = tankImg;
        this.tank = new Rectangle();
    }

    // ----------------------------------------------------

    public Texture getTankImg() {
        return this.tankImg;
    }

    public void setTankImg(Texture tankImg) {
        this.tankImg = tankImg;
    }

    public Rectangle getTank() {
        return this.tank;
    }

    public void setTank(float x, float y) {
        this.tank = new Rectangle();
        this.tank.width = (float) getTankImg().getWidth() / 3;
        this.tank.height = (float) getTankImg().getHeight() / 3;
        this.tank.x = x;
        this.tank.y = y;
    }

    // ----------------------------------------------------

    public void selectTank() {
        this.player.setTank(this);
    }

    public void deselectTank() {
        this.player.setTank(null);
    }
}