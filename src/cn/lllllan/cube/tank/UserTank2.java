package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank2 extends UserTank implements Tank {

    public UserTank2(int tankID, int x, int y) {
        super(tankID, x, y);
    }

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
