package cn.lllllan.cube.tank;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.bullet.UserBullet;

import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank extends TankImpl implements Tank {

    private static final int MAX_BULLETS_NUMBER = 5;
    private Vector<BulletImpl> bullets;

    public UserTank(int tankID, int x, int y) {
        super(0, tankID, x, y);
        bullets = new Vector<>();
    }

    public static int getMaxBulletsNumber() {
        return MAX_BULLETS_NUMBER;
    }

    public boolean canShoot() {
        return bullets.size() < MAX_BULLETS_NUMBER;
    }

    public BulletImpl shoot() {
        if (canShoot()) {
            int[] xy = getBulletCoordinate();
            UserBullet userBullet = new UserBullet(xy[0], xy[1], super.getDirect());
            bullets.add(userBullet);
            return userBullet;
        }
        return null;
    }

    public void destroyBullet(BulletImpl bullet) {
        bullets.remove(bullet);
    }

    public int getDirect(KeyEvent e) {
        return -1;
    }

    public void startMoving() {
        super.setMoving(2);
    }

    public void keepMoving() {
        super.setMoving(2);
    }

    public void stopMoving() {
        super.setMoving(0);
    }

    public void changeDirect() {
        super.setMoving(1);
    }
}
