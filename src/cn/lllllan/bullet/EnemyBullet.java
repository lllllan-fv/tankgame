package cn.lllllan.bullet;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class EnemyBullet extends BulletImpl implements Bullet {
    public EnemyBullet(int x, int y, int direct) {
        super(x, y, direct, true);
    }
}
