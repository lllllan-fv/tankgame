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

@SuppressWarnings({"all"})

public class Data2 extends Data {
    public Data2() {
        super();

        int size = Cube.getSIZE();

        // enemy
        int[] enemyX = new int[]{3, 19, 9, 13, 9, 13, 3, 19};
        int[] enemyY = new int[]{3, 3, 5, 5, 9, 9, 11, 11};
        for (int i = 0; i < enemyX.length; ++i) {
            int x = enemyX[i] * size, y = enemyY[i] * size;
            super.addEnemyTank(new EnemyTank(0, x, y));
        }

        // Steel
        int[] steelX = new int[]{2, 20, 11, 7, 15, 11, 2, 20};
        int[] steelY = new int[]{2, 2, 3, 7, 7, 11, 12, 12};
        for (int i = 0; i < steelX.length; ++i) {
            int x = steelX[i] * size, y = steelY[i] * size;
            super.addBarrier(new StellBarrier(x, y));
        }

        // Wall
        int[] wallX = new int[]{10, 12, 10, 12, 8, 9, 10, 12, 13, 14, 8, 9, 10, 12, 13, 14, 10, 12, 10, 12};
        int[] wallY = new int[]{4, 4, 5, 5, 6, 6, 6, 6, 6, 6, 8, 8, 8, 8, 8, 8, 9, 9, 10, 10};
        for (int i = 0; i < wallX.length; ++i) {
            int x = wallX[i] * size, y = wallY[i] * size;
            super.addBarrier(new WallBarrier(x, y));
        }

        // Grass
        int[] grassX = new int[]{2, 20, 1, 3, 19, 21, 2, 20, 2, 20, 1, 3, 19, 21, 2, 20};
        int[] grassY = new int[]{1, 1, 2, 2, 2, 2, 3, 3, 11, 11, 12, 12, 12, 12, 13, 13};
        for (int i = 0; i < grassX.length; ++i) {
            int x = grassX[i] * size, y = grassY[i] * size;
            super.addBarrier(new GrassBarrier(x, y));
        }

        // Water
        int[] waterX = new int[]{11, 11, 11, 8, 9, 10, 11, 12, 13, 14, 11, 11, 11};
        int[] waterY = new int[]{4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10};
        for (int i = 0; i < waterX.length; ++i) {
            int x = waterX[i] * size, y = waterY[i] * size;
            super.addBarrier(new WaterBarrier(x, y));
        }

        int x1 = 1 * size, y1 = 13 * size;
        int x2 = 21 * size, y2 = 13 * size;
        super.setUserTanksCoordinate(new int[][]{{x1, y1}, {x2, y2}});
    }
}
