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

        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("EndStage", 100, 100);

        URL url = BarrierImpl.class.getResource("/img/tank/cover.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 250, 300, 900, 436, this);
    }
}
