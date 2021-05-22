package panels.mainFramePanels;

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
    private JButton savePosition;
    private JButton exit;
    private final Manager manager;
    private final String userName;

    public ButtonPanel(String userName, Manager manager) {
        this.userName = userName;
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
        scoreBoardB.addActionListener(e -> manager.scoreBoardIn(userName));

        back = new JButton("Back");
        back.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 5 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        back.setFocusable(false);
        back.addActionListener(e -> manager.logout());

        savePosition = new JButton("Save Position");
        savePosition.setBounds((PANEL_WIDTH - BUTTON_WIDTH) / 2, PANEL_HEIGHT - 7 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        savePosition.setFocusable(false);
        savePosition.setEnabled(false);
        savePosition.addActionListener(e -> manager.savePosition());

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
        this.add(savePosition);
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

    public JButton getSavePosition() {
        return savePosition;
    }

    public JButton getExit() {
        return exit;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public ButtonPanel clone() {
        return new ButtonPanel(getUserName(), getManager());
    }

    public Manager getManager() {
        return manager;
    }
}
