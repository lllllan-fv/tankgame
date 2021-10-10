package cn.lllllan.stage;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class InitialStage extends Stage implements Runnable {

    private static final int[][] COORDINATES = new int[][]{
            {50, 100}, {50, 200}
    };

    private static final String[] number = new String[]{
            "单人游戏", "双人游戏"
    };

    private static final int ICON_SIZE = 50;

    private int index = 0;

    public int getIndex() {
        return index;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.setBackground(Color.DARK_GRAY);

        g.setFont(new Font("隶书", Font.BOLD, 50));

        for (int i = 0; i < 2; ++i) {
            g.setColor(i == index ? Color.yellow : Color.red);

            String str = number[i] + ((i == index) ? "√" : "");
            g.drawString(str, COORDINATES[i][0] + ICON_SIZE, COORDINATES[i][1]);
        }

        URL url = BarrierImpl.class.getResource("/img/icon/cover.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, 250, 300, 900, 436, this);
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("initial InterruptedException");
                break;
            }

            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        super.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                index = Math.min(index + 1, 1);
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                index = Math.max(index - 1, 0);
                break;
            case KeyEvent.VK_ENTER:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
    }

}
