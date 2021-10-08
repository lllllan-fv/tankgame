package cn.lllllan.cube.tank;

import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank extends TankImpl implements Tank {
    
    public UserTank(int tankID, int x, int y) {
        super(0, tankID, x, y);
        super.setLife(5);
    }

    public int getDirect(KeyEvent e) {
        return -1;
    }

    public void startMoving() {
        super.setMoving(2);
    }

    public void keepMoving() {
        super.setMoving(2);
    }

    public void stopMoving() {
        super.setMoving(0);
    }

    public void changeDirect() {
        super.setMoving(1);
    }
}
