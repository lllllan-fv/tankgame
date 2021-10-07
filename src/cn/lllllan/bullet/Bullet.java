package cn.lllllan.bullet;

import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * 子弹对象需要继承的接口，提供一些最基础的方法
 *
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public interface Bullet {

    public abstract void paint(Graphics g, ImageObserver observer);

    public abstract void move();

}
