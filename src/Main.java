import Controller.AuthController;
import DAO.FilesDAO.DataLoader;
import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import Model.User;
import View.MenuAdmin;
import View.MenuAuth;
import View.MenuClient;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("____________SETUP____________");
        Banque banque = new Banque("Dire Straits Bank", "direstraits@banque.com", 100);
        FilesHandler.flush();
        FilesHandler.init();
        FilesHandler.seed();

        DataLoader dataLoader = new DataLoader(banque);
        dataLoader.load("clients");
        dataLoader.load("comptes");
        System.out.println(banque.getClients());
        System.out.println(banque.getComptes());
        System.out.println("_____________________________________");

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
                    MenuAdmin menuAdmin = new MenuAdmin(banque);
                    menuAdmin.afficherMenuPrincipale();
                } else if (currentUser != null && currentUser.getRole().equals("client")) {
                    MenuClient menuClient = new MenuClient(banque, currentUser);
                    menuClient.afficherMenuPrincipale();
                } else {
                System.out.println("Login ou mot de passe incorrect");
                }
            } else if (choix == 9) {
                System.out.println("Au revoir");
            } else {
                System.out.println("Choix invalide");
            }
        } while (choix != 9);
    }
}