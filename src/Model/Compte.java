package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Compte {
    public static int cmpCompte = 1;
    private int idCompte;
    private double solde;
    private String dateCreation;
    private final ArrayList<String> journalisation;
    private final Client proprietaire;

    public int getId() {
        return idCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public ArrayList<String> getJournalisation() {
        return journalisation;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public Compte(Client proprietaire, double solde){
        idCompte = cmpCompte++;
        this.solde = solde;
        dateCreation = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        journalisation = new ArrayList<>();
        journalisation.add("Création du compte le " + dateCreation);
        if(solde != 0){
            journalisation.add("Dépôt de " + solde + "dh");
        }
        this.proprietaire = proprietaire;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = new SimpleDateFormat(dateCreation).format(new Date());
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

    public void setId(int idCompte) {
        this.idCompte = idCompte;
    }

    @Override
    public String toString() {
        return "Compte[" + '\n' +
                "+ idCompte=" + idCompte + '\n' +
                "- solde=" + solde + '\n' +
                "- dateCreation= " + dateCreation + '\n' +
                "- proprietaire= {" + proprietaire.getId() + "}- " + proprietaire.getNom() + '\n' +
                ']';
    }
}