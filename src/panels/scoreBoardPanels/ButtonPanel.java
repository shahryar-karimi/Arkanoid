package panels.scoreBoardPanels;

import logic.Manager;
import logic.Player;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private static final int PANEL_WIDTH = 100;
    private static final int PANEL_HEIGHT = 500;
    private static final int BUTTON_WIDTH = 80;
    private static final int BUTTON_HEIGHT = 40;
    private JButton back;

    public ButtonPanel(String userName, Manager manager) {
        back = new JButton("Back");
        back.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, (PANEL_HEIGHT - BUTTON_HEIGHT) / 2, BUTTON_WIDTH, BUTTON_HEIGHT);
        back.setFocusable(false);
        back.addActionListener(e -> manager.scoreBoardOut(userName));

        this.setBounds(400, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(new Color(12, 23, 45));
        this.setOpaque(true);
        this.setLayout(null);
        this.add(back);
    }
}
