import Controller.AuthController;
import DAO.ClientsDAO;
import DAO.ComptesDAO;
import DAO.FilesDAO.DataLoader;
import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import Model.Client;
import Model.Compte;
import Model.User;
import View.Console.MenuAdmin;
import View.Console.MenuAuth;
import View.Console.MenuClient;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("____________SETUP____________");
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

//        System.out.println("_____________________________________");

        AuthController authController = new AuthController(banque);

        int choix;
        do {
            MenuAuth.afficherMenuAuth();
            choix = new Scanner(System.in).nextInt();
            if (choix == 1) {
                System.out.println("Entrer votre login");
                String login = new Scanner(System.in).nextLine();
                System.out.println("Entrer votre mot de passe");
                String password = new Scanner(System.in).nextLine();
                User currentUser = authController.authenticate(login, password);
                if (currentUser != null && currentUser.getRole().equals("admin")) {
                    LogsDAO.write("Admin " + currentUser.getLogin() + " s'est connecté");
                    MenuAdmin menuAdmin = new MenuAdmin(banque);
                    menuAdmin.afficherMenuPrincipale();
                } else if (currentUser != null && currentUser.getRole().equals("client")) {
                    LogsDAO.write("Client " + currentUser.getLogin() + " s'est connecté");
                    MenuClient menuClient = new MenuClient(banque, currentUser);
                    menuClient.afficherMenuPrincipale();
                } else {
                    System.out.println("Login ou mot de passe incorrect");
                }
            } else if (choix == 2) {
                System.out.println("******************************************************");
                System.out.println("Affichage des logs");
                System.out.println("******************************************************");
                if (FilesHandler.LOGS_PATH.length() == 0) {
                    System.out.println("Aucun log");
                } else {
                    FilesHandler.readLogs();
                }
                System.out.println("******************************************************");

            } else if (choix == 3) {
                FilesHandler.flushLogs();
                System.out.println("Logs vidés");

            } else if (choix == 9) {
                FilesHandler.flush();
                FilesHandler.init();
                for (Client client : banque.getClients()) {
                    ClientsDAO.writeClient(client);
                    for (Compte compte : client.getComptes()) {
                        ComptesDAO.writeCompte(compte, client.getId());
                    }
                }
                LogsDAO.write("_______________________________________________________Fin Session");
                System.out.println("Au revoir");
            } else {
                System.out.println("Choix invalide");
            }
        } while (choix != 9);
    }
}