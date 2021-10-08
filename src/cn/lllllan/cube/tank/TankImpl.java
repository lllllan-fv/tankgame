package cn.lllllan.cube.tank;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.bullet.UserBullet;
import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.Vector;

/**
 * 坦克基类的升级版，提供图片的绘制
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class TankImpl extends Cube implements Tank, Runnable {

    private static final int NUMBER = 4;
    private static final int SPEED = 25;
    private static final int MAX_BULLETS_NUMBER = 5;
    private int direct;
    private boolean isLive;

    private Vector<BulletImpl> bullets;

    /**
     * 定义坦克的类型
     *
     * <ul>
     *     <li>0 玩家坦克</li>
     *     <li>1 敌人坦克</li>
     * </ul>
     */
    private int tankType;
    private int tankID;

    private String imgUrl;
    private Image img = null;

    /**
     * <ul>
     *     <li>0 - 不动</li>
     *     <li>1 - 转向</li>
     *     <li>2 - 移动</li>
     * </ul>
     */
    private int moving;

    public TankImpl(int tankType, int tankID, int x, int y) {
        super(x, y, true, true, false, false);
        this.tankType = tankType;
        this.tankID = tankID;
        this.moving = 0;
        bullets = new Vector<>();
        setProperty();
    }

    public static int getMaxBulletsNumber() {
        return MAX_BULLETS_NUMBER;
    }

    public boolean canShoot() {
        return bullets.size() < MAX_BULLETS_NUMBER;
    }

    public void addBullet(BulletImpl bullet) {
        bullets.add(bullet);
    }

    public BulletImpl shoot() {
        if (canShoot()) {
            int[] xy = getBulletCoordinate();
            UserBullet userBullet = new UserBullet(xy[0], xy[1], direct);
            addBullet(userBullet);
            return userBullet;
        }
        return null;
    }

    public void destroyBullet(BulletImpl bullet) {
        if (bullets.contains(bullet))
            bullets.remove(bullet);
    }

    public boolean isBulletBelongTo(BulletImpl bullet) {
        return bullets.contains(bullet);
    }

    public static int getSPEED() {
        return SPEED;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct % NUMBER;
        setProperty();
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    private void setProperty() {

        int type = tankType % 2;
        int id = tankID % NUMBER;

        imgUrl = "/img/tank/" + ((type == 0) ? "user" : "enemy") + "/" + id + "/";

        String imgurl = imgUrl + direct + ".png";
        URL url = BarrierImpl.class.getResource(imgurl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    public int getMoving() {
        return moving;
    }

    public void setMoving(int moving) {
        this.moving = moving;
    }

    public int getTankID() {
        return tankID;
    }

    public void setCoordination(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    public int[] getBulletCoordinate() {
        int x, y;
        if (direct == 0) {
            x = super.getX() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
            y = super.getY() - BulletImpl.getSIZE();
        } else if (direct == 1) {
            x = super.getX() + super.getSIZE();
            y = super.getY() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
        } else if (direct == 2) {
            x = super.getX() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
            y = super.getY() + super.getSIZE();
        } else {
            x = super.getX() - BulletImpl.getSIZE();
            y = super.getY() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
        }
        return new int[]{x, y};
    }

    public void paint(Graphics g, ImageObserver observer) {
        g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), observer);
    }

    @Override
    public void run() {
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
