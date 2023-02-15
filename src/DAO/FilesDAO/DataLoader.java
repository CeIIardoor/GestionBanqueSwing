package DAO.FilesDAO;

import DAO.ClientsDAO;
import DAO.ComptesDAO;
import Model.Banque;

import java.io.FileNotFoundException;

public class DataLoader {
    Banque banque;

    public DataLoader(Banque banque) {
        this.banque = banque;
    }

    public void load(String fileType) throws FileNotFoundException {
        switch (fileType) {
            case "clients":
                ClientsDAO.loadClients(banque);
                return;
            case "comptes":
                ComptesDAO.loadComptes(banque);
                return;
        }
        System.out.println("Error: FilesReader.getData() switch statement failed");
    }
}