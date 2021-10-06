package cn.lllllan.barrier;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class StellBarrier extends BarrierImpl implements Barrier {
    /**
     * Desc: 有参构造
     *
     * @param x 起始位置左上角 x 坐标
     * @param y 起始位置左上角 y 坐标
     * @author lllllan
     * @date 2021/10/5 16:32
     */
    public StellBarrier(int x, int y) {
        super(x, y, 3);
    }
}
