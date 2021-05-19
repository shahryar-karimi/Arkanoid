package frames;

import logic.Manager;
import panels.scoreBoardPanels.ButtonPanel;
import panels.scoreBoardPanels.ScoreBoardPanel;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardFrame extends JFrame {

    private ScoreBoardPanel scoreBoardPanel;
    private ButtonPanel buttonPanel;

    public ScoreBoardFrame(String userName, Manager manager) {
        scoreBoardPanel = new ScoreBoardPanel(manager);
        buttonPanel = new ButtonPanel(userName, manager);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(500, 500);

        this.add(scoreBoardPanel);
        this.add(buttonPanel);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
        this.setVisible(true);
    }
}
