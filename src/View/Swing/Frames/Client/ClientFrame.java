package View.Swing.Frames.Client;

import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import Model.Client;
import View.Swing.Panels.Client.ClientPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientFrame extends JFrame {
    public ClientFrame(Banque banque, Client client) {
        super("Client");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FilesHandler.save(banque);
                LogsDAO.write("_______________________________________________________Fin Session");
                System.exit(0);
            }
        });
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        ClientPanel clientPanel = new ClientPanel(banque, client);
        this.add(clientPanel);
    }
}