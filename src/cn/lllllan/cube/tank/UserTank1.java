package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank1 extends UserTank implements Tank {

    public UserTank1(int tankID, int x, int y) {
        super(tankID, x, y);
    }

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
