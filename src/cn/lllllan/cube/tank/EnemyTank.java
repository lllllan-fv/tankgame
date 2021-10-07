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
    }

    public BulletImpl shoot() {
        int[] xy = getBulletCoordinate();
        return new EnemyBullet(xy[0], xy[1], super.getDirect());
    }

}
