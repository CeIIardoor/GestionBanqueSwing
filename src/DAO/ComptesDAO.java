package DAO;

import DAO.FilesDAO.FilesBasePaths;
import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import Model.Client;
import Model.Compte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ComptesDAO {
    public static void loadComptes(Banque banque) throws FileNotFoundException {
        File comptescsv = new File(FilesBasePaths.COMPTES_PATH.toURI());
        Scanner scanner = new Scanner(comptescsv);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            int idCompte = Integer.parseInt(data[0]);
            int idClient = Integer.parseInt(data[1]);
            double solde = Double.parseDouble(data[2]);
            String dateCreation = data[3];
            Client proprietaire = banque.getClientById(idClient);
            Compte compte = new Compte(proprietaire, solde);
            compte.setId(idCompte);
            compte.setDateCreation(dateCreation);
            proprietaire.ajouterCompte(compte);
        }
    }

    public static void writeCompte(Compte compte, int idClient) {
        try {
            String sb = compte.getId() + ";" +
                    idClient + ";" +
                    compte.getSolde() + ";" +
                    compte.getDateCreation() +
                    System.lineSeparator();
            FilesHandler.lockAndWrite(FilesBasePaths.COMPTES_PATH.toPath(), sb, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}