package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank1 extends UserTank implements Tank {

    /**
     * 有参构造器
     *
     * @param tankID 坦克样式的编号
     * @param x      x坐标
     * @param y      y坐标
     */
    public UserTank1(int tankID, int x, int y) {
        super(tankID, x, y);
    }

    /**
     * 根据键盘事件，判断坦克的移动方向
     * <p>
     * 坦克玩家一，其方向键为 wasd
     *
     * @param e 键盘事件
     * @return 移动方向
     */
    public int getDirect(KeyEvent e) {
        int ans = -1;
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            ans = 0;
        } else if (code == KeyEvent.VK_D) {
            ans = 1;
        } else if (code == KeyEvent.VK_S) {
            ans = 2;
        } else if (code == KeyEvent.VK_A) {
            ans = 3;
        }

        return ans;
    }

}
