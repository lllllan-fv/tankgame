package cn.lllllan.tank;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class UserTank extends TankPlus implements Tank {


    public UserTank(int tankID, int x, int y) {
        super(1, tankID, x, y);
    }
    
}
