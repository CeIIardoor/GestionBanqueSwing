package Service;


import Model.Banque;
import Model.Client;
import Model.Compte;

import java.util.Scanner;

public class ServiceCRUD {
    private static Banque banque;

    public ServiceCRUD(Banque _banque){
        banque = _banque;
    }

    public static Client getClientById(int id){
        for (Client client : banque.getClients()) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public void creerEtAjouterCompte(){
        System.out.println("Client existant ? (o/n)");
        Scanner clavier = new Scanner(System.in);
        String reponse = clavier.nextLine();
        if (reponse.equals("o")){
            System.out.println("Entrer l'id du Client : ");
            int idCompte = clavier.nextInt();
            Client client = getClientById(idCompte);
            if (client != null){
                System.out.println("Entrer le solde du compte : ");
                double solde = clavier.nextDouble();
                client.getComptes().add(new Compte(client,solde));
            }
            else {
                System.out.println("Client inexistant");
            }
        }
        else if (reponse.equals("n")){
            Client client = new Client();
            System.out.println("Entrer le solde initial du compte : ");
            double solde = clavier.nextDouble();
            client.getComptes().add(new Compte(client,solde));
            banque.getClients().add(client);
        }
        else {
            System.out.println("Réponse invalide");

        }
    }

    public void creerEtAjouterClient(){
        Client client = new Client();
        banque.ajouterClient(client);
        System.out.println("Client " + client.getId() + " créé et ajouté à " + banque.getNomAgence());
    }

    public static Compte getCompteById(int idCompte) {
        for (Compte compte : banque.getComptes()) {
            if (compte.getId() == idCompte) {
                return compte;
            }
        }
        return null;
    }

    public Compte chercherUnCompte(int idCompte){
        Compte compte = getCompteById(idCompte);
        if (compte != null){
            System.out.println("Compte " + compte.getId() + " trouvé");
            return compte;
        } else {
            System.out.println("Compte inexistant");
            return null;
        }
    }

    public Client chercherUnClient(int idClient){
        Client client = getClientById(idClient);
        if (client != null){
            System.out.println("Client " + client.getId() + " trouvé");
            return client;
        } else {
            System.out.println("Client inexistant");
            return null;
        }
    }

    public void consulterDetailCompte(int idCompte){
        Compte compte = chercherUnCompte(idCompte);
        if (compte != null){
            System.out.println("Détail du compte " + compte.getId() + " :");
            System.out.println(compte);
        } else {
            System.out.println("Compte inexistant");
        }
    }

    public void consulterDetailClient(int idClient){
        Client client = chercherUnClient(idClient);
        if (client != null){
            System.out.println("Détail du client " + client.getId() + " :");
            System.out.println(client);
        } else {
            System.out.println("Client inexistant");
        }
    }

    public void modifierCompte(int idCompte){
        Compte compteAModifier = getCompteById(idCompte);
        if (compteAModifier != null){
            do {
                System.out.println("Saisir le nouveau solde du compte " + compteAModifier.getId() + " :");
                double s = new Scanner(System.in).nextDouble();
                compteAModifier.setSolde(s);
            } while (compteAModifier.getSolde() < 0);
            System.out.println("Compte " + compteAModifier.getId() + " modifié");
        } else {
            System.out.println("Compte inexistant");
        }
    }

    public void modifierClient(int idClient){
        Client clientAModifier = getClientById(idClient);
        if (clientAModifier != null){
            Scanner sc = new Scanner(System.in);
            System.out.println("Saisir le nouveau nom du client " + clientAModifier.getId() + " :");
            clientAModifier.setNom(sc.nextLine());
            System.out.println("Saisir le nouveau prénom du client " + clientAModifier.getId() + " :");
            clientAModifier.setPrenom(sc.nextLine());
            do {
                System.out.println("Saisir le nouveau mail du client " + clientAModifier.getId() + " :");
                clientAModifier.setEmail(sc.nextLine());
            } while (!clientAModifier.getEmail().matches("^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\\.[a-z]{2,4}$"));
            System.out.println("Client " + clientAModifier.getId() + " modifié");
        } else {
            System.out.println("Client inexistant");
        }
    }

    public void supprimerCompte(int idCompte){
        Compte compte = getCompteById(idCompte);
        if (compte != null){
            banque.getComptes().remove(compte);
            System.out.println("Compte " + compte.getId() + " supprimé");
        } else {
            System.out.println("Compte inexistant");
        }
    }

    public void supprimerClient(int idClient){
        Client client = getClientById(idClient);
        if (client != null){
            client.getComptes().clear();
            banque.supprimerClient(client);
        } else {
            System.out.println("Client inexistant");
        }
    }

}