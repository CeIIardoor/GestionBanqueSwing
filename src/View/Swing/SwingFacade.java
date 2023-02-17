package View.Swing;

import DAO.FilesDAO.DataLoader;
import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import View.Swing.Frames.Auth.LoginFrame;

import java.io.FileNotFoundException;

public class SwingFacade {
    public static void run() throws FileNotFoundException {
        Banque banque = new Banque("Dire Straits Bank", "direstraits@banque.com", 100);
//      FilesHandler.flush(); // Pour vider la base de données
//      FilesHandler.drop(); // Pour supprimer la base de données
        if (!FilesHandler.BASE_FOLDER.toFile().exists()) { // Si le dossier DB n'existe pas
            FilesHandler.init();
            FilesHandler.seed();
        }

        DataLoader dataLoader = new DataLoader(banque);
        dataLoader.load("clients");
        dataLoader.load("comptes");

        LogsDAO.write("_______________________________________________________Nouvelle Session");

        new LoginFrame(banque);
    }
}