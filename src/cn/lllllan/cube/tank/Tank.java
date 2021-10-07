package cn.lllllan.cube.tank;

import java.awt.*;
import java.awt.image.ImageObserver;

@SuppressWarnings({"all"})

public interface Tank {

    public abstract void paint(Graphics g, ImageObserver observer);

    public abstract void moveUp(int move);

    public abstract void moveDown(int move);

    public abstract void moveLeft(int move);

    public abstract void moveRight(int move);
}
