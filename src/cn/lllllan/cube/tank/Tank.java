package cn.lllllan.cube.tank;

import java.awt.*;

@SuppressWarnings({"all"})

public interface Tank {

    public abstract void paint(Graphics g);

    public abstract void moveUp(int move);

    public abstract void moveDown(int move);

    public abstract void moveLeft(int move);

    public abstract void moveRight(int move);
}
