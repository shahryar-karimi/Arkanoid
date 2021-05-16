package panels;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private static final int PANEL_WIDTH = 50;
    private static final int PANEL_HEIGHT = 500;
    private static final int BUTTON_WIDTH = 30;
    private static final int BUTTON_HEIGHT = 10;
    private static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private JButton power;

    public ButtonPanel() {
        this.setBorder(null);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLUE);
        power = new JButton("Play");
        power.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        power.setFocusable(false);

        this.add(power);
    }

    public JButton getPower() {
        return power;
    }
}
