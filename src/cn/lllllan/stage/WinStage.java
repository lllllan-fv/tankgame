package cn.lllllan.stage;

import cn.lllllan.cube.barrier.BarrierImpl;

import java.awt.*;
import java.net.URL;

public class WinStage extends Stage implements Runnable {

    private int score1;
    private int score2;

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

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

        String str;
        if (score1 > score2) {
            str = "玩家一获胜";
        } else if (score1 == score2) {
            str = "双方平局";
        } else {
            str = "玩家二获胜";
        }

        g.setFont(new Font("隶书", Font.BOLD, 50));
        g.drawString(str, 100, 100);

        URL url = BarrierImpl.class.getResource("/img/icon/win.png");
        Image img = Toolkit.getDefaultToolkit().getImage(url);
        g.drawImage(img, -260, 0, 1900, 800, this);
    }
}
