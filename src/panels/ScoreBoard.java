package panels;

import logic.Manager;

import javax.swing.*;

public class ScoreBoard extends JPanel {

    private Manager manager;

    public ScoreBoard(Manager manager) {
        this.manager = manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
