package cn.lllllan.data;

import cn.lllllan.cube.tank.EnemyTank;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Data1 extends Data {
    public Data1() {
        super();

        super.addEnemyTank(new EnemyTank(0, 400, 400));
        super.addEnemyTank(new EnemyTank(0, 500, 400));

        super.setUserTanksCoordinate(new int[][]{{100, 100}, {200, 100}});
    }
}
