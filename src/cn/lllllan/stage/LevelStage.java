package cn.lllllan.stage;

import cn.lllllan.bullet.Bullet;
import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.*;
import cn.lllllan.cube.tank.EnemyTank;
import cn.lllllan.cube.tank.TankImpl;
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

    private static final int TIPS_X = 1180;
    private static final int TIPS_Y = 500;
    private static final int TIPS_HEIGHT = 50;
    private static final String[] TIPS = new String[]{
            "Space 暂停", "F5 重新开始", "F6 重选人数", "F7 重选样式", "F8 重选关卡"
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

        for (int i = 0; i < 4; ++i) {
            EnemyTank enemyTank = new EnemyTank(i, 400 + i * 100, 100);
            enemyTank.setDirect(i);
            enemyTanks.add(enemyTank);
            bullets.add(enemyTank.shoot());

            UserTank userTank = new UserTank(i, 400 + i * 100, 200);
            userTank.setDirect(i);
            bullets.add(userTank.shoot());
        }


    }

    public void setUserTanks(UserTank user1, UserTank user2) {
        this.user1 = user1;
        this.user2 = user2;

        if (user1 != null) user1.setCoordination(100, 100);
        if (user2 != null) user2.setCoordination(200, 100);
    }

    public int calculateDistanceOfTankToOtherCubes(TankImpl tank, Cube cube) {
        int ans = Integer.MAX_VALUE;

        if (cube == null || cube.isCanTankPass() || tank == cube) return ans;

        int direct = tank.getDirect();

        if (direct == 0 && tank.getY() >= cube.getY() + cube.getHeight()) {

            if (!(cube.getX() >= tank.getX() + tank.getWidth() || cube.getX() + cube.getWidth() <= tank.getX())) {
                ans = Math.min(ans, tank.getY() - (cube.getY() + cube.getHeight()));
            }

        } else if (direct == 1 && cube.getX() >= tank.getX() + tank.getWidth()) {

            if (!(cube.getY() >= tank.getY() + tank.getHeight() || cube.getY() + cube.getHeight() <= tank.getY())) {
                ans = Math.min(ans, cube.getX() - (tank.getX() + tank.getWidth()));
            }

        } else if (direct == 2 && cube.getY() >= tank.getY() + tank.getHeight()) {

            if (!(cube.getX() >= tank.getX() + tank.getWidth() || cube.getX() + cube.getWidth() <= tank.getX())) {
                ans = Math.min(ans, cube.getY() - (tank.getY() + tank.getHeight()));
            }

        } else if (direct == 3 && tank.getX() >= cube.getX() + cube.getWidth()) {

            if (!(cube.getY() >= tank.getY() + tank.getHeight() || cube.getY() + cube.getHeight() <= tank.getY())) {
                ans = Math.min(ans, tank.getX() - (cube.getX() + cube.getWidth()));
            }

        }

        if (ans == 0) {
            System.out.println("tank: " + tank.getX() + " " + tank.getY());
            System.out.println("cube: " + cube.getX() + " " + cube.getY());
        }

        return ans;
    }

    public int longestDistanceOfTankCanMove(TankImpl tank) {
        int direct = tank.getDirect();
        int distance = Integer.MAX_VALUE;

        distance = Math.min(distance, calculateDistanceOfTankToOtherCubes(tank, user1));
        distance = Math.min(distance, calculateDistanceOfTankToOtherCubes(tank, user2));

        for (EnemyTank enemyTank : enemyTanks) {
            distance = Math.min(distance, calculateDistanceOfTankToOtherCubes(tank, enemyTank));
        }

        for (BarrierImpl barrier : barriers) {
            distance = Math.min(distance, calculateDistanceOfTankToOtherCubes(tank, barrier));
        }

        if (direct == 0) {

            distance = Math.min(distance, tank.getY() - Cube.getSIZE());

        } else if (direct == 1) {

            distance = Math.min(distance, WIDTH - (tank.getX() + tank.getWidth()));

        } else if (direct == 2) {

            distance = Math.min(distance, HEIGHT - -(tank.getY() + tank.getHeight()));

        } else {

            distance = Math.min(distance, tank.getX() - Cube.getSIZE());

        }

        return distance;
    }

    public void userTankMove(UserTank user) {
        if (user == null || user.getMoving() == 0) return;

        if (user.getMoving() == 1) {
            user.keepMoving();
            return;
        }

        int direct = user.getDirect();
        int speed = Math.min(user.getSPEED(), longestDistanceOfTankCanMove(user));

        System.out.println("speed = " + speed);

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


    public void startMoving(UserTank user, int direct) {
        if (user.getDirect() != direct) {
            user.setDirect(direct);
            user.changeDirect();
        } else {
            user.startMoving();
        }
    }

    public void enemyTankMove(EnemyTank tank) {
    }

    public void enemyTanksMove() {
        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank == null) enemyTanks.remove(enemyTank);
            else enemyTankMove(enemyTank);
        }
    }

    public Cube isBulletHitCube(BulletImpl bullet) {

        return null;
    }

    public void destroyBulletsAndTanks() {
        for (BulletImpl bullet : bullets) {
//            Cube cube =
        }
    }

    public void userTankShoot(UserTank user) {
        BulletImpl shoot = user.shoot();
        if (shoot != null) bullets.add(shoot);
    }

    public void bulletMove(BulletImpl bullet) {
        bullet.move();
    }

    public void bulletsMove() {
        for (BulletImpl bullet : bullets) {
            if (bullet == null) bullets.remove(bullet);
            else bulletMove(bullet);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private boolean[][] moveEvent = new boolean[2][5];

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode() + "press");

        int direct;

        if (user1 != null && (direct = user1.getDirect(e)) != -1) {
            moveEvent[0][direct] = true;
            startMoving(user1, direct);
        }

        if (user2 != null && (direct = user2.getDirect(e)) != -1) {
            moveEvent[1][direct] = true;
            startMoving(user2, direct);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode() + "releas");

        int direct;

        if (user1 != null) {
            if ((direct = user1.getDirect(e)) != -1) {
                moveEvent[0][direct] = false;
                boolean moveFlag = false;
                for (int i = 0; i < 4; ++i) moveFlag |= moveEvent[0][i];
                if (!moveFlag) user1.stopMoving();
            } else if (e.getKeyCode() == KeyEvent.VK_J) {
                userTankShoot(user1);
            }
        }

        if (user2 != null) {
            if ((direct = user2.getDirect(e)) != -1) {
                moveEvent[1][direct] = false;
                boolean moveFlag = false;
                for (int i = 0; i < 4; ++i) moveFlag |= moveEvent[1][i];
                if (!moveFlag) user2.stopMoving();
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                userTankShoot(user2);
            }
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("level InterruptedException");
                break;
            }

            enemyTanksMove();
            userTankMove(user1);
            userTankMove(user2);
            bulletsMove();

//            System.out.println(Thread.currentThread().getName());
            this.repaint();
        }
    }

    public void paintSplitLine(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1150, 0, 10, 800);
    }

    public void paintTips(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("隶书", Font.BOLD, 30));

        int x = TIPS_X;
        int y = TIPS_Y;
        for (int i = 0; i < TIPS.length; ++i) {
            y += TIPS_HEIGHT;
            g.drawString(TIPS[i], x, y);
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
        for (Bullet bullet : bullets) {
            bullet.paint(g, this);
        }
    }

    public void paintBarriers(Graphics g) {
        for (BarrierImpl barrier : barriers) {
            barrier.paint(g, this);
        }
    }
}
