package DAO;

import DAO.FilesDAO.FilesBasePaths;
import Model.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientsDAO {

    public static ArrayList<Client> readClients() {
        File clientscsv = new File(FilesBasePaths.CLIENTS_PATH.toURI());
        ArrayList<Client> listeClients = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(clientscsv);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                int idClient = Integer.parseInt(data[0]);
                String nom = data[1];
                String prenom = data[2];
                String email = data[3];
                String dateAjout = data[4];
                Client client = new Client(nom, prenom, email);
                client.setIdClient(idClient);
                client.setDateAjout(dateAjout);
                listeClients.add(client);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return listeClients;
    }

    public static void writeClient(Client client) {
        File clientscsv = new File(FilesBasePaths.CLIENTS_PATH.toURI());
        try {
            FileWriter writer = new FileWriter(clientscsv, true);
            String sb = client.getIdClient() + ";" +
                    client.getNom() + ";" +
                    client.getPrenom() + ";" +
                    client.getEmail() + ";" +
                    client.getDateAjout() +
                    "\n";
            writer.write(sb);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}