package cn.lllllan.stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class Stage extends JPanel implements Runnable {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.DARK_GRAY);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                break;
            }

            this.repaint();
        }
    }
}
