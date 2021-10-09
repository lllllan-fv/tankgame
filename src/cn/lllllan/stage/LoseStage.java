package cn.lllllan.stage;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.net.URL;

public class LoseStage extends Stage implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("LoseStage InterruptedException");
                break;
            }

            this.repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
 
        URL url = BarrierImpl.class.getResource("/img/over.gif");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 300, 200, 800, 450, this);
    }
}
