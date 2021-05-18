package panels.scoreBoardPanels;

import logic.Manager;
import logic.Player;
import models.Score;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardPanel extends JPanel {

    private static final int PANEL_WIDTH = 400;
    private static final int PANEL_HEIGHT = 500;
    private Manager manager;
    private ArrayList<String> scores;
    private JTextPane textPane;

    public ScoreBoardPanel(Manager manager) {
        this.manager = manager;
        this.scores = new ArrayList<>();
        addScore();

        this.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        this.setBackground(new Color(12, 23, 45));
        this.setOpaque(true);
        this.setLayout(new FlowLayout());

        setUpTextPane();
    }

    private void setUpTextPane() {
        this.textPane = new JTextPane();
        this.textPane.setBackground(new Color(12, 34, 56));
        this.textPane.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        String table = "";
        int counter = 1;
        for (String score : scores) {
            table += counter + ". " + score + "\n";
            counter++;
        }
        textPane.setText(table);

        scrollPane.setViewportView(textPane);
        this.add(scrollPane);
    }

    private void addScore() {
        for (Player player : manager.getPlayers()) {
            for (Score score : player.getScores()) {
                scores.add(player.getUserName() + " : " + score.getScore());
            }
        }
        sort();
    }

    private void sort() {
        for (int i = 0; i < scores.size(); i++) {
            String max = scores.get(i);
            int index = i;
            for (int j = i + 1; j < scores.size(); j++) {
                if (compare(max, scores.get(j)) < 0) {
                    max = scores.get(j);
                    index = j;
                }
            }
            scores.set(index, scores.get(i));
            scores.set(i, max);
        }
    }

    private int compare(String s1, String s2) {
        int i1 = Integer.parseInt(s1.split(" : ")[1]);
        int i2 = Integer.parseInt(s2.split(" : ")[1]);
        return Integer.compare(i1, i2);
    }


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
