package cn.lllllan.stage;

import cn.lllllan.bullet.Bullet;
import cn.lllllan.bullet.BulletImpl;
import cn.lllllan.cube.Cube;
import cn.lllllan.cube.barrier.BarrierImpl;
import cn.lllllan.cube.barrier.StellBarrier;
import cn.lllllan.cube.tank.EnemyTank;
import cn.lllllan.cube.tank.TankImpl;
import cn.lllllan.cube.tank.UserTank;
import cn.lllllan.data.Data;
import cn.lllllan.data.Data1;
import cn.lllllan.data.Data2;
import cn.lllllan.gif.BlastGif;
import cn.lllllan.gif.Gif;
import cn.lllllan.gif.HitGif;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class LevelStage extends Stage implements Runnable {

    private static final int MAX_LEVEL = 8;

    private static final int WIDTH = 1100;
    private static final int HEIGHT = 700;

    private static final int INFO_X = 1180;
    private static final int INFO_Y = 100;
    private static final int INFO_HEIGHT = 50;

    private static final int TIPS_X = 1180;
    private static final int TIPS_Y = 500;
    private static final int TIPS_HEIGHT = 50;
    private static final String[] TIPS = new String[]{
            "Space ", "F5 重新开始", "F6 重选人数", "F7 重选样式", "F8 重选关卡"
    };

    private Vector<BarrierImpl> barriers;
    private Vector<EnemyTank> enemyTanks;
    private Vector<BulletImpl> bullets;
    private Vector<Gif> gifs;

    private UserTank user1;
    private UserTank user2;

    private int user1Kill;
    private int user2Kill;

    private Data data;
    private int level;

    public LevelStage(int level) {
        barriers = new Vector<>();
        enemyTanks = new Vector<>();
        bullets = new Vector<>();
        gifs = new Vector<>();
        this.level = level % MAX_LEVEL;

        // 不明原因：第一次爆炸不会绘制，因此先生成一个显示界面之外的爆炸
        gifs.add(new BlastGif(-100, -100));

        init();
    }

    public void init() {
        barriers.clear();
        enemyTanks.clear();
        bullets.clear();

        switch (level) {
            case 0:
                data = new Data1();
                break;
            case 1:
                data = new Data2();
                break;
            default:
        }

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

        Vector<BarrierImpl> dataBarriers = data.getBarriers();
        for (BarrierImpl barrier : dataBarriers) {
            barriers.add(barrier);
        }

        Vector<EnemyTank> dataEnemyTanks = data.getEnemyTanks();
        for (EnemyTank enemyTank : dataEnemyTanks) {
            enemyTanks.add(enemyTank);
        }

        int[][] userTanksCoordinate = data.getUserTanksCoordinate();
        if (user1 != null) {
            user1.setCoordinate(userTanksCoordinate[0][0], userTanksCoordinate[0][1]);
        }
        if (user2 != null) {
            user2.setCoordinate(userTanksCoordinate[1][0], userTanksCoordinate[1][1]);
        }
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void reInit(UserTank[] users) {
        setUserTanks(users);
        init();
    }

    public boolean isOver() {
        return (enemyTanks.size() == 0) && (gifs.size() == 0);
    }

    public boolean isLive() {
        return (user1 != null || user2 != null) || (gifs.size() != 0);
    }

    public int getUser1Kill() {
        return user1Kill;
    }

    public int getUser2Kill() {
        return user2Kill;
    }

    public void addBarrier(BarrierImpl barrier) {
        if (barrier != null) barriers.add(barrier);
    }

    public void addEnemyTank(EnemyTank enemyTank) {
        if (enemyTank != null) enemyTanks.add(enemyTank);
    }

    public void setUserTanks(UserTank[] users) {
        user1 = users[0];
        user2 = users[1];
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

    public boolean tankImplMove(TankImpl tank) {
        int direct = tank.getDirect();
        int speed = Math.min(tank.getSPEED(), longestDistanceOfTankCanMove(tank));

        switch (direct) {
            case 0:
                tank.moveUp(speed);
                break;
            case 1:
                tank.moveRight(speed);
                break;
            case 2:
                tank.moveDown(speed);
                break;
            case 3:
                tank.moveLeft(speed);
                break;
        }

        return speed > 0;
    }

    public void userTankMove(UserTank userTank) {
        if (userTank == null || userTank.getMoving() == 0) return;

        if (userTank.getMoving() == 1) {
            userTank.keepMoving();
            return;
        }

        tankImplMove(userTank);
    }

    public void userTanksMove() {
        if (user1 != null) userTankMove(user1);
        if (user2 != null) userTankMove(user2);
    }

    public void startMoving(UserTank userTank, int direct) {
        if (userTank.getDirect() != direct) {
            userTank.setDirect(direct);
            userTank.turning();
        } else {
            userTank.startMoving();
        }
    }

    public void enemyTankMove(EnemyTank enemyTank) {
        if (enemyTank.keepMoving()) {
            if (!enemyTank.turning()) {
                if (!tankImplMove(enemyTank)) {
                    enemyTank.forcedTurn();
                }
            }
        }
    }

    public int[] getDirectionOfUserTank(EnemyTank enemyTank, UserTank userTank) {
        int[] ans = new int[4];
        if (enemyTank == null || userTank == null) return ans;

        if (userTank.getX() + userTank.getWidth() <= enemyTank.getX()) ans[3] = 1;
        else if (enemyTank.getX() + enemyTank.getWidth() <= userTank.getX()) ans[1] = 1;

        if (userTank.getY() + userTank.getHeight() <= enemyTank.getY()) ans[0] = 1;
        else if (enemyTank.getY() + enemyTank.getHeight() <= userTank.getY()) ans[2] = 1;

        return ans;
    }

    public boolean isUserTankInGrass(UserTank userTank) {
        boolean leftTop = false;
        boolean leftBottom = false;
        boolean rightTop = false;
        boolean rightBottom = false;

        for (BarrierImpl barrier : barriers) {
            if (barrier.isCanTankPass()) {

                if (barrier.getX() + barrier.getWidth() / 2 == userTank.getX()
                        || barrier.getX() == userTank.getX()) {

                    if (barrier.getY() + barrier.getHeight() / 2 == userTank.getY()) {
                        leftTop = true;
                    }

                    if (barrier.getY() == userTank.getY()) {
                        leftTop = leftBottom = true;
                    }

                    if (userTank.getY() + userTank.getHeight() / 2 == barrier.getY()) {
                        leftBottom = true;
                    }
                }

                if (barrier.getX() == userTank.getX()
                        || userTank.getX() + userTank.getWidth() / 2 == barrier.getX()) {

                    if (barrier.getY() + barrier.getHeight() / 2 == userTank.getY()) {
                        rightTop = true;
                    }

                    if (barrier.getY() == userTank.getY()) {
                        rightTop = rightBottom = true;
                    }

                    if (userTank.getY() + userTank.getHeight() / 2 == barrier.getY()) {
                        rightBottom = true;
                    }
                }

            }
        }

        return leftTop && leftBottom && rightTop && rightBottom;
    }

    public int[] getDirectionOfUserTanks(EnemyTank enemyTank) {
        int[] ans = new int[4];

        if (enemyTank == null) return ans;

        if (user1 != null && (!isUserTankInGrass(user1) || user1.getBulletsNumber() > 0)) {
            int[] tem = getDirectionOfUserTank(enemyTank, user1);
            for (int i = 0; i < ans.length; ++i) ans[i] |= tem[i];
        }

        if (user2 != null && (!isUserTankInGrass(user2) || user2.getBulletsNumber() > 0)) {
            int[] tem = getDirectionOfUserTank(enemyTank, user2);
            for (int i = 0; i < ans.length; ++i) ans[i] |= tem[i];
        }

        return ans;
    }

    public void enemyTankShoot(EnemyTank enemyTank) {
        int[] directions = getDirectionOfUserTanks(enemyTank);
        int direct = enemyTank.getDirect();

        if (directions[direct] > 0) {
            tankShoot(enemyTank);
        }
    }

    public void tankShoot(TankImpl user) {
        if (user == null) return;
        BulletImpl shoot = user.shoot();
        if (shoot != null) bullets.add(shoot);
    }

    public void enemyTanksMoveAndShoot() {
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTankMove(enemyTank);
        }
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTankShoot(enemyTank);
        }
    }

    public void bulletMove(BulletImpl bullet) {
        bullet.move();
    }

    public void bulletsMove() {
        for (BulletImpl bullet : bullets) {
            bulletMove(bullet);
        }
    }

    /**
     * @param bullet
     * @param cube
     * @return <ul>
     * <li>-1 - 可以穿过</li>
     * <li>0 - 不可穿过，但只是擦边</li>
     * <li>1 - 不可穿过，会造成伤害</li>
     * <li>2 - 不可穿过，但不会造成伤害</li>
     * </ul>
     */
    public int isBulletHitCube(BulletImpl bullet, Cube cube) {
        // 草丛、河流等，子弹可以穿过
        if (cube == null || cube.isCanBulletPass()) return -1;

        int direct = bullet.getDirect();

        int x = (bullet.getX() + bullet.getWIDTH()) / bullet.getSPEED() * bullet.getSPEED();
        int y = (bullet.getY() + bullet.getHEIGHT()) / bullet.getSPEED() * bullet.getSPEED();

        if (direct == 0 || direct == 2) {

            if (cube.getY() <= y + bullet.getHEIGHT() && y <= cube.getY() + cube.getHeight()) {

                // 擦边
                if (x == cube.getX() || x == cube.getX() + cube.getWidth()) {
                    // 子弹可以从坦克边上擦过、坦克中间擦过、坦克和墙体之间擦过
                    return cube.isTank() ? -1 : 0;
                }
                // 撞击
                else if (cube.getX() < x && x < cube.getX() + cube.getWidth()) {
                    if (cube.isCanBeBorken()) {

                        if (cube.isTank()) {

                            if (bullet.getBelong() == cube) {
                                return -1;
                            } else if (bullet.isEnemy() && ((TankImpl) cube).isEnemy()) {
                                return 2;
                            } else return 1;

                        } else return 1;

                    } else return 2;
                }

            }

        } else {

            if (cube.getX() <= x + bullet.getWIDTH() && x <= cube.getX() + cube.getWidth()) {

                // 擦边
                if (y == cube.getY() || y == cube.getY() + cube.getHeight()) {
                    return cube.isTank() ? -1 : 0;
                }
                // 撞击
                else if (cube.getY() < y && y < cube.getY() + cube.getHeight()) {
                    if (cube.isCanBeBorken()) {

                        if (cube.isTank()) {

                            if (bullet.getBelong() == cube) {
                                return -1;
                            } else if (bullet.isEnemy() && ((TankImpl) cube).isEnemy()) {
                                return 2;
                            } else return 1;

                        } else return 1;

                    } else return 2;
                }

            }

        }

        return -1;
    }

    public Cube isBulletHitCube(int type, Cube cube) {
        if (type == 2) return new StellBarrier(0, 0);
        if (type == 1) return cube;
        return null;
    }

    public Cube isBulletHitCube(BulletImpl bullet) {
        if (bullet == null) return null;
        // 擦边标记
        boolean flag = false;
        Cube cube;

        if (user1 != null) {
            int type = isBulletHitCube(bullet, user1);
            if ((cube = isBulletHitCube(type, user1)) != null) return cube;
        }
        if (user2 != null) {
            int type = isBulletHitCube(bullet, user2);
            if ((cube = isBulletHitCube(type, user2)) != null) return cube;
        }

        for (BarrierImpl barrier : barriers) {
            int type = isBulletHitCube(bullet, barrier);

            if ((cube = isBulletHitCube(type, barrier)) != null) return cube;
            if (type == 0) {
                if (flag) return new StellBarrier(0, 0);
                flag = true;
            }
        }

        for (EnemyTank enemyTank : enemyTanks) {
            if (bullet.getBelong() == enemyTank) continue;
            int type = isBulletHitCube(bullet, enemyTank);
            if ((cube = isBulletHitCube(type, enemyTank)) != null) return cube;
        }
        return null;
    }

    public void makeBlast(Cube cube) {
        gifs.add(new BlastGif(cube.getX(), cube.getY()));
    }

    public void destroyUserTank(UserTank userTank) {
        if (userTank == null) return;
        userTank.lifeDown();
        if (userTank.getLife() == 0) {
            makeBlast(userTank);
            if (userTank == user1) user1 = null;
            if (userTank == user2) user2 = null;
        }
    }

    public void destroyEnemyTank(EnemyTank enemyTank) {
        enemyTank.lifeDown();
        if (enemyTank.getLife() == 0) {
            makeBlast(enemyTank);
            enemyTanks.remove(enemyTank);
        }
    }

    public void bulletHitEnemyTank(BulletImpl bullet, EnemyTank enemyTank) {
        if (bullet == null || enemyTank == null || bullet.getBelong() == enemyTank) return;

        destroyEnemyTank(enemyTank);

        if (enemyTank.getLife() == 0) {
            TankImpl tank = bullet.getBelong();

            if (tank == user1) user1Kill++;
            else if (tank == user2) user2Kill++;
        }
    }

    public void destroyBarrier(BarrierImpl barrier) {
        barrier.lifeDown();
        if (barrier.getLife() == 0) {
            makeBlast(barrier);
            barriers.remove(barrier);
        }
    }

    public void destroy() {
        Vector<BulletImpl> del = new Vector<>();

        Iterator<BulletImpl> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            BulletImpl bullet = iterator.next();
            Cube cube = isBulletHitCube(bullet);
            if (cube == null) continue;

            if (cube.isTank()) {
                if (((TankImpl) cube).isEnemy()) bulletHitEnemyTank(bullet, (EnemyTank) cube);
                else destroyUserTank((UserTank) cube);
            } else if (((BarrierImpl) cube).isCanBeBorken()) {
                destroyBarrier((BarrierImpl) cube);
            }

            TankImpl tank = bullet.getBelong();
            tank.destroyBullet(bullet);
            del.add(bullet);
        }


        for (BulletImpl bullet : del) {
            bullets.remove(bullet);
            gifs.add(new HitGif(bullet.getX(), bullet.getY()));
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
                tankShoot(user1);
            }
        }

        if (user2 != null) {
            if ((direct = user2.getDirect(e)) != -1) {
                moveEvent[1][direct] = false;
                boolean moveFlag = false;
                for (int i = 0; i < 4; ++i) moveFlag |= moveEvent[1][i];
                if (!moveFlag) user2.stopMoving();
            } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                tankShoot(user2);
            }
        }

    }

    private boolean moveFlag = true;
    private boolean stopFlag = false;

    public void stop() {
        stopFlag ^= true;
        this.repaint();
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

            if (!stopFlag) {

                destroy();
                bulletsMove();

                if (moveFlag) {
                    enemyTanksMoveAndShoot();
                    userTanksMove();
                }

                moveFlag ^= true;

                this.repaint();
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        paintSplitLine(g);
        paintTipsAndInfo(g);

        paintUserTanks(g);
        paintEnemyTanks(g);
        paintBarriers(g);
        paintBullets(g);
        paintGifs(g);
        clearGifs();
    }

    public void paintSplitLine(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(1150, 0, 10, 800);
    }

    public void paintTipsAndInfo(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("隶书", Font.BOLD, 30));

        int x = TIPS_X;
        int y = TIPS_Y;
        for (int i = 0; i < TIPS.length; ++i) {
            g.setColor(Color.WHITE);
            y += TIPS_HEIGHT;
            if (i == 0) {
                String str = "";
                if (stopFlag) {
                    g.setColor(Color.red);
                    str = TIPS[i] + "开始";
                } else {
                    str = TIPS[i] + "暂停";
                }
                g.drawString(str, x, y);
            } else {
                g.drawString(TIPS[i], x, y);
            }
        }

        int user1Life = user1 == null ? 0 : user1.getLife();
        int user2Life = user2 == null ? 0 : user2.getLife();
        g.drawString("User1", INFO_X, INFO_Y);
        g.drawString("  HP: " + user1Life, INFO_X, INFO_Y + INFO_HEIGHT);
        g.drawString("  Kill: " + user1Kill, INFO_X, INFO_Y + 2 * INFO_HEIGHT);
        g.drawString("User2", INFO_X, INFO_Y + 3 * INFO_HEIGHT);
        g.drawString("  HP: " + user2Life, INFO_X, INFO_Y + 4 * INFO_HEIGHT);
        g.drawString("  Kill: " + user2Kill, INFO_X, INFO_Y + 5 * INFO_HEIGHT);
    }

    public void paintUserTanks(Graphics g) {
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
            if (bullet == null) continue;
            bullet.paint(g, this);
        }
    }

    public void paintBarriers(Graphics g) {
        for (BarrierImpl barrier : barriers) {
            barrier.paint(g, this);
        }
    }

    public void paintGifs(Graphics g) {
        for (Gif gif : gifs) {
            gif.paint(g, this);
        }
    }

    public void clearGifs() {
        Vector<Gif> del = new Vector<>();
        for (Gif gif : gifs) {
            if (!gif.isLve()) del.add(gif);
        }
        for (Gif gif : del) {
            gifs.remove(gif);
        }
    }
}
