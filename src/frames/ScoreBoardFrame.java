package frames;

import logic.Manager;
import panels.ScoreBoardPanel;

import javax.swing.*;

public class ScoreBoardFrame extends JFrame {

    private ScoreBoardPanel scoreBoardPanel;
    private Manager manager;

    public ScoreBoardFrame(Manager manager) {
        this.manager = manager;
        scoreBoardPanel = new ScoreBoardPanel(manager);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setSize(500, 500);

        this.add(scoreBoardPanel);

        this.setVisible(true);
    }
}
