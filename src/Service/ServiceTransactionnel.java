package Service;

import DAO.LogsDAO;
import Model.Banque;
import Model.Compte;
import Service.Interfaces.IServiceTransactionnel;

public class ServiceTransactionnel implements IServiceTransactionnel {
    private static Banque banque;

    public ServiceTransactionnel(Banque _banque){
        banque = _banque;
    }

    public boolean deposer(double montant, Compte c) {
        if (montant > 0) {
            c.setSolde(c.getSolde() + montant);
            c.getJournalisation().add("Versement de " + montant + "dh");
            System.out.println("Le montant de " + montant + " a été versé sur le compte " + c.getId());
            LogsDAO.write("Le montant de " + montant + " a été versé sur le compte " + c.getId());
            return true;
        } else {
            System.out.println("Le montant doit être supérieur à 0");
            return false;
        }
    }

    public boolean retirer(double montant, Compte c) {
        if (montant > 0) {
            if (c.getSolde() >= montant) {
                c.setSolde(c.getSolde() - montant);
                c.getJournalisation().add("Retrait de " + montant + "dh");
                System.out.println("Le montant de " + montant + " a été retiré du compte " + c.getId());
                LogsDAO.write("Le montant de " + montant + " a été retiré du compte " + c.getId());
                return true;
            } else {
                System.out.println("Le montant à retirer est supérieur au solde du compte");
                return false;
            }
        } else {
            System.out.println("Le montant doit être supérieur à 0");
            return false;
        }
    }

    public boolean effectuerVirement(double montant, Compte src, Compte des) {
        if (montant > 0) {
            if (src.getSolde() >= montant) {
                src.setSolde(src.getSolde() - montant);
                des.setSolde(des.getSolde() + montant);
                src.getJournalisation().add("Virement de " + montant + "dh vers le compte " + des.getId());
                des.getJournalisation().add("Virement de " + montant + "dh du compte " + src.getId());
                System.out.println("Le montant de " + montant + " a été viré du compte " + src.getId() + " vers le compte " + des.getId());
                LogsDAO.write("Le montant de " + montant + " a été viré du compte " + src.getId() + " vers le compte " + des.getId());
                return true;
            } else {
                System.out.println("Le montant à virer est supérieur au solde du compte");
                return false;
            }
        } else {
            System.out.println("Le montant doit être supérieur à 0");
            return false;
        }
    }
}