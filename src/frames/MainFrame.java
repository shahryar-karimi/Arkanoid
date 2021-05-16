package frames;

import logic.Manager;
import logic.Player;
import panels.ButtonPanel;
import panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements ActionListener {

    private static int TIME_LOOP = 30;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private Timer gameLoopTimer;
    private int velocityCounter = 1;
    private int winkCounter = 0;
    private long addRowCounter = 0;
    private Manager manager;

    public MainFrame(Player player, Manager manager) {
        this.manager = manager;
        init(player);
        this.addKeyListener(new AL());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.add(gamePanel);
        this.add(buttonPanel);
        this.pack();
        this.setVisible(true);
    }

    private void init(Player player) {
        gameLoopTimer = new Timer(TIME_LOOP, this);
        gamePanel = new GamePanel(player, manager);
        buttonPanel = new ButtonPanel(manager);
        buttonPanel.getPower().addActionListener(e -> {
            if (buttonPanel.getPower().getText().equals("Pause")) {
                buttonPanel.getPower().setText("Play");
                gameLoopTimer.stop();
            } else {
                buttonPanel.getPower().setText("Pause");
                gameLoopTimer.start();
            }
        });
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gamePanel.move();
        gamePanel.checkCollision();
        if (gamePanel.getBall().intersects(gamePanel.getPaddle())) {

        }
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
