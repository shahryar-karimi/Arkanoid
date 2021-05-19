package logic;

import frames.MainFrame;
import models.Score;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String userName;
    private String password;
    private Score score;
    private ArrayList<Score> tokenScores;
    private HashMap<String, MainFrame> pausesGames;

    public Player(String userName, String password, int PANEL_WIDTH, int PANEL_HEIGHT) {
        this.userName = userName;
        this.password = password;
        this.tokenScores = new ArrayList<>();
        this.pausesGames = new HashMap<>();
        this.score = new Score(0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }

    public Player() {}

    public ArrayList<Score> getTokenScores() {
        return tokenScores;
    }

    public Player setTokenScores(ArrayList<Score> tokenScores) {
        this.tokenScores = tokenScores;
        return this;
    }

    public void addScore() {
        tokenScores.add(score);
    }

    public Score getScore() {
        return score;
    }

    public Player setScore(Score score) {
        this.score = score;
        return this;
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

    public void addPausesGame(String name, MainFrame mainFrame) {
        this.pausesGames.put(name, mainFrame);
    }

    public HashMap<String, MainFrame> getPausesGames() {
        return pausesGames;
    }

    public void setPausesGames(HashMap<String, MainFrame> pausesGames) {
        this.pausesGames = pausesGames;
    }
}
