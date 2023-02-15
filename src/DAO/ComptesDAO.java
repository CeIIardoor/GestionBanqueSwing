package DAO;

import DAO.FilesDAO.FilesBasePaths;
import Model.Compte;
import Service.ServiceCRUD;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ComptesDAO {
    public static ArrayList<Compte> readComptes() throws FileNotFoundException {
        File comptescsv = new File(FilesBasePaths.COMPTES_PATH.toURI());
        ArrayList<Compte> listeComptes = new ArrayList<>();
        Scanner scanner = new Scanner(comptescsv);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            int idCompte = Integer.parseInt(data[0]);
            int idClient = Integer.parseInt(data[1]);
            double solde = Double.parseDouble(data[2]);
            String dateCreation = data[3];
            Compte compte = new Compte(ServiceCRUD.getClientById(idClient), solde);
            compte.setIdCompte(idCompte);
            compte.setDateCreation(dateCreation);
            listeComptes.add(compte);
        }
        return listeComptes;
    }

    public static void writeCompte(Compte compte, int idClient) {
        File comptescsv = new File(FilesBasePaths.COMPTES_PATH.toURI());
        try {
            FileWriter writer = new FileWriter(comptescsv, true);
            String sb = compte.getIdCompte() + ";" +
                    idClient + ";" +
                    compte.getSolde() + ";" +
                    compte.getDateCreation() +
                    "\n";
            writer.write(sb);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}