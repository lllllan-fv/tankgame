package cn.lllllan.stage;

import cn.lllllan.barrier.*;
import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.tank.EnemyTank;
import cn.lllllan.tank.UserTank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class LevelStage extends Stage implements Runnable {

    private static final int WIDTH = 1100;
    private static final int HEIGHT = 700;

    private static final int[][] COORDINATES = new int[][]{
            {1180, 550}, {1180, 600}, {1180, 650}, {1180, 700},
    };

    private static final String[] TIPS = new String[]{
            "Space 暂停", "F5 重新开始", "F6 重选关卡", "F7 重选模式"
    };

    private Vector<BarrierImpl> barriers;
    private Vector<EnemyTank> enemyTanks;
    private Vector<BulletImpl> bullets;

    private UserTank user1;
    private UserTank user2;

    public LevelStage() {
        barriers = new Vector<>();
        enemyTanks = new Vector<>();
        bullets = new Vector<>();

        // 边框
        int add = BarrierImpl.getSIZE();
        for (int col = 0; col <= HEIGHT; col += add) {
            barriers.add(new StellBarrier(0, col));
            barriers.add(new StellBarrier(WIDTH, col));
        }
        for (int row = 0; row <= WIDTH; row += add) {
            barriers.add(new StellBarrier(row, 0));
            barriers.add(new StellBarrier(row, HEIGHT));
            barriers.add(new StellBarrier(row, HEIGHT + add));
        }

        for (int col = 3 * add; col <= 10 * add; col += 2 * add) {
            barriers.add(new WallBarrier(3 * add, col));
            barriers.add(new WaterBarrier(5 * add, col));
            barriers.add(new GrassBarrier(7 * add, col));
        }

    }

    public void setUsers(UserTank user1, UserTank user2) {
        this.user1 = user1;
        this.user2 = user2;

        if (user1 != null) user1.setCoordination(100, 100);
        if (user2 != null) user2.setCoordination(200, 100);
    }

    public void usersMove() {
        if (user1 != null && user1.isMoving()) {
            int direct = user1.getDirect();
        }

        if (user2 != null && user2.isMoving()) {
            int direct = user2.getDirect();
        }
    }

    public void paintSplitLine(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1150, 0, 10, 800);
    }

    public void paintTips(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("隶书", Font.BOLD, 30));
        for (int i = 0; i < TIPS.length; ++i) {
            int x = COORDINATES[i][0];
            int y = COORDINATES[i][1];
            g.drawString(TIPS[i], COORDINATES[i][0], COORDINATES[i][1]);
        }
    }

    public void paintUsers(Graphics g) {
        if (user1 != null) user1.paint(g, this);
        if (user2 != null) user2.paint(g, this);
    }

    public void paintEnemyTanks(Graphics g) {
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.paint(g, this);
        }
    }

    public void paintBullets(Graphics g) {
    }

    public void paintBarriers(Graphics g) {
        for (BarrierImpl barrier : barriers) {
            barrier.paint(g, this);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        paintSplitLine(g);
        paintTips(g);

        paintUsers(g);
        paintEnemyTanks(g);
        paintBarriers(g);
        paintBullets(g);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("level InterruptedException");
                break;
            }

            usersMove();
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                user1.moveUp(user1.getSPEED());
                break;
            case KeyEvent.VK_S:
                user1.moveDown(user1.getSPEED());
                break;
            case KeyEvent.VK_A:
                user1.moveLeft(user1.getSPEED());
                break;
            case KeyEvent.VK_D:
                user1.moveRight(user1.getSPEED());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode() + "releas");
    }
}
