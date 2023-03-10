package View.Swing.Frames.Admin;

import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import View.Swing.Panels.Admin.TransactionnelPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TransactionnelFrame extends JFrame {
    public TransactionnelFrame(Banque banque) {
        super("Transactionnel");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FilesHandler.save(banque);
                LogsDAO.write("_______________________________________________________Fin Session");
                System.exit(0);
            }
        });
        setSize(600, 400);
        setLocationRelativeTo(null);
        add(new TransactionnelPanel(banque));
        setVisible(true);
    }
}