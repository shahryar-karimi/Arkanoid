package frames;

import logic.Manager;
import models.Ball;
import models.Paddle;
import models.cells.*;
import models.prizes.Prize;
import models.prizes.ballPrizes.MultiBall;
import panels.mainFramePanels.ButtonPanel;
import panels.mainFramePanels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

    private int timeLoop = 40;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private Timer gameLoopTimer;
    private int winkCounter = 0;
    private int addRowCounter = 0;

    public MainFrame(int timeLoop, GamePanel gamePanel, ButtonPanel buttonPanel, int winkCounter, int addRowCounter) {
        this();
        this.timeLoop = timeLoop;
        this.gamePanel = gamePanel;
        this.buttonPanel = buttonPanel;
        this.winkCounter = winkCounter;
        this.addRowCounter = addRowCounter;
        this.add(gamePanel);
        this.add(buttonPanel);
        init();
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
    }

    public MainFrame() {
        this.addKeyListener(new AL());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
    }

    public MainFrame(String userName, Manager manager) {
        this();
        gamePanel = new GamePanel(userName, manager);
        buttonPanel = new ButtonPanel(userName, manager);
        this.add(gamePanel);
        this.add(buttonPanel);
        init();
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
        this.setVisible(true);
    }

    private void init() {
        gameLoopTimer = new Timer(timeLoop, this);
        buttonPanel.getPower().addActionListener(e -> {
            if (buttonPanel.getPower().getText().equals("Pause")) {
                buttonPanel.getPower().setText("Play");
                buttonPanel.getRestart().setEnabled(true);
                buttonPanel.getScoreBoardB().setEnabled(true);
                buttonPanel.getBack().setEnabled(true);
                buttonPanel.getSavePosition().setEnabled(true);
                buttonPanel.getExit().setEnabled(true);
                gameLoopTimer.stop();
            } else {
                buttonPanel.getPower().setText("Pause");
                gameLoopTimer.start();
                buttonPanel.getRestart().setEnabled(false);
                buttonPanel.getScoreBoardB().setEnabled(false);
                buttonPanel.getBack().setEnabled(false);
                buttonPanel.getSavePosition().setEnabled(false);
                buttonPanel.getExit().setEnabled(false);
            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Prize> tokenPrize = gamePanel.getTokenPrizes();
        for (int i = 0; i < tokenPrize.size(); i++) {
            Prize prize = tokenPrize.get(i);
            if (prize instanceof MultiBall) {
                tokenPrize.remove(prize);
                i--;
            } else {
                prize.setTime(prize.getTime() - timeLoop);
                if (prize.getTime() <= 0) {
                    prize.offPrize(gamePanel);
                    tokenPrize.remove(prize);
                    i--;
                }
            }
        }

        if (gamePanel.isGameOver()) {
            restart();
        }
        gamePanel.move();
        gamePanel.checkCollision();

        winkCounter++;
        if (winkCounter == 20) {
            winkCounter = 0;
            gamePanel.wink();
        }
        addRowCounter++;
        if (addRowCounter * timeLoop >= 20_000) {
            addRowCounter = 0;
            gamePanel.moveCells();
            timeLoop--;
            gameLoopTimer.stop();
            gameLoopTimer.setDelay(timeLoop);
            gameLoopTimer.restart();
        }
        gamePanel.repaint();
    }

    public void restart() {
        gameLoopTimer.stop();
        buttonPanel.getPower().setText("Play");
        gamePanel.restart();
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public int getTimeLoop() {
        return timeLoop;
    }

    public void setTimeLoop(int timeLoop) {
        this.timeLoop = timeLoop;
    }

    public int getWinkCounter() {
        return winkCounter;
    }

    public void setWinkCounter(int winkCounter) {
        this.winkCounter = winkCounter;
    }

    public int getAddRowCounter() {
        return addRowCounter;
    }

    public void setAddRowCounter(int addRowCounter) {
        this.addRowCounter = addRowCounter;
    }

    @Override
    public MainFrame clone() {
        GamePanel gamePanel = this.gamePanel.clone();
        ButtonPanel buttonPanel = this.buttonPanel.clone();
        return new MainFrame(timeLoop, gamePanel, buttonPanel, winkCounter, addRowCounter);
    }

    public class AL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            gamePanel.getPaddle().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gamePanel.getPaddle().keyReleased(e);
        }
    }
}
