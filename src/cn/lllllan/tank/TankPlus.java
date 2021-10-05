package cn.lllllan.tank;

import cn.lllllan.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * 坦克基类的升级版，提供图片的绘制
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class TankPlus extends TankImpl implements Tank, Runnable {

    private static final String[][] urls = new String[][]{
            {"/img/tank/user1/", "/img/tank/user2/"},
            {"/img/tank/enemy1/", "/img/tank/enemy2/", "/img/tank/enemy3/"}
    };

    /**
     * 定义坦克的类型
     *
     * <ul>
     *     <li>0 绘制的坦克</li>
     *     <li>1 玩家坦克</li>
     *     <li>2 敌人坦克</li>
     * </ul>
     */
    private int tankType;

    /**
     * 坦克编号
     */
    private int tankID;

    private String imgUrl;
    private Image img = null;


    /**
     * Desc: 根据方向找到对应的坦克图片
     *
     * @author lllllan
     * @date 2021/10/5 17:59
     */
    private void setProperty() {
        if (tankType == 0) return;

        int type = Math.min(tankType - 1, urls.length - 1);
        int id = Math.min(tankID, urls[type].length - 1);
        imgUrl = urls[type][id];

        String imgurl = imgUrl + super.getDirect() + ".gif";
        URL url = BarrierImpl.class.getResource(imgurl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    public TankPlus(int tankType, int tankID, int x, int y) {
        super(x, y, 0, 0);

        this.tankType = tankType;
        this.tankID = tankID;

        setProperty();
    }

    @Override
    public void setColorType(int colorType) {
        super.setColorType(colorType);
    }

    @Override
    public void setDirect(int direct) {
        super.setDirect(direct);
        setProperty();
    }

    /***
     * Desc: 根据坦克类型，判断调用父类的坦克绘制，还是图片绘制
     * @param g 画笔
     * @param observer
     * @author lllllan
     * @date 2021/10/5 18:05
     */
    public void paint(Graphics g, ImageObserver observer) {
        if (tankType == 0) {
            super.paint(g);
        } else {
            g.drawImage(img, super.getX(), super.getY(), super.getWIDTH(), super.getHEIGHT(), observer);
        }
    }
}
