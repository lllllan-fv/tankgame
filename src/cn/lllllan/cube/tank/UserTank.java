package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank extends TankImpl implements Tank {

    /**
     * 有参构造
     *
     * @param tankID 坦克样式的编号
     * @param x      x坐标
     * @param y      y坐标
     */
    public UserTank(int tankID, int x, int y) {
        super(0, tankID, x, y);
        // 设置生命值为 5
        super.setLife(5);
    }

    /**
     * 根据键盘事件，判断是哪个移动方向。具体看子类的实现
     *
     * @param e 键盘事件
     * @return 移动方向
     */
    public int getDirect(KeyEvent e) {
        return -1;
    }

    /**
     * 将坦克的移动状态改为：正在移动
     * <p>
     * 坦克的移动状态分为：没有移动、正在转向、正在移动
     * <p>
     * 这个方法是让坦克从【正在转向】过度到【正在移动】
     */
    public void startMoving() {
        super.setMoving(2);
    }

    /**
     * 将坦克的移动状态改为：停止不动
     */
    public void stopMoving() {
        super.setMoving(0);
    }

    /**
     * 将坦克的移动状态改为：正在转向
     */
    public void turning() {
        super.setMoving(1);
    }
}
