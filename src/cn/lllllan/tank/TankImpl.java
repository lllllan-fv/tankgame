package cn.lllllan.tank;

import cn.lllllan.bullet.BulletImpl;

import java.awt.*;
import java.util.Vector;


/**
 * 坦克的基类，定义了一些基本的参数和方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class TankImpl implements Tank, Runnable {

    /**
     * 坦克设计成一个正方形的样式，方便各个方向的绘制
     */
    private static final int SIZE = 50;
    private static final int WIDTH = SIZE;
    private static final int HEIGHT = SIZE;

    /**
     * 坦克的移动速度
     */
    private static final int SPEED = 10;

    /**
     * 分别为坦克左上角 x,y 坐标，朝向和颜色
     * <p>
     * direct 0 上 1 右 2 下 3 左
     */
    private int x;
    private int y;
    private int direct;
    private int colorType;

    /**
     * 记录坦克是否存活
     */
    private boolean isLive;

    /**
     * 子弹列表
     */
    Vector<BulletImpl> bullets;

    /**
     * 规定了坦克可以使用的颜色组
     */
    private static final Color[] TANK_COLORS = new Color[]{
            Color.lightGray, Color.gray, Color.pink, Color.orange,
            Color.yellow, Color.green, Color.magenta, Color.cyan, Color.blue
    };


    /**
     * Desc: 无参构造
     *
     * @author lllllan
     * @date 2021/10/4 22:40
     */
    public TankImpl() {
        isLive = true;
        x = y = direct = colorType = 0;
    }

    /**
     * Desc: 有参构造，规定了坦克的初始位置、方向和颜色
     *
     * @param x         左上角 x 坐标
     * @param y         左上角 y 坐标
     * @param direct    坦克朝向
     * @param colorType 坦克颜色
     * @author lllllan
     * @date 2021/10/4 22:41
     */
    public TankImpl(int x, int y, int direct, int colorType) {
        isLive = true;
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.colorType = colorType;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static Color[] getTankColors() {
        return TANK_COLORS;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
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

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getColorType() {
        return colorType;
    }

    public void setColorType(int colorType) {
        this.colorType = colorType;
    }

    /**
     * Desc: 根据坦克的坐标、朝向、颜色，绘制出坦克
     *
     * @param g 画笔
     * @author lllllan
     * @date 2021/10/4 22:48
     */
    public void paint(Graphics g) {
        g.setColor(TANK_COLORS[colorType]);

        // 绘制坦克主体
        switch (direct) {
            case 0: // 炮筒朝上
            case 2: // 炮筒朝下
                // 左边的履带
                g.fill3DRect(x, y, SIZE / 5, SIZE, false);
                // 右边的履带
                g.fill3DRect(x + SIZE * 4 / 5, y, SIZE / 5, SIZE, false);
                // 中间方形车身
                g.fill3DRect(x + SIZE / 5, y + SIZE / 10, SIZE * 3 / 5, SIZE * 4 / 5, false);
                // 中间圆形车盖
                g.fillOval(x + SIZE / 5, y + SIZE / 5, SIZE * 3 / 5, SIZE * 3 / 5);
                break;
            case 1: // 炮筒朝右
            case 3: // 炮筒朝左
                // 上边的履带
                g.fill3DRect(x, y, SIZE, SIZE / 5, false);
                // 下边的履带
                g.fill3DRect(x, y + SIZE * 4 / 5, SIZE, SIZE / 5, false);
                // 中间方形车身
                g.fill3DRect(x + SIZE / 10, y + SIZE / 5, SIZE * 4 / 5, SIZE * 3 / 5, false);
                // 中间圆形车盖
                g.fillOval(x + SIZE / 5, y + SIZE / 5, SIZE * 3 / 5, SIZE * 3 / 5);
                break;
        }

        // 绘制炮筒
        switch (direct) {
            case 0: // 炮筒朝上
                g.fill3DRect(x + SIZE * 22 / 50, y, SIZE * 4 / 50, SIZE / 5, false);
                break;
            case 1: // 炮筒朝右
                g.fill3DRect(x + SIZE * 4 / 5, y + SIZE * 22 / 50, SIZE / 5, SIZE * 4 / 50, false);
                break;
            case 2: // 炮筒朝下
                g.fill3DRect(x + SIZE * 22 / 50, y + SIZE * 4 / 5, SIZE * 4 / 50, SIZE / 5, false);
                break;
            case 3: // 炮筒朝左
                g.fill3DRect(x, y + SIZE * 22 / 50, SIZE / 5, SIZE * 4 / 50, false);
                break;
        }
    }

    /**
     * Desc: 向上移动
     *
     * @param move 移动距离
     * @author lllllan
     * @date 2021/10/4 23:59
     */
    public void moveUp(int move) {
        y -= move;
    }

    /**
     * Desc: 向下移动
     *
     * @param move 移动距离
     * @author lllllan
     * @date 2021/10/4 23:59
     */
    public void moveDown(int move) {
        y += move;
    }

    /**
     * Desc: 向左移动
     *
     * @param move 移动距离
     * @author lllllan
     * @date 2021/10/4 23:59
     */
    public void moveLeft(int move) {
        x -= move;
    }

    /**
     * Desc: 向右移动
     *
     * @param move 移动距离
     * @author lllllan
     * @date 2021/10/4 23:59
     */
    public void moveRight(int move) {
        x += move;
    }

    /**
     * Desc: Runnable 的 run 方法
     *
     * @author lllllan
     * @date 2021/10/5 0:00
     */
    public void run() {

    }

}
