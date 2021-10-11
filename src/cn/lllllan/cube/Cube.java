package cn.lllllan.cube;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/


@SuppressWarnings({"all"})

public class Cube {

    private static final int SIZE = 50;

    private int x;
    private int y;
    private int life;

    private boolean isTank;
    private boolean canBeBorken;
    private boolean canTankPass;
    private boolean canBulletPass;

    public Cube(int x, int y, boolean isTank, boolean canBeBorken, boolean canTankPass, boolean canBulletPass) {
        this.x = x;
        this.y = y;
        this.life = 1;
        this.isTank = isTank;
        this.canBeBorken = canBeBorken;
        this.canTankPass = canTankPass;
        this.canBulletPass = canBulletPass;
    }

    public void setProperty(boolean canBeBorken, boolean canTankPass, boolean canBulletPass) {
        this.canBeBorken = canBeBorken;
        this.canTankPass = canTankPass;
        this.canBulletPass = canBulletPass;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void lifeDown() {
        if (life > 0) life--;
    }

    public void lifeUp() {
        life++;
    }

    public boolean isTank() {
        return isTank;
    }

    public boolean isCanBeBorken() {
        return canBeBorken;
    }

    public boolean isCanTankPass() {
        return canTankPass;
    }

    public boolean isCanBulletPass() {
        return canBulletPass;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static int getSIZE() {
        return SIZE;
    }

    public int getWidth() {
        return SIZE;
    }

    public int getHeight() {
        return SIZE;
    }
}
