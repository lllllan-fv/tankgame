package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank2 extends UserTank implements Tank {

    /**
     * 有参构造器
     *
     * @param tankID 坦克样式的编号
     * @param x      x坐标
     * @param y      y坐标
     */
    public UserTank2(int tankID, int x, int y) {
        super(tankID, x, y);
    }

    /**
     * 根据键盘事件，判断坦克的移动方向
     * <p>
     * 坦克玩家二，其方向键为 方向键
     *
     * @param e 键盘事件
     * @return 移动方向
     */
    public int getDirect(KeyEvent e) {
        int ans = -1;
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            ans = 0;
        } else if (code == KeyEvent.VK_RIGHT) {
            ans = 1;
        } else if (code == KeyEvent.VK_DOWN) {
            ans = 2;
        } else if (code == KeyEvent.VK_LEFT) {
            ans = 3;
        }

        return ans;
    }
}
