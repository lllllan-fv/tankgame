package cn.lllllan.bullet;

import cn.lllllan.cube.tank.TankImpl;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class EnemyBullet extends BulletImpl implements Bullet {

    public EnemyBullet(int x, int y, int direct, TankImpl belong) {
        super(x, y, direct, belong, true);
    }
}
