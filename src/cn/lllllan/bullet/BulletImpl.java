package cn.lllllan.bullet;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * 子弹的基类，规定了一些基本参数和方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class BulletImpl implements Bullet {

    private static final int SIZE = 5;
    private static final int SPEED = 25;

    private int x;
    private int y;
    private int direct;
    private int offsetX;
    private int offsetY;

    private boolean isLive;
    private boolean isEnemy;

    public BulletImpl(int x, int y, int direct, boolean isEnemy) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.isEnemy = isEnemy;
        this.isLive = true;

        // 根据方向设置偏移量 offsetX, offsetY
        switch (direct) {
            case 0: // 方向朝上
                offsetX = 0;
                offsetY = -SPEED;
                break;
            case 1: // 方向朝右
                offsetX = SPEED;
                offsetY = 0;
                break;
            case 2: // 方向朝下
                offsetX = 0;
                offsetY = SPEED;
                break;
            case 3: // 方向朝左
                offsetX = -SPEED;
                offsetY = 0;
                break;
        }
    }

    public static int getSIZE() {
        return SIZE;
    }

    public int getWIDTH() {
        return SIZE;
    }

    public int getHEIGHT() {
        return SIZE;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirect() {
        return direct;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public boolean isLive() {
        return isLive;
    }

    public boolean isEnemy() {
        return isEnemy;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void paint(Graphics g, ImageObserver observer) {
        String s = "/img/bullet/" + (isEnemy ? "enemy" : "user") + ".gif";
        URL url = BarrierImpl.class.getResource(s);
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, x, y, SIZE, SIZE, observer);
    }

    public void move() {
        x += offsetX;
        y += offsetY;
    }

}
