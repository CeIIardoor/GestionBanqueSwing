package View.Swing.Frames.Admin;

import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import View.Swing.Panels.Admin.CRUDPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CRUDFrame extends JFrame {
    public CRUDFrame(Banque banque) {
        super("CRUD");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FilesHandler.save(banque);
                System.exit(0);
            }
        });
        setSize(800, 600);
        setLocationRelativeTo(null);
        CRUDPanel crudPanel = new CRUDPanel(banque);
        add(crudPanel);
        setVisible(true);
    }
}