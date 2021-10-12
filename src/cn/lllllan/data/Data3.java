package cn.lllllan.data;

import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.GrassBarrier;
import cn.lllllan.cube.barrier.WallBarrier;
import cn.lllllan.cube.barrier.WaterBarrier;
import cn.lllllan.cube.tank.EnemyTank;

public class Data3 extends Data {
    public Data3() {
        super();

        int size = Cube.getSIZE();

        // enemy
        int[] enemyX = new int[]{1, 21, 4, 18, 4, 18, 7, 15, 7, 15};
        int[] enemyY = new int[]{1, 1, 2, 2, 9, 9, 4, 4, 8, 8};
        for (int i = 0; i < enemyX.length; ++i) {
            int x = enemyX[i] * size;
            int y = enemyY[i] * size;
            super.addEnemyTank(new EnemyTank(i > 5 ? 1 : 0, x, y));
        }

        // Wall
        int[] wallX = new int[]{1, 2, 20, 21, 9, 13, 9, 13, 10, 11, 12, 3, 4, 18, 19};
        int[] wally = new int[]{4, 4, 4, 4, 5, 5, 6, 6, 8, 8, 8, 11, 11, 11, 11};
        for (int i = 0; i < wallX.length; ++i) {
            int x = wallX[i] * size;
            int y = wally[i] * size;
            super.addBarrier(new WallBarrier(x, y));
        }

        // Grass
        for (int i = 2; i <= 9; ++i) {
            int x = 5 * size, _x = 17 * size, y = i * size;
            super.addBarrier(new GrassBarrier(x, y));
            super.addBarrier(new GrassBarrier(_x, y));
        }
        for (int i = 6; i <= 16; ++i) {
            int x = i * size, y = 2 * size;
            super.addBarrier(new GrassBarrier(x, y));
        }
        int[] grassX = new int[]{8, 14, 8, 14, 9, 10, 12, 13};
        int[] grassY = new int[]{10, 10, 11, 11, 12, 12, 12, 12};
        for (int i = 0; i < grassX.length; ++i) {
            int x = grassX[i] * size, y = grassY[i] * size;
            super.addBarrier(new GrassBarrier(x, y));
        }

        // Water
        for (int i = 3; i <= 9; ++i) {
            int x = 6 * size, _x = 16 * size, y = i * size;
            super.addBarrier(new WaterBarrier(x, y));
            super.addBarrier(new WaterBarrier(_x, y));
        }
        for (int i = 6; i <= 15; ++i) {
            int x = i * size, y = 3 * size;
            super.addBarrier(new WaterBarrier(x, y));
        }
        int[] waterX = new int[]{7, 8, 14, 15};
        int[] waterY = new int[]{9, 9, 9, 9};
        for (int i = 0; i < waterX.length; ++i) {
            int x = waterX[i] * size, y = waterY[i] * size;
            super.addBarrier(new WaterBarrier(x, y));
        }

        int x1 = 1 * size, y1 = 13 * size;
        int x2 = 21 * size, y2 = 13 * size;
        super.setUserTanksCoordinate(new int[][]{{x1, y1}, {x2, y2}});
    }
}
