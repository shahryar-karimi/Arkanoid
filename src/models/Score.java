package models;

import java.awt.*;

public class Score extends Rectangle {
    private int score;
    private int heal;

    public Score(int x, int y, int width, int height, int score, int heal) {
        super(x, y, width, height);
        this.score = score;
        this.heal = heal;
    }

    public Score(int x, int y, int width, int height, int score) {
        this(x, y, width, height, score, 3);
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

    public int getHeal() {
        return heal;
    }

    public void loseHeal() {
        if (heal > 0) heal--;
    }

    public void increaseHeal() {
        heal++;
    }

    @Override
    public Score clone() {
        return new Score(x, y, width, height, score, heal);
    }
}
