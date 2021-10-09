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

    private static final int MAX_TOTAL_NUMBER = 8;

    private int activeNumber;
    private int totalNumber;

    private int level;

    public LevelSelectStage(int totalNumber) {
        level = 1;
        this.totalNumber = Math.min(totalNumber, MAX_TOTAL_NUMBER);
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public int getActiveNumber() {
        return activeNumber;
    }

    public void setActiveNumber(int activeNumber) {
        this.activeNumber = Math.min(activeNumber, totalNumber);
    }

    public void setMoreActiveNumber(int activeNumber) {
        setActiveNumber(Math.max(activeNumber, this.activeNumber));
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
                if (i * 4 + j + 1 > totalNumber) break;
                g.setColor((i * 4 + j + 1 == level) ? Color.yellow : Color.white);
                if (i * 4 + j + 1 > activeNumber) g.setColor(Color.GRAY);

                g.fillRoundRect(x + j * 300, y + i * 200, 200, 150, 10, 10);

                g.setColor((i * 4 + j + 1 == level) ? Color.red : Color.black);
                g.drawString("" + (4 * i + j + 1), x + j * 300 + 80, y + i * 200 + 100);
            }
        }
    }

    public void keyUp() {
        if (level - 4 >= 0) level -= 4;
    }

    public void keyDown() {
        if (level + 4 <= activeNumber) level += 4;
    }

    public void keyLeft() {
        if (level == 5) level = 4;
        else if (level > 1) level--;
    }

    public void keyRight() {
        if (level == 3 && activeNumber > 3) level = 4;
        else if (level < activeNumber) level++;
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
