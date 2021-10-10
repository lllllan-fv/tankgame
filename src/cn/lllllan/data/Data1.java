package cn.lllllan.data;

import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.GrassBarrier;
import cn.lllllan.cube.barrier.StellBarrier;
import cn.lllllan.cube.barrier.WallBarrier;
import cn.lllllan.cube.barrier.WaterBarrier;
import cn.lllllan.cube.tank.EnemyTank;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Data1 extends Data {
    public Data1() {
        super();

        int size = Cube.getSIZE();

        for (int i = 4; i <= 8; i += 2) {
            int x = i * size, _x = (i + 10) * size, y = 2 * size;
            super.addEnemyTank(new EnemyTank(0, x, y));
            super.addEnemyTank(new EnemyTank(0, _x, y));
        }

        for (int i = 4; i <= 8; ++i) {
            int x = i * size, _x = (i + 10) * size;
            int y = 3 * size, _y = 4 * size;
            super.addBarrier(new StellBarrier(x, y));
            super.addBarrier(new StellBarrier(_x, y));
            super.addBarrier(new WallBarrier(x, _y));
            super.addBarrier(new WallBarrier(_x, _y));
        }

        for (int i = 10; i <= 12; ++i) {
            for (int j = 4; j <= 7; ++j) {
                int x = i * size, y = j * size;
                super.addBarrier(new StellBarrier(x, y));
            }
        }

        super.addBarrier(new StellBarrier(11 * size, 8 * size));
        super.addBarrier(new GrassBarrier(10 * size, 8 * size));
        super.addBarrier(new GrassBarrier(12 * size, 8 * size));

        for (int i = 9; i <= 13; ++i) {
            int x = i * size, y = 11 * size, _y = 13 * size;
            super.addBarrier(new WallBarrier(x, y));
            super.addBarrier(new WallBarrier(x, _y));
        }
        super.addBarrier(new WallBarrier(9 * size, 12 * size));
        super.addBarrier(new WallBarrier(13 * size, 12 * size));

        for (int i = 10; i <= 12; ++i) {
            int x = i * size, y = 12 * size;
            super.addBarrier(new WaterBarrier(x, y));
        }

        int x1 = 4 * size, y1 = 10 * size;
        int x2 = 18 * size, y2 = 10 * size;
        super.setUserTanksCoordinate(new int[][]{{x1, y1}, {x2, y2}});
    }
}
