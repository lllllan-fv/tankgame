package cn.lllllan.gif;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public abstract class Gif {

    private int x;
    private int y;
    private int life;

    public Gif(int x, int y, int life) {
        this.x = x;
        this.y = y;
        this.life = life;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLve() {
        return life > 0;
    }

    public abstract void paint(Graphics g, ImageObserver observer);

    public abstract void lifeDown();
}
