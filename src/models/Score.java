package models;

import java.awt.*;

public class Score extends Rectangle {
    private int score;

    public Score(int x, int y, int width, int height, int score) {
        super(x, y, width, height);
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(getScore()), width - 30, height - 20);
    }
}
