package cn.lllllan.cube.tank;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.bullet.EnemyBullet;

import java.util.Random;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class EnemyTank extends TankImpl implements Tank {

    private static final int MAX_TURNING_RANDOM_FACTOR = 20;
    private static final int MIN_TURNING_RANDOM_FACTOR = 10;
    private int turningRandom;

    public EnemyTank(int tankID, int x, int y) {
        super(1, tankID, x, y);
        super.setLife(tankID + 5);
        turningRandom = rand();
    }

    public int rand() {
        return new Random().nextInt(MAX_TURNING_RANDOM_FACTOR - MIN_TURNING_RANDOM_FACTOR + 1) + MIN_TURNING_RANDOM_FACTOR;
    }

    public void forcedTurn() {
        turningRandom = 0;
    }

    public boolean turning() {
        if (turningRandom == 0) {
            super.setDirect(new Random().nextInt(4));
            turningRandom = rand();
            return true;
        } else {
            turningRandom--;
            return false;
        }
    }

    public boolean keepMoving() {
        int moving = super.getMoving();
        if (moving == 0) {
            super.setMoving(1);
            return false;
        } else {
            super.setMoving(new Random().nextInt(2));
            return true;
        }
    }

    @Override
    public BulletImpl shoot() {
//        if (canShoot()) {
        int[] xy = getBulletCoordinate();
        EnemyBullet enemyBullet = new EnemyBullet(xy[0], xy[1], super.getDirect(), this);
        super.addBullet(enemyBullet);
        return enemyBullet;
//        }
//        return null;
    }
}
