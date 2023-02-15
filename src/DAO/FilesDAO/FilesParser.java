package DAO.FilesDAO;

import DAO.ClientsDAO;
import DAO.ComptesDAO;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FilesParser {
    String fileType;

    public FilesParser(String fileType) {
        this.fileType = fileType;
    }

    public static ArrayList getData(String fileType) throws FileNotFoundException {
        ArrayList data = new ArrayList<>();
        switch (fileType) {
            case "clients":
                data = ClientsDAO.readClients();
                break;
            case "comptes":
                data = ComptesDAO.readComptes();
                break;
        }
        return data;
    }
}