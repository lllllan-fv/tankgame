package cn.lllllan.stage;

import cn.lllllan.cube.Cube;
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

public class TankSelectStage extends Stage implements Runnable {

    private int userNumber = 1;
    private int[] index;

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
        index = new int[userNumber];

        for (int i = 0; i < index.length; ++i) index[i] = i;
    }

    public int[] getIndex() {
        return index;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < 4; ++i) {
            int x = 100 + i * 300;
            int y = 250;

            g.setColor(Color.black);
            g.fillRoundRect(x, y, 250, 250, 20, 20);
            for (int j = 0; j < index.length; ++j) {
                if (i == index[j]) {
                    g.setColor(j == 0 ? Color.red : Color.yellow);
                }
            }
            g.drawRoundRect(x, y, 250, 250, 20, 20);


            for (int j = 0; j < 4; ++j) {
                int _x = x + 50 + (j % 2) * 100;
                int _y = y + 50 + (j / 2) * 100;

                String imgurl = "/img/tank/user/" + i + "/" + j + ".png";
                URL url = BarrierImpl.class.getResource(imgurl);
                Image img = Toolkit.getDefaultToolkit().getImage(url);
                g.drawImage(img, _x, _y, Cube.getSIZE(), Cube.getSIZE(), this);
            }
        }

    }

    public void keyLeft(int i) {
        if (i >= index.length) return;
        if ((i ^ 1) < index.length && index[i ^ 1] == index[i] - 1) {
            if (index[i] > 1) index[i] -= 2;
        } else {
            index[i] = Math.max(index[i] - 1, 0);
        }
    }

    public void keyRight(int i) {
        if (i >= index.length) return;
        if ((i ^ 1) < index.length && index[i ^ 1] == index[i] + 1) {
            if (index[i] < 2) index[i] += 2;
        } else {
            index[i] = Math.min(index[i] + 1, 3);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_A:
                keyLeft(0);
                break;
            case KeyEvent.VK_LEFT:
                keyLeft(1);
                break;
            case KeyEvent.VK_D:
                keyRight(0);
                break;
            case KeyEvent.VK_RIGHT:
                keyRight(1);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("tank select InterruptedException");
                break;
            }

            this.repaint();
        }

    }
}
