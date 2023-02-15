package DAO.FilesDAO;

import java.io.File;
import java.nio.file.Path;

public interface FilesBasePaths {
    Path BASE_FOLDER = new File("src\\DB\\").toPath();
    File CLIENTS_PATH = new File(BASE_FOLDER + "\\clients.csv");
    File COMPTES_PATH = new File(BASE_FOLDER + "\\comptes.csv");
    File LOGS_PATH = new File(BASE_FOLDER + "\\logs.txt");
}