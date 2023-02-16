package View.Swing.Frames.Admin;

import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import View.Swing.Panels.Admin.AdminPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminFrame extends JFrame {
    public AdminFrame(Banque banque) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FilesHandler.save(banque);
                System.exit(0);
            }
        });
        setSize(600, 400);
        setLocationRelativeTo(null);
        AdminPanel adminPanel = new AdminPanel(banque);
        add(adminPanel);
        setVisible(true);
    }
}