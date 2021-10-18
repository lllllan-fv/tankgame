package cn.lllllan.cube;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/


@SuppressWarnings({"all"})

public class Cube {

    /**
     * 绘制的尺寸，长 = 宽，绘制成正方形更加方便
     */
    private static final int SIZE = 50;

    /**
     * @param x             x 坐标
     * @param y             y 坐标
     * @param life          生命值
     */
    private int x;
    private int y;
    private int life;

    /**
     * @param isTank        坦克 or 地形
     * @param canBeBorken   子弹是否可以击破
     * @param canTankPass   坦克是否可以穿过
     * @param canBulletPass 子弹是否可以穿过
     */
    private boolean isTank;
    private boolean canBeBorken;
    private boolean canTankPass;
    private boolean canBulletPass;

    /**
     * 有参构造器
     *
     * @param x             x 坐标
     * @param y             y 坐标
     * @param isTank        坦克 or 地形
     * @param canBeBorken   子弹是否可以击破
     * @param canTankPass   坦克是否可以穿过
     * @param canBulletPass 子弹是否可以穿过
     */
    public Cube(int x, int y, boolean isTank, boolean canBeBorken, boolean canTankPass, boolean canBulletPass) {
        this.x = x;
        this.y = y;
        this.life = 1;
        this.isTank = isTank;
        this.canBeBorken = canBeBorken;
        this.canTankPass = canTankPass;
        this.canBulletPass = canBulletPass;
    }

    /**
     * 设置一些基本属性
     *
     * @param canBeBorken   子弹是否可以击破
     * @param canTankPass   坦克是否可以穿过
     * @param canBulletPass 子弹是否可以穿过
     */
    public void setProperty(boolean canBeBorken, boolean canTankPass, boolean canBulletPass) {
        this.canBeBorken = canBeBorken;
        this.canTankPass = canTankPass;
        this.canBulletPass = canBulletPass;
    }

    /**
     * 获取当前的生命值
     *
     * @return 生命值
     */
    public int getLife() {
        return life;
    }

    /**
     * 设置基础生命值
     *
     * @param life 生命值
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * 存活状态下，扣除一点生命值
     */
    public void lifeDown() {
        if (life > 0) life--;
    }

    /**
     * 存活状态下，恢复一点生命值
     */
    public void lifeUp() {
        if (life > 0) life++;
    }

    /**
     * 判断该物体，是坦克还是地形
     *
     * @return 是否为坦克
     */
    public boolean isTank() {
        return isTank;
    }

    /**
     * @return 子弹是否可击破
     */
    public boolean isCanBeBorken() {
        return canBeBorken;
    }

    /**
     * @return 坦克是否可以穿过
     */
    public boolean isCanTankPass() {
        return canTankPass;
    }

    /**
     * @return 子弹是否可以经过
     */
    public boolean isCanBulletPass() {
        return canBulletPass;
    }

    /**
     * @return x 坐标
     */
    public int getX() {
        return x;
    }

    /**
     * 设置 x 坐标
     *
     * @param x x 坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y 坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 设置 y 坐标
     *
     * @param y y 坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return 绘制尺寸
     */
    public static int getSIZE() {
        return SIZE;
    }

    /**
     * @return 绘制宽度
     */
    public int getWidth() {
        return SIZE;
    }

    /**
     * @return 绘制高度
     */
    public int getHeight() {
        return SIZE;
    }
}
