package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Banque{
    private static int cmpBanques = 1;
    private final int idBanque;
    private final String nomAgence;
    private String email;
    private int maxClients;
    private final ArrayList<Client> clients;

    public String getNomAgence() {
        return nomAgence;
    }

    public String getEmailAgence() {
        return email;
    }

    public ArrayList<Compte> getComptes() {
        ArrayList<Compte> comptes = new ArrayList<>();
        for (Client client : clients) {
            comptes.addAll(client.getComptes());
        }
        return comptes;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Banque(){
        this.idBanque = cmpBanques++;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer le nom de l'agence");
        this.nomAgence = sc.nextLine();
        do {
            System.out.println("Entrer l'email de l'agence");
            this.email = sc.nextLine();
        } while (!this.email.matches("^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$"));
        do {
            System.out.println("Entrer le nombre max de clients (>=10) :");
            this.maxClients = sc.nextInt();
        } while (this.maxClients < 10);
        this.clients = new ArrayList<>(maxClients);
    }
    public Banque(String nomAgence, String email, int maxClients) {
        idBanque = cmpBanques++;
        this.nomAgence = nomAgence;
        this.email = email;
        this.maxClients = maxClients;
        this.clients = new ArrayList<>(maxClients);
    }

    public String toString() {
        return "Banque [idBanque=" + idBanque + ", nomAgence=" + nomAgence + ", email=" + email
                +  ", maxClients=" + maxClients  + ", clients="
                + clients + "]";
    }

    public boolean equals(Object autreBanque) {
        if (this == autreBanque)
            return true;
        if (autreBanque == null)
            return false;
        if (getClass() != autreBanque.getClass())
            return false;
        Banque other = (Banque) autreBanque;
        return idBanque == other.idBanque && maxClients == other.maxClients
                && Objects.equals(clients, other.clients)
                && Objects.equals(email, other.email) && Objects.equals(nomAgence, other.nomAgence);
    }

    public void ajouterClient(Client client){
        if (this.clients.size() < this.maxClients){
            this.clients.add(client);
//            System.out.println("Client " + client.getId() + " ajouté avec succès");
        } else {
            System.out.println("Impossible d'ajouter le client " + client.getId() + " : nombre max de clients atteint");
        }
    }

    public void supprimerClient(Client client) {
        if (this.clients.contains(client)) {
            this.clients.remove(client);
            System.out.println("Client " + client.getId() + " supprimé avec succès");
        } else {
            System.out.println("Impossible de supprimer le client " + client.getId());
        }
    }

    public Compte getCompteById(int numCompte) {
        for (Client client : clients) {
            for (Compte compte : client.getComptes()) {
                if (compte.getIdCompte() == numCompte) {
                    return compte;
                }
            }
        }
        return null;
    }

    public Client getClientById(int idClient) {
        for (Client client : clients) {
            if (client.getId() == idClient) {
                return client;
            }
        }
        return null;
    }
}