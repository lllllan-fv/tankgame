package cn.lllllan.bullet;

import java.awt.*;

/**
 * 子弹对象需要继承的接口，提供一些最基础的方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public interface Bullet {

    /**
     * Desc: 子弹的绘制
     *
     * @param g 画笔
     * @author lllllan
     * @date 2021/10/5 0:10
     */
    public abstract void paint(Graphics g);

    /**
     * Desc: 子弹的移动
     *
     * @author lllllan
     * @date 2021/10/5 0:11
     */
    public abstract void move();

}
