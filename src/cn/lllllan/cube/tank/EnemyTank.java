package cn.lllllan.cube.tank;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.bullet.EnemyBullet;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class EnemyTank extends TankImpl implements Tank {

    public EnemyTank(int tankID, int x, int y) {
        super(1, tankID, x, y);
        super.setLife(tankID + 5);
    }

    @Override
    public BulletImpl shoot() {
        if (canShoot()) {
            int[] xy = getBulletCoordinate();
            EnemyBullet enemyBullet = new EnemyBullet(xy[0], xy[1], super.getDirect(), this);
            super.addBullet(enemyBullet);
            return enemyBullet;
        }
        return null;
    }
}
