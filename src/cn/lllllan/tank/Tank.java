package cn.lllllan.tank;

import java.awt.*;


/**
 * <p>坦克对象需要继承的接口，提供了最基本的方法：</p>
 * <ul>
 *     <li>paint()</li>
 *     <li>moveUp()</li>
 *     <li>moveDown()</li>
 *     <li>moveLeft()</li>
 *     <li>moveDown()</li>
 * </ul>
 * <p>其中，move类的移动方法，需要传递移动距离参数，移动的距离交由放置坦克的Jpanel去判断</p>
 *
 * @author lllllan
 */

@SuppressWarnings({"all"})

public interface Tank {

    /**
     * Desc: 坦克的绘制方法
     *
     * @param g 画笔
     * @return
     * @author lllllan
     * @date 2021/10/4 22:01
     */
    public abstract void paint(Graphics g);

    /**
     * Desc: 坦克向上移动的方法
     *
     * @param move 移动距离
     * @return
     * @author lllllan
     * @date 2021/10/4 22:01
     */
    public abstract void moveUp(int move);

    /**
     * Desc:
     *
     * @param move 移动距离
     * @return
     * @author lllllan
     * @date 2021/10/4 22:01
     */
    public abstract void moveDown(int move);

    /**
     * Desc:
     *
     * @param move 移动距离
     * @return
     * @author lllllan
     * @date 2021/10/4 22:01
     */
    public abstract void moveLeft(int move);

    /**
     * Desc:
     *
     * @param move 移动距离
     * @return
     * @author lllllan
     * @date 2021/10/4 22:01
     */
    public abstract void moveRight(int move);
}
