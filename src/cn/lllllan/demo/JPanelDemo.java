package cn.lllllan.demo;

import cn.lllllan.barrier.BarrierImpl;
import cn.lllllan.tank.TankPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class JPanelDemo extends JPanel implements Runnable, KeyListener {

    private static TankPlus tankPlus;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        tankPlus = new TankPlus(1, 0, 400, 100);

        JPanelDemo jPanelDemo = new JPanelDemo();
        new Thread(jPanelDemo).start();

        jFrame.add(jPanelDemo);
        jFrame.setSize(800, 600);
        jFrame.addKeyListener(jPanelDemo);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口即结束程序
        jFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                TankPlus tankPlus = new TankPlus(i, j, j * 100, i * 100);
                tankPlus.paint(g, this);
            }
        }

        for (int i = 0; i < 4; ++i) {
            BarrierImpl barrier = new BarrierImpl(i * 100, 300, i);
            barrier.paint(g, this);
        }

        System.out.println(tankPlus.getX() + " " + tankPlus.getY());
        tankPlus.paint(g, this);

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            tankPlus.moveUp(10);
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
                e.printStackTrace();
            }

            this.repaint();
        }
    }
}
