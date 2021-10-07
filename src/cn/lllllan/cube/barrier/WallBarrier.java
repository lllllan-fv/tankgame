package cn.lllllan.cube.barrier;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class WallBarrier extends BarrierImpl implements Barrier {
 
    public WallBarrier(int x, int y) {
        super(x, y, 0);
    }

}
