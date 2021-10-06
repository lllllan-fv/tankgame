package cn.lllllan.cube.barrier;

import cn.lllllan.cube.Cube;

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

public class BarrierImpl extends Cube implements Barrier {


    /**
     * 绘制对象路径，默认为土墙
     */
    private String imgUrl = "/img/barrier/walls.gif";
    private Image img = null;


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
 
    public BarrierImpl(int x, int y, int barrierType) {
        super(x, y, true, false, false);
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
                super.setsetProperty(false, true, true);
                imgUrl = "/img/barrier/grass.png";
                break;
            case 2: // 水
                super.setsetProperty(false, false, true);
                imgUrl = "/img/barrier/water.gif";
                break;
            case 3: // 铁墙
                super.setsetProperty(false, false, false);
                imgUrl = "/img/barrier/steels.gif";
                break;
            case 0:  // 土墙
            default: //
                super.setsetProperty(true, false, false);
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

    public void paint(Graphics g, ImageObserver observer) {
        g.drawImage(img, super.getX(), super.getY(), super.getSIZE(), super.getSIZE(), observer);
    }
}
