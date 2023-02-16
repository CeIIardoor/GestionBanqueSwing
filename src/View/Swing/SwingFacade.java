package View.Swing;

import View.Swing.Panels.Auth.LoginPanel;

import javax.swing.*;

public class SwingFacade {
    public static void run() {
        JFrame frame = new JFrame("Dire Straits Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(new LoginPanel());
    }
}