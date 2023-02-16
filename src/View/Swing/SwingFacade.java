package View.Swing;

import DAO.FilesDAO.DataLoader;
import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import View.Swing.Panels.Auth.LoginPanel;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SwingFacade {
    public static void run() throws FileNotFoundException {
        Banque banque = new Banque("Dire Straits Bank", "direstraits@banque.com", 100);
//      FilesHandler.flush(); // Pour vider la base de données

        if (!FilesHandler.BASE_FOLDER.toFile().exists()) { // Si le dossier DB n'existe pas
            FilesHandler.init();
            FilesHandler.seed();
        }

        DataLoader dataLoader = new DataLoader(banque);
        dataLoader.load("clients");
        dataLoader.load("comptes");

        JFrame loginFrame = new JFrame("Dire Straits Bank");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(600, 400);
        loginFrame.setLocationRelativeTo(null);
        LoginPanel loginPanel = new LoginPanel(banque);
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);

    }
}