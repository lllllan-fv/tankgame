package cn.lllllan.data;

import cn.lllllan.cube.barrier.BarrierImpl;
import cn.lllllan.cube.tank.EnemyTank;
import cn.lllllan.cube.tank.UserTank;
import cn.lllllan.cube.tank.UserTank1;
import cn.lllllan.cube.tank.UserTank2;

import java.util.Vector;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Data {

    Vector<BarrierImpl> barriers;
    Vector<EnemyTank> enemyTanks;
    UserTank[] userTanks;

    public Data() {
        barriers = new Vector<>();
        enemyTanks = new Vector<>();

        userTanks = new UserTank[2];
        userTanks[0] = new UserTank1(0, 100, 100);
        userTanks[1] = new UserTank2(1, 200, 100);
    }

    public Vector<BarrierImpl> getBarriers() {
        return barriers;
    }

    public void setBarriers(Vector<BarrierImpl> barriers) {
        this.barriers = barriers;
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public UserTank[] getUserTanks() {
        return userTanks;
    }

    public void setUserTanks(UserTank[] userTanks) {
        this.userTanks = userTanks;
    }

    public void addEnemyTank(EnemyTank enemyTank) {
        if (enemyTank != null) enemyTanks.add(enemyTank);
    }

    public void addBarrier(BarrierImpl barrier) {
        if (enemyTanks != null) barriers.add(barrier);
    }

    public void setUserTanksCoordinate(int[][] coordinate) {
        for (int i = 0; i < 2; ++i) {
            userTanks[i].setCoordinate(coordinate[i][0], coordinate[i][1]);
        }
    }

    public int[][] getUserTanksCoordinate() {
        int[][] ans = new int[2][2];
        for (int i = 0; i < 2; ++i) {
            ans[i][0] = userTanks[i].getX();
            ans[i][1] = userTanks[i].getY();
        }
        return ans;
    }

}
