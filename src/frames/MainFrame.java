package frames;

import logic.Manager;
import logic.Player;
import models.Paddle;
import models.prizes.Prize;
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

    private static int TIME_LOOP = 40;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private Timer gameLoopTimer;
    private int winkCounter = 0;
    private long addRowCounter = 0;
    private boolean isNormal = true;

    public MainFrame(Player player, Manager manager) {
        init(player, manager);
        this.addKeyListener(new AL());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.add(gamePanel);
        this.add(buttonPanel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
        this.setVisible(true);
    }

    private void init(Player player, Manager manager) {
        gameLoopTimer = new Timer(TIME_LOOP, this);
        gamePanel = new GamePanel(player, manager);
        buttonPanel = new ButtonPanel(player, manager);
        buttonPanel.getPower().addActionListener(e -> {
            if (buttonPanel.getPower().getText().equals("Pause")) {
                buttonPanel.getPower().setText("Play");
                buttonPanel.getRestart().setEnabled(true);
                gameLoopTimer.stop();
            } else {
                buttonPanel.getPower().setText("Pause");
                gameLoopTimer.start();
                buttonPanel.getRestart().setEnabled(false);
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
            prize.setTime(prize.getTime() - TIME_LOOP);
            if (prize.getTime() <= 0) {
                prize.offPrize(gamePanel);
                tokenPrize.remove(prize);
                i--;
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
        if (addRowCounter * TIME_LOOP >= 20_000) {
            addRowCounter = 0;
            gamePanel.moveCells();
            TIME_LOOP--;
            gameLoopTimer.stop();
            gameLoopTimer.setDelay(TIME_LOOP);
            gameLoopTimer.restart();
        }
        gamePanel.repaint();
    }

    public void restart() {
        gameLoopTimer.stop();
        buttonPanel.getPower().setText("Play");
        gamePanel.restart();
    }

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
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
