import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;
    private ButtonPanel buttonPanel;

    public MainFrame() {
        gamePanel = new GamePanel();
        buttonPanel = new ButtonPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.add(gamePanel);
        this.add(buttonPanel);
        this.pack();
        this.setVisible(true);

        run();
    }

    private void run() {
        while (true) {
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
