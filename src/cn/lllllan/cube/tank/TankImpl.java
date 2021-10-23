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

public class TankImpl extends Cube implements Tank {

    /**
     * @param MAX_DIRECT_NUMBER     坦克样式数量
     * @param SPEED                 坦克默认移动速度
     * @param MAX_BULLETS_NUMBER    坦克默认最大子弹数量
     * @param direct                坦克朝向
     */
    private static final int MAX_STYLE_NUMBER = 4;
    private static final int SPEED = 25;
    private int max_bullets_number = 3;
    private int direct;

    /**
     * 存放坦克当前发射出、并且存活的子弹
     */
    private Vector<BulletImpl> bullets;

    /**
     * @param tankType  定义坦克的类型
     *
     * <ul>
     *     <li>0 玩家坦克</li>
     *     <li>1 敌人坦克</li>
     * </ul>
     *
     * <p>
     * @param tankID    各类型坦克下对应的坦克样式
     */
    private int tankType;
    private int tankID;

    /**
     * @param imgUrl    不同类型及样式的坦克对应的图片地址
     * @param img       不同类型及样式的坦克对应的图片
     */
    private String imgUrl;
    private Image img = null;

    /**
     * @param moving 坦克的移动状态
     *
     * <ul>
     *      <li>0 - 没有移动</li>
     *      <li>1 - 正在转向</li>
     *      <li>2 - 正在移动</li>
     * </ul>
     */
    private int moving;

    /**
     * 有参构造器，决定坦克的类型、样式、坐标
     *
     * @param tankType 坦克的类型【0 玩家坦克、1 敌方坦克】
     * @param tankID   坦克的样式
     * @param x        x坐标
     * @param y        y坐标
     */
    public TankImpl(int tankType, int tankID, int x, int y) {
        // 调用 Cube 父类的构造方法
        super(x, y, true, true, false, false);
        this.tankType = tankType;
        this.tankID = tankID;
        this.moving = 0;
        bullets = new Vector<>();

        // 默认生命值为 5
        super.setLife(5);

        // 初始化一些基本参数
        setProperty();
    }

    /**
     * 规定同一时刻坦克能够发射的最大子弹数量
     * <p>
     * 为了增加游戏难度，允许一些敌方坦克拥有更高的上限
     *
     * @param max_bullets_number 子弹数量
     */
    public void setMax_bullets_number(int max_bullets_number) {
        this.max_bullets_number = max_bullets_number;
    }

    /**
     * @return 坦克同一时刻发射的最大子弹数量
     */
    public int getMaxBulletsNumber() {
        return max_bullets_number;
    }

    /**
     * 根据最大子弹数量以及当前发射的子弹数量，判断是否能够继续射击
     *
     * @return true/false 是否能够继续射击
     */
    public boolean canShoot() {
        return bullets.size() < max_bullets_number;
    }

    /**
     * 查看该坦克当前发射出、并且存活的子弹数量
     *
     * @return 发射出的子弹数量
     */
    public int getBulletsNumber() {
        return bullets.size();
    }

    /**
     * 判断该坦克是否为敌方坦克
     *
     * @return true/false 是否为敌方坦克
     */
    public boolean isEnemy() {
        return tankType == 1;
    }

