package cn.lllllan.bullet;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class UserBullet extends BulletImpl implements Bullet {
    public UserBullet(int x, int y, int direct) {
        super(x, y, direct, false);
    }
}
