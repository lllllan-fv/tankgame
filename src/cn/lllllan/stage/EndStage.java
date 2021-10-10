package cn.lllllan.stage;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.net.URL;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class EndStage extends Stage implements Runnable {


    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("EndStage InterruptedException");
                break;
            }

            this.repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.red);
        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("敬请期待，坦克大战二", 100, 100);
        g.drawString("我赌你的枪里，没有子弹", 400, 200);

        URL url = BarrierImpl.class.getResource("/img/icon/end.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 500, 300, 534, 300, this);
    }
}
