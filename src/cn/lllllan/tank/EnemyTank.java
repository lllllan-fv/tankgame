package cn.lllllan.tank;

import cn.lllllan.barrier.BarrierImpl;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class EnemyTank extends TankImpl implements Tank, Runnable {

    /**
     * 定义坦克的类型
     *
     * <ul>
     *     <li>0 绘制的坦克</li>
     *     <li>1 图片的坦克1</li>
     *     <li>2 图片的坦克2</li>
     *     <li>3 图片的坦克3</li>
     * </ul>
     */
    private int tankType;

    private String imgUrl;
    private Image img = null;

    /**
     * Desc: 根据方向找到对应的坦克图片
     *
     * @author lllllan
     * @date 2021/10/5 18:13
     */
    private void setProperty() {
        if (tankType == 0) return;
        String imgurl = imgUrl + super.getDirect() + ".gif";
        URL url = BarrierImpl.class.getResource(imgurl);
        img = Toolkit.getDefaultToolkit().getImage(url);
    }

    public EnemyTank(int tankType, int x, int y) {
        super(x, y, 0, 0);
        this.tankType = tankType;

        if (tankType == 0) {
        } else if (tankType == 1) {
            imgUrl = "/img/tank/enemy1/";
        } else if (tankType == 2) {
            imgUrl = "/img/tank/enemy2/";
        } else {
            imgUrl = "/img/tank/enemy3/";
        }

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
