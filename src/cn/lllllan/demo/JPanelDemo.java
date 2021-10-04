package cn.lllllan.demo;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.tank.TankImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/
public class JPanelDemo extends JPanel {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();

        JPanelDemo jPanelDemo = new JPanelDemo();

        jFrame.add(jPanelDemo);
        jFrame.setSize(800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口即结束程序
        jFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        TankImpl tank = new TankImpl(0, 0, 3, 0);
        tank.paint(g);

        BulletImpl bullet = new BulletImpl(100, 100, 0);
        bullet.paint(g);
    }
}
