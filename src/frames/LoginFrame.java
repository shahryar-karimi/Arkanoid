package frames;

import logic.Manager;
import panels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private LoginPanel loginPanel;
    private Manager manager;

    public LoginFrame(Manager manager) {
        this.manager = manager;
        loginPanel = new LoginPanel(manager);

        this.setSize(350, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(loginPanel);

        this.setVisible(true);
    }
}
