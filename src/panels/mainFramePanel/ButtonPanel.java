package panels.mainFramePanel;

import logic.Manager;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private static final int PANEL_WIDTH = 150;
    private static final int PANEL_HEIGHT = 500;
    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 40;
    private static final Dimension SCREEN_SIZE = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
    private JButton power;
    private JButton restart;
    private JButton scoreBoardB;
    private JButton back;
    private JButton save;
    private JButton exit;
    private Manager manager;

    public ButtonPanel(Manager manager) {
        this.manager = manager;
        this.setBorder(null);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLUE);
        this.setLayout(null);
        init();
    }

    private void init() {
        power = new JButton("Play");
        power.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        power.setFocusable(false);

        scoreBoardB = new JButton("Score Board");
        scoreBoardB.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 3 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        scoreBoardB.setFocusable(false);
        scoreBoardB.addActionListener(e -> manager.scoreBoard());

        back = new JButton("Back");
        back.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 5 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        back.setFocusable(false);
        back.addActionListener(e -> manager.logout());

        save = new JButton("Save");
        save.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 7 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        save.setFocusable(false);
        save.addActionListener(e -> manager.save());

        exit = new JButton("Exit");
        exit.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 9 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        exit.setFocusable(false);
        exit.addActionListener(e -> manager.exit());


        restart = new JButton("Restart");
        restart.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 11 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        restart.setFocusable(false);
        restart.setEnabled(false);
        restart.addActionListener(e -> manager.restart());

        this.add(power);
        this.add(back);
        this.add(save);
        this.add(exit);
        this.add(scoreBoardB);
        this.add(restart);
    }

    public JButton getPower() {
        return power;
    }

    public JButton getRestart() {
        return restart;
    }

    public JButton getScoreBoardB() {
        return scoreBoardB;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getSave() {
        return save;
    }

    public JButton getExit() {
        return exit;
    }
}
