package logic;

import frames.*;

import javax.swing.*;
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

    public void loginNewMainFrame(String userName) {
        if (loginFrame != null) loginFrame.dispose();
        mainFrame = new MainFrame(userName, this);
    }

    public void login(MainFrame mainFrame) {
        if (loginFrame != null) loginFrame.dispose();
        this.mainFrame = mainFrame;
        mainFrame.setVisible(true);
    }

    public void logout() {
        savePlayers();
        if (mainFrame != null) mainFrame.dispose();
        loginFrame = new LoginFrame(this);
    }

    public void exit() {
        savePlayers();
        System.exit(0);
    }

    public void savePlayers() {
        SavePlayers.save(this);
    }

    public void savePosition() {
        String name = JOptionPane.showInputDialog(null, "Enter save name:", "Get name", JOptionPane.QUESTION_MESSAGE);
        while (name == null) name = JOptionPane.showInputDialog(null, "Enter save name:", "Get name", JOptionPane.QUESTION_MESSAGE);
        mainFrame.getGamePanel().getPlayer().addPausesGame(name, mainFrame);
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

    public void scoreBoardIn(String userName) {
        if (mainFrame != null) mainFrame.dispose();
        scoreBoardFrame = new ScoreBoardFrame(userName, this);
    }

    public void scoreBoardOut (String userName) {
        if (scoreBoardFrame != null) scoreBoardFrame.dispose();
        mainFrame = new MainFrame(userName, this);
    }
}
