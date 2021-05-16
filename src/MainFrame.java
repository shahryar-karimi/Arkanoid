import panels.ButtonPanel;
import panels.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int TIME_LOOP = 10;
    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;
    private Timer gameLoopTimer;

    public MainFrame() {
        init();
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

    private void init() {
        gameLoopTimer = new Timer(TIME_LOOP, e -> {
            gamePanel.move();
            gamePanel.checkCollision();
            gamePanel.repaint();
        });
        gamePanel = new GamePanel();
        buttonPanel = new ButtonPanel();
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
}
