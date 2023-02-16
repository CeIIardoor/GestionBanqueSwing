import DAO.FilesDAO.FilesHandler;
import View.Swing.SwingFacade;

import javax.swing.*;
import java.io.FileNotFoundException;

public class SwingApp {
    public static void main(String[] args) throws FileNotFoundException {
//      FilesHandler.flush(); // Pour vider la base de données
        if (!FilesHandler.BASE_FOLDER.toFile().exists()) { // Si le dossier DB n'existe pas
            FilesHandler.init();
            FilesHandler.seed();
        }
        SwingUtilities.invokeLater(SwingFacade::run);
    }
}