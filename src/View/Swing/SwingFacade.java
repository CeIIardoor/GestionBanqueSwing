package View.Swing;

import DAO.FilesDAO.DataLoader;
import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import View.Swing.Frames.Auth.LoginFrame;

import java.io.FileNotFoundException;
import java.util.Objects;

public class SwingFacade {
    public static void run() throws FileNotFoundException {
        Banque banque = new Banque("Dire Straits Bank", "direstraits@banque.com", 100);
        if (!FilesHandler.BASE_FOLDER.toFile().exists()) { // Si le dossier DB n'existe pas
            FilesHandler.init();
            FilesHandler.seed();
        } else { // Edge case : si le dossier DB existe, mais qu'il est endommagé
            if (Objects.requireNonNull(FilesHandler.BASE_FOLDER.toFile().list()).length < 3) {
                FilesHandler.drop();
                FilesHandler.init();
                FilesHandler.seed();
            }
        }

        DataLoader dataLoader = new DataLoader(banque);
        dataLoader.load("clients");
        dataLoader.load("comptes");


        new LoginFrame(banque);
    }
}