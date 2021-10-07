package cn.lllllan.game;

import cn.lllllan.cube.tank.UserTank1;
import cn.lllllan.cube.tank.UserTank2;
import cn.lllllan.stage.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author lllllan
 * <p>
 * version 0.1.0
 **/

@SuppressWarnings({"all"})

public class Game implements KeyListener {

    private static JFrame jFrame;
    private static InitialStage initialStage;
    private static TankSelectStage tankSelectStage;
    private static LevelSelectStage levelSelectStage;
    private static Vector<LevelStage> levels;
    private static Stage currentStage;

    private static Thread stageThread;

    /**
     * 当前的游戏阶段
     *
     * <ul>
     *     <li>0 - initial stage</li>
     *     <li>1 - tank select stage</li>
     *     <li>2 - level select stage</li>
     *     <li>3 - level stage</li>
     * </ul>
     */
    private static final int MAX_INDEX = 3;
    private static int stageIndex;
    private static int levelInedx;

    public static void main(String[] args) {
        // 新建窗体
        jFrame = new JFrame();
        // 键盘监听
        Game game = new Game();

        levels = new Vector<>();
        levels.add(new LevelStage());

        initialStage = new InitialStage();
        tankSelectStage = new TankSelectStage();
        levelSelectStage = new LevelSelectStage();

        stageIndex = levelInedx = 0;
        currentStage = initialStage;
        levelSelectStage.setMaxLevel(levels.size() - 1);

        stageThread = new Thread(currentStage);
        stageThread.start();

        jFrame.add(currentStage);
        jFrame.addKeyListener(game);
        jFrame.setSize(1400, 800);
        //设置窗口居中
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); //获取屏幕的尺寸
        jFrame.setLocation(screen.width / 2 - jFrame.getWidth() / 2, screen.height / 2 - jFrame.getHeight() / 2);
        //设置窗口大小不可变
        jFrame.setResizable(false);
        // 关闭窗口即结束程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void setProperty() {
        // !!!! 删除上一个画板，避免留在窗体的底部
        jFrame.remove(currentStage);

        switch (stageIndex) {
            case 0:
                currentStage = initialStage;
                break;
            case 1:
                currentStage = tankSelectStage;
                break;
            case 2:
                currentStage = levelSelectStage;
                break;
            case 3:
            default:
                currentStage = levels.get(levelInedx);
                break;
        }

        stageThread = new Thread(currentStage);
        stageThread.start();

        jFrame.add(currentStage);
        jFrame.setVisible(true);
    }

    public void nextStage() {
        System.out.println("nextStage " + stageIndex);
        if (stageIndex < MAX_INDEX) {
            stageThread.interrupt();
            stageIndex++;
            setProperty();
        }
    }

    public void setStageIndex(int stageIndex) {
        if (stageIndex < MAX_INDEX) {
            stageThread.interrupt();
            this.stageIndex = stageIndex;
            setProperty();
        }
    }

    public void setUsersOfLevels(int[] users) {
        UserTank1 user1 = users.length > 0 ? new UserTank1(users[0], 0, 0) : null;
        UserTank2 user2 = users.length > 1 ? new UserTank2(users[1], 0, 0) : null;

        for (LevelStage level : levels) {
            level.setUserTanks(user1, user2);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentStage.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentStage.keyPressed(e);

        System.out.println(e.getKeyCode());

        // 如果在初始界面按下回车
        // 代表选好人数模式，为此创建相应数量的用户坦克，起始位置先不管
        if (stageIndex == 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            tankSelectStage.setUserNumber(initialStage.getIndex() + 1);

            nextStage();
        } else if (stageIndex == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            setUsersOfLevels(tankSelectStage.getIndex());

            nextStage();
        } else if (stageIndex == 2 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            levelInedx = levelSelectStage.getLevel();

            nextStage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentStage.keyReleased(e);

        if (e.getKeyCode() == KeyEvent.VK_F6 && stageIndex > 0) {
            setStageIndex(0);
        }

        if (e.getKeyCode() == KeyEvent.VK_F7 && stageIndex > 1) {
            setStageIndex(1);
        }

        if (e.getKeyCode() == KeyEvent.VK_F8 && stageIndex > 2) {
            setStageIndex(2);
        }
    }
}
