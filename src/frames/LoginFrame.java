package frames;

import logic.Manager;
import panels.LoginPanel;

import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame(Manager manager) {
        LoginPanel loginPanel = new LoginPanel(manager);

        this.setSize(350, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.add(loginPanel);

        this.setVisible(true);
    }
}
