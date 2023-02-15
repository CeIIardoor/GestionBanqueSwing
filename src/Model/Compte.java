package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Compte {
    /*
    Écrire une classe Compte définissant un compte bancaire :
    Attributs : idCompte, solde, date de création, tableau de
    journalisation et un propriétaire (Client)
    Accès : getters, setters
    (Solde initiale doit être positif)
    (id doit être auto-généré)
    (la journalisation de la création du compte est stocké dès
    l’initialisation du compte, ainsi que le dépôt du solde
    initiale si ce n'est pas 0dh)
    Méthodes : public String toString(),
    public boolean equals(Object autreCompte)
     */
    public static int cmpCompte = 1;
    private int idCompte;
    private double solde;
    private Date dateCreation;
    private final ArrayList<String> journalisation;
    private Client proprietaire;

    public int getIdCompte() {
        return idCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public ArrayList<String> getJournalisation() {
        return journalisation;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Compte(){
        idCompte = cmpCompte++;
        do{
            System.out.println("Entrer le solde initial du compte " + idCompte + " : ");
            solde = new Scanner(System.in).nextDouble();
            System.out.println(solde);
            if(solde < 0){
                System.out.println("Le solde doit être un nombre positif, veuillez réessayer : ");
            }
        }while(solde < 0);
        dateCreation = new Date();
        journalisation = new ArrayList<String>();
        journalisation.add("Création du compte le " + dateCreation);
        if(solde != 0){
            journalisation.add("Dépôt de " + solde + "dh");
        }
    }

    public Compte(Client proprietaire){
        idCompte = cmpCompte++;
        do {
            System.out.println("Entrer le solde initial du compte " + idCompte + " : ");
            solde = new Scanner(System.in).nextDouble();
            if (solde < 0) {
                System.out.println("Le solde doit être positif, veuillez réessayer : ");
            }
        } while (solde < 0);
        dateCreation = new Date();
        journalisation = new ArrayList<String>();
        journalisation.add("Création du compte le " + dateCreation);
        if(solde != 0){
            journalisation.add("Dépôt de " + solde + "dh");
        }
        this.proprietaire.getComptes().add(this);
    }

    public Compte(Client proprietaire, double solde){
        idCompte = cmpCompte++;
        this.solde = solde;
        dateCreation = new Date();
        journalisation = new ArrayList<>();
        journalisation.add("Création du compte le " + dateCreation);
        if(solde != 0){
            journalisation.add("Dépôt de " + solde + "dh");
        }
        this.proprietaire = proprietaire;
    }

    @Override
    public String toString() {
        return "Compte[" + '\n' +
                "+ idCompte=" + idCompte + '\n' +
                "- solde=" + solde + '\n' +
                "- dateCreation= " + dateCreation.getDate() + "/" + (dateCreation.getMonth() + 1) + "/" + (dateCreation.getYear() + 1900) + '\n' +
                "- proprietaire= " + proprietaire.getIdClient() + "- " + proprietaire.getNom() + '\n' +
                ']';
    }

    @Override
    public boolean equals(Object autreCompte) {
        if (this == autreCompte) return true;
        if (autreCompte == null || getClass() != autreCompte.getClass()) return false;

        Compte c = (Compte) autreCompte;
        return dateCreation.equals(c.dateCreation)
                && journalisation.equals(c.journalisation)
                && proprietaire.equals(c.proprietaire);
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = new Date(dateCreation);
    }
}