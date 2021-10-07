package cn.lllllan.cube.barrier;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class WaterBarrier extends BarrierImpl implements Barrier {
   
    public WaterBarrier(int x, int y) {
        super(x, y, 2);
    }
}
