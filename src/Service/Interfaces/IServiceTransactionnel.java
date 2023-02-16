package Service.Interfaces;

import Model.Compte;

public interface IServiceTransactionnel {
    boolean deposer(double montant, Compte c);

    boolean retirer(double montant, Compte c);

    boolean effectuerVirement(double montant, Compte src, Compte des);
}