    /**
     * 坦克射击后，将该子弹添加到 bullets 中
     *
     * @param bullet 子弹
     */
    public void addBullet(BulletImpl bullet) {
        bullets.add(bullet);
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
    public BulletImpl shoot() {
        // 先判断是否能够继续射击
        if (canShoot()) {
            // 根据坦克的朝向，获取子弹发射时的初始位置
            int[] xy = getBulletCoordinate();
            // 创建对应的子弹对象
            UserBullet userBullet = new UserBullet(xy[0], xy[1], direct, this);
            // 将该子弹，添加到 bullers 中
            addBullet(userBullet);

            return userBullet;
        }
        // 不能继续射击，返回 null
        return null;
    }

    /**
     * 销毁失效的子弹
     *
     * @param bullet 子弹
     */
    public void destroyBullet(BulletImpl bullet) {
        if (bullets.contains(bullet)) bullets.remove(bullet);
    }

    /**
     * 获取坦克默认的移动速度
     *
     * @return 坦克默认的移动速度
     */
    public static int getSPEED() {
        return SPEED;
    }

    /**
     * 获取坦克当前的朝向
     *
     * @return 坦克的朝向
     */
    public int getDirect() {
        return direct;
    }

    /**
     * 改变坦克的朝向
     *
     * @param direct 方向【0 上、1 右、2 下、 3 左】
     */
    public void setDirect(int direct) {
        this.direct = direct % 4;
        setProperty();
    }

    /**
     * 初始化一些基本参数：
     * <p>
     * 坦克的类型：玩家坦克、地方坦克
     * <p>
     * 坦克的样式：
     * <p>
     * 绘制坦克的图片：
     */
    private void setProperty() {

        // 坦克类型只能有两种：0 玩家坦克、1 敌方坦克
        int type = tankType % 2;
        // 两种类型的坦克最多只有四种不同样式
        int id = tankID % MAX_STYLE_NUMBER;

        imgUrl = "/img/tank/" + ((type == 0) ? "user" : "enemy") + "/" + id + "/";

        String imgurl = imgUrl + direct + ".png";
        URL url = BarrierImpl.class.getResource(imgurl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    /**
     * 获取坦克当前的移动状态
     *
     * @return 坦克当前的移动状态
     * <ul>
     *      <li>0 没有移动</li>
     *      <li>1 正在转向</li>
     *      <li>2 正在移动</li>
     * </ul>
     */
    public int getMoving() {
        return moving;
    }

    /**
     * 改变坦克的移动状态
     *
     * @param moving 移动状态
     *               <ul>
     *                    <li>0 没有移动</li>
     *                    <li>1 正在转向</li>
     *                    <li>2 正在移动</li>
     *               </ul>
     */
    public void setMoving(int moving) {
        this.moving = moving;
    }

    /**
     * 设置坦克的坐标
     *
     * @param x x坐标
     * @param y y坐标
     */
    public void setCoordinate(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    /**
     * 根据坦克的位置和朝向，计算子弹发射时的初始位置
     *
     * @return 子弹的坐标
     */
    public int[] getBulletCoordinate() {
        int x, y;
        if (direct == 0) {          // 坦克朝上
            x = super.getX() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
            y = super.getY() - BulletImpl.getSIZE();
        } else if (direct == 1) {   // 坦克朝右
            x = super.getX() + super.getSIZE();
            y = super.getY() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
        } else if (direct == 2) {   // 坦克朝下
            x = super.getX() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
            y = super.getY() + super.getSIZE();
        } else {                    // 坦克朝左
            x = super.getX() - BulletImpl.getSIZE();
            y = super.getY() + super.getSIZE() / 2 - BulletImpl.getSIZE() / 2;
        }
        return new int[]{x, y};
    }

    /**
     * 坦克的绘制方法
     *
     * @param g        画笔
     * @param observer
     */
    public void paint(Graphics g, ImageObserver observer) {
        g.drawImage(img, super.getX(), super.getY(), super.getWidth(), super.getHeight(), observer);
    }

    /**
     * 坦克对象的向上移动方法
     *
     * @param move 移动距离
     */
    public void moveUp(int move) {
        super.setY(super.getY() - move);
    }

    /**
     * 坦克对象的向下移动方法
     *
     * @param move 移动距离
     */
    public void moveDown(int move) {
        super.setY(super.getY() + move);
    }

    /**
     * 坦克对象的向左移动方法
     *
     * @param move 移动距离
     */
    public void moveLeft(int move) {
        super.setX(super.getX() - move);
    }

    /**
     * 坦克对象的向右移动方法
     *
     * @param move 移动距离
     */
    public void moveRight(int move) {
        super.setX(super.getX() + move);
    }
}
