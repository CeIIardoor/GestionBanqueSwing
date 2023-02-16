package View.Swing.Frames.Admin;

import Model.Banque;
import View.Swing.Panels.Admin.AdminPanel;

import javax.swing.*;

public class AdminFrame extends JFrame {
    public AdminFrame(Banque banque) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        AdminPanel adminPanel = new AdminPanel(banque);
        add(adminPanel);
        setVisible(true);
    }
}