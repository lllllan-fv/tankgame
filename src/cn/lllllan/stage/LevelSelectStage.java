package cn.lllllan.stage;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class LevelSelectStage extends Stage implements Runnable {

    private int maxLevel;
    private int level;

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int x = 150, y = 200;
        g.setFont(new Font("隶书", Font.BOLD, 80));
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 4; ++j) {
                g.setColor((i * 4 + j == level) ? Color.yellow : Color.white);
                if (i * 4 + j > maxLevel) g.setColor(Color.GRAY);

                g.fillRoundRect(x + j * 300, y + i * 200, 200, 150, 10, 10);

                g.setColor((i * 4 + j == level) ? Color.red : Color.black);
                g.drawString("" + (4 * i + j + 1), x + j * 300 + 80, y + i * 200 + 100);
            }
        }
    }

    public void keyUp() {
        if (level - 4 >= 0) level -= 4;
    }

    public void keyDown() {
        if (level + 4 <= maxLevel) level += 4;
    }

    public void keyLeft() {
        if (level == 4) {
            level = 3;
        } else if (level > 0) level--;
    }

    public void keyRight() {
        if (level == 3 && maxLevel > 3) level = 4;
        else if (level < maxLevel) level++;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                keyUp();
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                keyDown();
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                keyLeft();
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                keyRight();
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
                System.out.println("level select InterruptedException");
                break;
            }

            this.repaint();
        }
    }
}
