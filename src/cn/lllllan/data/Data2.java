package cn.lllllan.data;

import cn.lllllan.cube.tank.EnemyTank;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Data2 extends Data {
    public Data2() {
        super();
        
        super.addEnemyTank(new EnemyTank(0, 400, 400));

        super.setUserTanksCoordinate(new int[][]{{200, 200}, {300, 300}});
    }
}
