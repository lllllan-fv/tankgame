package cn.lllllan.stage.level;

import cn.lllllan.cube.tank.UserTank;
import cn.lllllan.data.Data1;
import cn.lllllan.stage.LevelStage;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Level1 extends LevelStage {

    public Level1() {
        super(new Data1());
    }

    public void reInit(UserTank[] users) {
        super.setData(new Data1());
        super.reInit(users);
    }
}
