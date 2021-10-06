package cn.lllllan.game;

import cn.lllllan.cube.tank.UserTank;
import cn.lllllan.cube.tank.UserTank1;
import cn.lllllan.cube.tank.UserTank2;
import cn.lllllan.stage.InitialStage;
import cn.lllllan.stage.LevelStage;
import cn.lllllan.stage.SelectStage;
import cn.lllllan.stage.Stage;

import javax.swing.*;
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

    /**
     * 窗体
     */
    private static JFrame jFrame;

    /**
     * 所有的阶段，包括初始界面和各个游戏关卡
     */
    private static InitialStage initialStage;
    private static SelectStage selectStage;
    private static Vector<LevelStage> levels;
    private static Stage currentStage;

    /**
     * 当前的画板线程
     */
    private static Thread stageThread;

    /**
     * 当前的游戏阶段
     *
     * <ul>
     *     <li>0 - initial stage</li>
     *     <li>1 - select stage</li>
     *     <li>2 - level stage</li>
     * </ul>
     */
    private static final int MAX_INDEX = 2;
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
        selectStage = new SelectStage();

        stageIndex = levelInedx = 0;
        currentStage = initialStage;
        selectStage.setMaxLevel(levels.size() - 1);

        stageThread = new Thread(currentStage);
        stageThread.start();

        jFrame.add(currentStage);
        jFrame.addKeyListener(game);
        jFrame.setSize(1400, 800);
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
                currentStage = selectStage;
                break;
            case 2:
            default:
                currentStage = levels.get(levelInedx);
                break;
        }

        stageThread = new Thread(currentStage);
        stageThread.start();

        jFrame.add(currentStage);
        jFrame.setVisible(true);
    }

    /**
     * Desc: 进入下一个游戏阶段
     *
     * @param
     * @return
     * @author lllllan
     * @date 2021/10/6 0:57
     */
    public void nextStage() {
        System.out.println("nextStage " + stageIndex);
        if (stageIndex < MAX_INDEX) {
            stageThread.interrupt();
            stageIndex++;
            setProperty();
        }
    }

    public void setUserOfLevels(boolean flag) {
        UserTank user1 = new UserTank1(0, 0);
        UserTank user2 = flag ? new UserTank2(0, 0) : null;

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
            boolean flag = ((InitialStage) currentStage).getIndex() == 1;
            setUserOfLevels(flag);

            nextStage();
        } else if (stageIndex == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            levelInedx = selectStage.getLevel();

            nextStage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentStage.keyReleased(e);
    }
}
