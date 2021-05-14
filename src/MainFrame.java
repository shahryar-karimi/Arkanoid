import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    GamePanel gamePanel;

    public MainFrame() {
        gamePanel = new GamePanel();

        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);
    }
}
