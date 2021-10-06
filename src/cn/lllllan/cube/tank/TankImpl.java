package cn.lllllan.cube.tank;

import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * 坦克基类的升级版，提供图片的绘制
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class TankImpl extends Cube implements Tank, Runnable {

    private static final String[][] urls = new String[][]{
            {"/img/tank/user1/", "/img/tank/user2/"},
            {"/img/tank/enemy1/", "/img/tank/enemy2/", "/img/tank/enemy3/"}
    };

    private static final int SPEED = 25;
    private int direct;
    private boolean isLive;

    /**
     * 定义坦克的类型
     *
     * <ul>
     *     <li>1 玩家坦克</li>
     *     <li>2 敌人坦克</li>
     * </ul>
     */
    private int tankType;
    private int tankID;

    private String imgUrl;
    private Image img = null;

    private boolean moving;
    private int speed;

    public TankImpl(int tankType, int tankID, int x, int y) {
        super(x, y, true, false, false);
        this.tankType = tankType;
        this.tankID = tankID;
        this.moving = false;
        this.speed = SPEED;

        setProperty();
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
        setProperty();
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Desc: 根据方向找到对应的坦克图片
     *
     * @author lllllan
     * @date 2021/10/5 17:59
     */
    private void setProperty() {
        if (tankType == 0) return;

        int type = Math.min(tankType - 1, urls.length - 1);
        int id = Math.min(tankID, urls[type].length - 1);
        imgUrl = urls[type][id];

        String imgurl = imgUrl + direct + ".gif";
        URL url = BarrierImpl.class.getResource(imgurl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getTankID() {
        return tankID;
    }

    public void setCoordination(int x, int y) {
        super.setX(x);
        super.setY(y);
    }


    public void paint(Graphics g, ImageObserver observer) {
        g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), observer);
    }

    @Override
    public void run() {
    }

    @Override
    public void paint(Graphics g) {
    }

    public void moveUp(int move) {
        super.setY(super.getY() - move);
    }

    public void moveDown(int move) {
        super.setY(super.getY() + move);
    }

    public void moveLeft(int move) {
        super.setX(super.getX() - move);
    }

    public void moveRight(int move) {
        super.setX(super.getX() + move);
    }
}
