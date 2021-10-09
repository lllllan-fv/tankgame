package cn.lllllan.stage;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.net.URL;

public class WinStage extends Stage implements Runnable {

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("WinStage InterruptedException");
                break;
            }

            this.repaint();
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString("WinStage", 100, 100);

        URL url = BarrierImpl.class.getResource("/img/tank/cover.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 250, 300, 900, 436, this);
    }
}
