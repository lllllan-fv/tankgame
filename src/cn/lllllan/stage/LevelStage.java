package cn.lllllan.stage;

import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.*;
import cn.lllllan.cube.tank.EnemyTank;
import cn.lllllan.cube.tank.UserTank;

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

    public void setUserTanks(UserTank user1, UserTank user2) {
        this.user1 = user1;
        this.user2 = user2;

        if (user1 != null) user1.setCoordination(100, 100);
        if (user2 != null) user2.setCoordination(200, 100);
    }

    public int calculateDistanceOfUserTankToOtherCubes(UserTank user, Cube cube) {
        int ans = Integer.MAX_VALUE;

        if (cube.isCanTankPass()) return ans;

        int direct = user.getDirect();

        if (direct == 0 && user.getY() >= cube.getY() + cube.getHeight()) {

            if (!(cube.getX() >= user.getX() + user.getWidth() || cube.getX() + cube.getWidth() <= user.getX())) {
                ans = Math.min(ans, user.getY() - (cube.getY() + cube.getHeight()));
            }

        } else if (direct == 1 && cube.getX() >= user.getX() + user.getWidth()) {

            if (!(cube.getY() >= user.getY() + user.getHeight() || cube.getY() + cube.getHeight() <= user.getY())) {
                ans = Math.min(ans, cube.getX() - (user.getX() + user.getWidth()));
            }

        } else if (direct == 2 && cube.getY() >= user.getY() + user.getHeight()) {

            if (!(cube.getX() >= user.getX() + user.getWidth() || cube.getX() + cube.getWidth() <= user.getX())) {
                ans = Math.min(ans, cube.getY() - (user.getY() + user.getHeight()));
            }

        } else if (direct == 3 && user.getX() >= cube.getX() + cube.getWidth()) {

            if (!(cube.getY() >= user.getY() + user.getHeight() || cube.getY() + cube.getHeight() <= user.getY())) {
                ans = Math.min(ans, user.getX() - (cube.getX() + cube.getWidth()));
            }

        }

        if (ans == 0) {
            System.out.println("user: " + user.getX() + " " + user.getY());
            System.out.println("cube: " + cube.getX() + " " + cube.getY());
        }

        return ans;
    }

    public void userTankMove(UserTank user, int direct) {
        if (user == null || direct == -1) return;

        if (user.getDirect() != direct) {

            user.setDirect(direct);

        } else {

            int speed = user.getSPEED();

            if (user1 != null && user != user1) {
                speed = Math.min(speed, calculateDistanceOfUserTankToOtherCubes(user, user1));
            }

            if (user2 != null && user != user2) {
                speed = Math.min(speed, calculateDistanceOfUserTankToOtherCubes(user, user2));
            }

            for (EnemyTank enemyTank : enemyTanks) {
                speed = Math.min(speed, calculateDistanceOfUserTankToOtherCubes(user, enemyTank));
            }

            for (BarrierImpl barrier : barriers) {
                speed = Math.min(speed, calculateDistanceOfUserTankToOtherCubes(user, barrier));
            }

            if (direct == 0) {

                speed = Math.min(speed, user.getY() - Cube.getSIZE());

            } else if (direct == 1) {

                speed = Math.min(speed, WIDTH - (user.getX() + user.getWidth()));

            } else if (direct == 2) {

                speed = Math.min(speed, HEIGHT - -(user.getY() + user.getHeight()));

            } else {

                speed = Math.min(speed, user.getX() - Cube.getSIZE());

            }

            System.out.println("speed = " + speed);

            user.setSpeed(speed);

            switch (direct) {
                case 0:
                    user.moveUp(speed);
                    break;
                case 1:
                    user.moveRight(speed);
                    break;
                case 2:
                    user.moveDown(speed);
                    break;
                case 3:
                    user.moveLeft(speed);
                    break;
            }
        }
    }

    public void user1Move(KeyEvent e) {
        if (user1 == null) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                userTankMove(user1, 0);
                break;
            case KeyEvent.VK_D:
                userTankMove(user1, 1);
                break;
            case KeyEvent.VK_S:
                userTankMove(user1, 2);
                break;
            case KeyEvent.VK_A:
                userTankMove(user1, 3);
                break;
            default:
                break;
        }

    }

    public void user2Move(KeyEvent e) {
        if (user2 == null) return;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                userTankMove(user2, 0);
                break;
            case KeyEvent.VK_RIGHT:
                userTankMove(user2, 1);
                break;
            case KeyEvent.VK_DOWN:
                userTankMove(user2, 2);
                break;
            case KeyEvent.VK_LEFT:
                userTankMove(user2, 3);
                break;
            default:
                break;
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

            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        user1Move(e);
        user2Move(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode() + "releas");
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
}
