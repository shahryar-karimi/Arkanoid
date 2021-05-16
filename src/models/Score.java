package models;

import java.awt.*;

public class Score extends Rectangle {
    private int score;
    private int heal;

    public Score(int x, int y, int width, int height, int score) {
        super(x, y, width, height);
        this.score = score;
        this.heal = 3;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Heal: " + heal + "     Score: " + score, width - 130, height - 20);
        g.drawLine(0, 9 * height / 10, width, 9 * height / 10);
    }

    public void setHeal(int playerHeal) {
        this.heal = playerHeal;
    }
}
