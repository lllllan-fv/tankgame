package cn.lllllan.barrier;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * 障碍物的基类，规定了最基础的参数和方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class BarrierImpl implements Barrier {

    /**
     * 规定了障碍物的尺寸
     */
    private static final int SIZE = 50;
    private static final int WIDTH = SIZE;
    private static final int HEIGHT = SIZE;

    /**
     * 三种墙体的图片路径
     */
    private static final String[] imgUrls = new String[]{
            "/img/barrier/walls.gif",
            "/img/barrier/water.gif",
            "/img/barrier/steels.gif"
    };

    /**
     * 障碍物左上角的 x,y 坐标
     */
    private int x;
    private int y;

    /**
     * 绘制对象路径，默认为土墙
     */
    private String imgUrl = "/img/barrier/walls.gif";
    private Image img = null;

    /**
     * 墙体是否可以被子弹击破
     */
    private boolean canBeBorken;

    /**
     * 坦克是否可以通过墙体 （比如草丛）
     */
    private boolean canTankPass;

    /**
     * 子弹是否可以穿过墙体 （比如草丛、水）
     */
    private boolean canBulletPass;

    /**
     * 墙体类型
     * <ul>
     *     <li>0 土墙</li>
     *     <li>1 草丛</li>
     *     <li>2 水</li>
     *     <li>3 铁墙</li>
     * </ul>
     */
    private int barrierType;

    /**
     * Desc: 有参构造
     *
     * @param x           起始位置左上角 x 坐标
     * @param y           起始位置左上角 y 坐标
     * @param barrierType 障碍物类型
     * @author lllllan
     * @date 2021/10/5 16:32
     */
    public BarrierImpl(int x, int y, int barrierType) {
        this.x = x;
        this.y = y;
        this.barrierType = barrierType;

        setProperty();
    }

    /**
     * Desc: 根据障碍物类型，蛇者相关的属性
     * <ul>
     *      <li>0 土墙: 子弹可击破、坦克不可穿过、子弹不可穿过</li>
     *      <li>1 草丛: 子弹不可击破、坦克可穿过、子弹可穿过</li>
     *      <li>2 水: 子弹不可击破、坦克不可穿过、子弹可穿过</li>
     *      <li>3 铁墙: 子弹不可击破、坦克不可穿过、子弹不可穿过</li>
     * </ul>
     *
     * @author lllllan
     * @date 2021/10/5 16:33
     */
    private void setProperty() {
        switch (barrierType) {
            case 1: // 草丛
                canBeBorken = false;
                canTankPass = true;
                canBulletPass = true;
                imgUrl = "/img/barrier/grass.png";
                break;
            case 2: // 水
                canBeBorken = false;
                canTankPass = false;
                canBulletPass = true;
                imgUrl = "/img/barrier/water.gif";
                break;
            case 3: // 铁墙
                canBeBorken = false;
                canTankPass = false;
                canBulletPass = false;
                imgUrl = "/img/barrier/steels.gif";
                break;
            case 0:  // 土墙
            default: //
                canBeBorken = true;
                canTankPass = false;
                canBulletPass = false;
                imgUrl = "/img/barrier/walls.gif";
                break;
        }

        URL url = BarrierImpl.class.getResource(imgUrl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    public int getBarrierType() {
        return barrierType;
    }

    /**
     * Desc: 修改障碍物的类型
     * <ul>
     *     <li>0 土墙</li>
     *     <li>1 草丛</li>
     *     <li>2 水</li>
     *     <li>3 铁墙</li>
     * </ul>
     *
     * @param barrierType 障碍物类型
     * @author lllllan
     * @date 2021/10/5 16:31
     */
    public void setBarrierType(int barrierType) {
        this.barrierType = barrierType;
        setProperty();
    }

    public static int getSIZE() {
        return SIZE;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * Desc: 障碍物的绘制方法
     *
     * @author lllllan
     * @date 2021/10/5 10:27
     */
    public void paint(Graphics g, ImageObserver observer) {
        g.drawImage(img, x, y, SIZE, SIZE, observer);
    }
}
