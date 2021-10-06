package cn.lllllan.bullet;

import java.awt.*;

/**
 * 子弹的基类，规定了一些基本参数和方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class BulletImpl implements Bullet {

    /**
     * 规定子弹的大小
     */
    private static final int SIZE = 4;

    /**
     * 子弹的移动速度
     */
    private static final int SPEED = 10;

    /**
     * 分别是子弹左上角的 x,y 坐标，移动方向，偏移量 offsetX, offsetY
     */
    private int x;
    private int y;
    private int direct;
    private int offsetX;
    private int offsetY;

    /**
     * 记录子弹是否存活
     */
    private boolean isLive;
    private boolean isEnemy;

    /**
     * Desc: 有参构造，规定了子弹的起始位置和移动方向
     * <p>并根据移动方向计算好 x,y 的偏移量
     *
     * @param x      起始位置左上角 x 坐标
     * @param y      起始位置左上角 y 坐标
     * @param direct 移动方向
     * @author lllllan
     * @date 2021/10/5 0:33
     */
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

    /**
     * Desc: 子弹的绘制
     *
     * @param g 画笔
     * @author lllllan
     * @date 2021/10/5 0:23
     */
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, SIZE, SIZE);
    }

    /**
     * Desc: 子弹的移动
     *
     * @author lllllan
     * @date 2021/10/5 0:23
     */
    public void move() {
        x += offsetX;
        y += offsetY;
    }
 
}
