package cn.lllllan.game;

import cn.lllllan.cube.tank.UserTank;
import cn.lllllan.cube.tank.UserTank1;
import cn.lllllan.cube.tank.UserTank2;
import cn.lllllan.data.Data1;
import cn.lllllan.data.Data2;
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

public class Game implements KeyListener, Runnable {

    private static JFrame jFrame;
    private static InitialStage initialStage;
    private static TankSelectStage tankSelectStage;
    private static LevelSelectStage levelSelectStage;
    private static WinStage winStage;
    private static LoseStage loseStage;
    private static EndStage endStage;
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
    private static final int MAX_INDEX = 6;
    private static int stageIndex;
    private static int levelInedx;

    public static void main(String[] args) {
        // 新建窗体
        jFrame = new JFrame();
        // 键盘监听
        Game game = new Game();
        new Thread(game).start();

        levels = new Vector<>();
        levels.add(new LevelStage(new Data1()));
        levels.add(new LevelStage(new Data2()));

        initialStage = new InitialStage();
        tankSelectStage = new TankSelectStage();
        levelSelectStage = new LevelSelectStage(levels.size());
        winStage = new WinStage();
        loseStage = new LoseStage();
        endStage = new EndStage();

        stageIndex = levelInedx = 0;
        currentStage = initialStage;
        levelSelectStage.setActiveNumber(1);

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

    public UserTank[] getUserTanks() {
        int[] users = tankSelectStage.getIndex();
        UserTank1 user1 = users.length > 0 ? new UserTank1(users[0], 0, 0) : null;
        UserTank2 user2 = users.length > 1 ? new UserTank2(users[1], 0, 0) : null;
        return new UserTank[]{user1, user2};
    }

    public void levelReInit(LevelStage level, UserTank[] users) {
        level.reInit(users);
    }

    public void setUsersOfLevels() {
        UserTank[] users = getUserTanks();

        for (LevelStage level : levels) {
            levelReInit(level, users);
        }
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
                LevelStage level = levels.get(levelInedx);
                // 每到一个信的关卡、F5 重新开始，需要重新赋予两只坦克并恢复初始状态
                levelReInit(level, getUserTanks());
                currentStage = level;
                break;
            case 4:
                currentStage = winStage;
                break;
            case 5:
                currentStage = loseStage;
                break;
            case 6:
                currentStage = endStage;
                break;
            default:
        }

        stageThread = new Thread(currentStage);
        stageThread.start();

        jFrame.add(currentStage);
        jFrame.setVisible(true);
    }

    public void setStageIndex(int stageIndex) {
        if (stageIndex <= MAX_INDEX) {
            stageThread.interrupt();
            this.stageIndex = stageIndex;
            setProperty();
        }
    }

    public void nextStage() {
        setStageIndex(stageIndex + 1);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentStage.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentStage.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentStage.keyReleased(e);

        if (e.getKeyCode() == KeyEvent.VK_SPACE && stageIndex == 3) {
            ((LevelStage) currentStage).stop();
        } else if (e.getKeyCode() == KeyEvent.VK_F5 && stageIndex == 3) {
            setStageIndex(3);
        } else if (e.getKeyCode() == KeyEvent.VK_F6 && stageIndex > 0) {
            setStageIndex(0);
        } else if (e.getKeyCode() == KeyEvent.VK_F7 && stageIndex > 1) {
            setStageIndex(1);
        } else if (e.getKeyCode() == KeyEvent.VK_F8 && stageIndex > 2) {
            setStageIndex(2);
        } else {
            if ((stageIndex == 4 || stageIndex == 5) && e.getKeyCode() == KeyEvent.VK_ENTER) {
                setStageIndex(3);
            } else if (stageIndex == 6) {
                setStageIndex(0);
            } else {
                if (stageIndex == 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tankSelectStage.setUserNumber(initialStage.getIndex() + 1);

                    nextStage();
                } else if (stageIndex == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    setUsersOfLevels();

                    nextStage();
                } else if (stageIndex == 2 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    levelInedx = levelSelectStage.getLevel() - 1;

                    nextStage();
                }
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (stageIndex == 3) {
                if (((LevelStage) currentStage).isOver()) {
                    int nxt = levelInedx + 1;
                    if (nxt < levelSelectStage.getTotalNumber()) {
                        levelInedx++;
                        levelSelectStage.setMoreActiveNumber(levelInedx + 1);
                        setStageIndex(4);
                    } else {
                        setStageIndex(6);
                    }
                } else if (!((LevelStage) currentStage).isLive()) {
                    setStageIndex(5);
                }
            }

//            System.out.println(Thread.currentThread().getName());
        }
    }
}
