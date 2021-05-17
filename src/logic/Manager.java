package logic;

import frames.*;

import java.util.ArrayList;

public class Manager {
    private static final int PANEL_WIDTH = 509;
    private static final int PANEL_HEIGHT = 509;

    private ArrayList<Player> players;
    private MainFrame mainFrame;
    private LoginFrame loginFrame;
    private ScoreBoardFrame scoreBoardFrame;

    public Manager() {
        players = new ArrayList<>();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void createAccount(String userName, String password) {
        players.add(new Player(userName, password, PANEL_WIDTH, PANEL_HEIGHT));
    }

    public Player search(String userName) {
        for (Player player : players)
            if (player.getUserName().equals(userName)) return player;
        return null;
    }

    public void login(Player player) {
        if (loginFrame != null) loginFrame.dispose();
        mainFrame = new MainFrame(player, this);
    }

    public void logout() {
        save();
        if (mainFrame != null) mainFrame.dispose();
        loginFrame = new LoginFrame(this);
    }

    public void exit() {
        save();
        System.exit(0);
    }

    public void save() {
        Save.save(this);
    }

    public void restart() {
        mainFrame.restart();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setLoginFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
    }

    public void start() {
        setLoginFrame(new LoginFrame(this));
    }

    public void scoreBoard() {
        if (mainFrame != null) mainFrame.dispose();
        scoreBoardFrame = new ScoreBoardFrame(this);
    }
}
