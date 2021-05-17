package logic;

import models.Score;

import java.util.ArrayList;

public class Player {
    private String userName;
    private String password;
    private Score score;
    private ArrayList<Score> scores;

    public Player(String userName, String password, int PANEL_WIDTH, int PANEL_HEIGHT) {
        this.userName = userName;
        this.password = password;
        this.scores = new ArrayList<>();
        this.score = new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }

    public Player() {

    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public Player setScores(ArrayList<Score> scores) {
        this.scores = scores;
        return this;
    }

    public void addScore() {
        scores.add(score);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Player setPassword(String password) {
        this.password = password;
        return this;
    }

    public Player setUserName(String userName) {
        this.userName = userName;
        return this;
    }
}
