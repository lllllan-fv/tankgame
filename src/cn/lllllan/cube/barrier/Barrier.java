package cn.lllllan.cube.barrier;

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
 
    public abstract void paint(Graphics g, ImageObserver observer);
}
