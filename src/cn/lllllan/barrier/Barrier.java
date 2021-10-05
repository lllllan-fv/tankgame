package cn.lllllan.barrier;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 障碍物对象需要继承的接口，规定了最基础的方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public interface Barrier {

    /**
     * Desc: 障碍物的绘制方法
     *
     * @return
     * @author lllllan
     * @date 2021/10/5 9:57
     */
    public abstract void paint(Graphics g, ImageObserver observer);
}
