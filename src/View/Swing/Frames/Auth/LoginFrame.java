package View.Swing.Frames.Auth;

import Model.Banque;
import View.Swing.Panels.Auth.LoginPanel;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame(Banque banque) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        LoginPanel loginPanel = new LoginPanel(banque);
        add(loginPanel);
        setVisible(true);
    }
}