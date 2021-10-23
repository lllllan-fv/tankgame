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

    /**
     * 想实现敌方坦克的自由移动，让其在移动一定步数以后必须转向
     *
     * @param MAX_TURNING_RANDOM_FACTOR 最大转向随机数
     * @param MIN_TURNING_RANDOM_FACTOR 最小转向随机数
     */
    private static final int MAX_TURNING_RANDOM_FACTOR = 20;
    private static final int MIN_TURNING_RANDOM_FACTOR = 10;
    private int turningRandom;

    /**
     * 有参构造器
     *
     * @param tankID 坦克样式的编号
     * @param x      x坐标
     * @param y      y坐标
     */
    public EnemyTank(int tankID, int x, int y) {
        super(1, tankID, x, y);
        // 为敌方坦克设置更高的生命值、子弹数
        super.setLife(tankID + 5);
        super.setMax_bullets_number(6);
        turningRandom = rand();
    }

    /**
     * 为实现坦克的自由移动，让其在移动一定步数以后必须转向
     *
     * @return 移动步数
     */
    public int rand() {
        return new Random().nextInt(MAX_TURNING_RANDOM_FACTOR - MIN_TURNING_RANDOM_FACTOR + 1) + MIN_TURNING_RANDOM_FACTOR;
    }

    /**
     * 强制转向，将 turningRandom 设为0
     */
    public void forcedTurn() {
        turningRandom = 0;
    }

    /**
     * @return 根据移动步数要求，判断是否需要转向
     */
    public boolean turning() {
        if (turningRandom == 0) {
            // 随机转向
            super.setDirect(new Random().nextInt(4));
            // 再次随机生成移动步数
            turningRandom = rand();
            return true;
        } else {
            turningRandom--;
            return false;
        }
    }

    /**
     * 让敌方坦克保持持续的移动
     *
     * @return 返回敌方坦克是否正在移动
     */
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

    /**
     * 坦克的射击方法
     * <p>
     * 如果能够射击，则返回此次射击的子弹对象
     * <p>
     * 如果不能射击，返回null
     *
     * @return 子弹/null
     */
    @Override
    public BulletImpl shoot() {
        // 先判断是否能够继续射击
        if (canShoot()) {
            // 根据天纳克的朝向，获取子弹发射的初始位置
            int[] xy = getBulletCoordinate();
            // 创建对应的敌方子弹
            EnemyBullet enemyBullet = new EnemyBullet(xy[0], xy[1], super.getDirect(), this);
            // 将该子弹，添加到 bullets 中
            super.addBullet(enemyBullet);
            return enemyBullet;
        }
        // 不能继续射击，返回 null
        return null;
    }
}
