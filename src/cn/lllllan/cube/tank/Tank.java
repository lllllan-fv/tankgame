package cn.lllllan.cube.tank;

import java.awt.*;
import java.awt.image.ImageObserver;

@SuppressWarnings({"all"})

public interface Tank {

    /**
     * 规定坦克对象必须要用的绘制方法，需要能绘制图片
     *
     * @param g        画笔
     * @param observer
     */
    public abstract void paint(Graphics g, ImageObserver observer);

    /**
     * 坦克对象的向上移动方法
     *
     * @param move 移动距离
     */
    public abstract void moveUp(int move);

    /**
     * 坦克对象的向下移动方法
     *
     * @param move 移动距离
     */
    public abstract void moveDown(int move);

    /**
     * 坦克对象的向左移动方法
     *
     * @param move 移动距离
     */
    public abstract void moveLeft(int move);

    /**
     * 坦克对象的向右移动方法
     *
     * @param move 移动距离
     */
    public abstract void moveRight(int move);
}
