package DAO;

import DAO.FilesDAO.FilesBasePaths;
import Model.Banque;
import Model.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientsDAO {

    public static void loadClients(Banque banque) throws FileNotFoundException {
        File clientscsv = new File(FilesBasePaths.CLIENTS_PATH.toURI());
        ArrayList<Client> listeClients = new ArrayList<>();
        Scanner scanner = new Scanner(clientscsv);
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] data = line.split(";");
            System.out.println(data[0]);
            int idClient = Integer.parseInt(data[0]);
            String nom = data[1];
            String prenom = data[2];
            String email = data[3];
            String password = data[4];
            String dateAjout = data[5];
            Client client = new Client(nom, prenom, email);
            client.setIdClient(idClient);
            client.setDateAjout(dateAjout);
            client.setPassword(password);
            listeClients.add(client);
        }
        for (Client client : listeClients) {
            banque.ajouterClient(client);
        }
    }

    public static void writeClient(Client client) {
        File clientscsv = new File(FilesBasePaths.CLIENTS_PATH.toURI());
        try {
            FileWriter writer = new FileWriter(clientscsv, true);
            String sb = client.getIdClient() + ";" +
                    client.getNom() + ";" +
                    client.getPrenom() + ";" +
                    client.getEmail() + ";" +
                    client.getPassword() + ";" +
                    client.getDateAjout() +
                    "\n";
            writer.write(sb);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}