package panels;

import logic.Manager;
import logic.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    private final JTextField userNameT;
    private final JPasswordField passwordT;
    private final JButton login;
    private final Manager manager;

    public LoginPanel(Manager manager) {
        this.manager = manager;
        this.setLayout(null);
        this.setBounds(0, 0, 350, 350);
        JLabel userNameL = new JLabel("User name:");
        userNameL.setBounds(10, 20, 80, 25);
        this.add(userNameL);

        userNameT = new JTextField();
        userNameT.setBounds(100, 20, 165, 25);
        this.add(userNameT);

        JLabel passwordL = new JLabel("Password:");
        passwordL.setBounds(10, 50, 80, 25);
        this.add(passwordL);

        passwordT = new JPasswordField();
        passwordT.setBounds(100, 50, 165, 25);
        this.add(passwordT);

        login = new JButton("Login");
        login.setBounds(10, 80, 80, 25);
        login.addActionListener(this);
        this.add(login);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String userName = userNameT.getText();
            Player player = manager.search(userName);
            if (player != null) {
                char[] playerPassword = player.getPassword().toCharArray();
                if (passwordT.getPassword().length != playerPassword.length) {
                    showErrorMessageDialog("Password is incorrect");
                    return;
                }
                for (int i = 0; i < playerPassword.length; i++) {
                    if (playerPassword[i] != passwordT.getPassword()[i]) {
                        showErrorMessageDialog("Password is incorrect");
                        return;
                    }
                }
            } else {
                String password = "";
                for (char c : passwordT.getPassword()) {
                    password += c;
                }
                manager.createAccount(userName, password);
                showInformationMessageDialog("Your account created successfully!");
                player = manager.search(userName);
            }
            showInformationMessageDialog(player.getUserName() + " Logged in!");
            manager.login(player.getUserName());
        }
    }

    public void showErrorMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Not found error", JOptionPane.ERROR_MESSAGE);
    }

    public void showInformationMessageDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Not found error", JOptionPane.INFORMATION_MESSAGE);
    }
}
