package DAO.FilesDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class FilesHandler implements FilesBasePaths {
    static void createBasePath() {
        if (!BASE_FOLDER.toFile().exists()) {
            if (BASE_FOLDER.toFile().mkdir()) {
                System.out.println(BASE_FOLDER + " créé");
            } else {
                System.out.println(BASE_FOLDER + " création échouée");
            }
        }
    }

    static void createFiles() {
        try {
            if (!CLIENTS_PATH.exists()) {
                if ((CLIENTS_PATH.createNewFile())) {
                    System.out.println("Fichier " + CLIENTS_PATH + " créé");
                } else {
                    System.out.println("Fichier " + CLIENTS_PATH + " non créé");
                }
            } else {
                System.out.println("Fichier " + CLIENTS_PATH + " existe déjà");
            }
            if (!COMPTES_PATH.exists()) {
                if ((COMPTES_PATH.createNewFile())) {
                    System.out.println("Fichier " + COMPTES_PATH + " créé");
                } else {
                    System.out.println("Fichier " + COMPTES_PATH + " non créé");
                }
            } else {
                System.out.println("Fichier " + COMPTES_PATH + " existe déjà");
            }
            if (!LOGS_PATH.exists()) {
                if ((LOGS_PATH.createNewFile())) {
                    System.out.println("Fichier " + LOGS_PATH + " créé");
                } else {
                    System.out.println("Fichier " + LOGS_PATH + " non créé");
                }
            } else {
                System.out.println("Fichier " + LOGS_PATH + " existe déjà");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void createHeaders() {
        try {
            if (CLIENTS_PATH.length() == 0) {
                Files.write(CLIENTS_PATH.toPath(), ("idClient;nom;prenom;email;password;dateAjout\n").getBytes(), StandardOpenOption.APPEND);
            }
            if (COMPTES_PATH.length() == 0) {
                Files.write(COMPTES_PATH.toPath(), ("idCompte;idClient;solde;dateCreation\n").getBytes(), StandardOpenOption.APPEND);
            }
            if (LOGS_PATH.length() == 0) {
                Files.write(LOGS_PATH.toPath(), ("idLog;date;action;idClient\n").getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seedComptes() {
        try {
            Files.write(CLIENTS_PATH.toPath(), ("1;test;test;test@test.com;123456;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(CLIENTS_PATH.toPath(), ("2;John;Doe;johndoe@test.com;123456;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(CLIENTS_PATH.toPath(), ("3;Jane;Doe;janedoe@test.com;123456;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(CLIENTS_PATH.toPath(), ("4;Jack;Doe;jackdoe@test.com;123456;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seedClients() {
        try {
            Files.write(COMPTES_PATH.toPath(), ("1;1;1000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("2;1;2000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("3;2;3000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("4;2;4000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("5;3;5000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("6;3;6000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("7;3;7000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(COMPTES_PATH.toPath(), ("8;4;8000;2020-01-01\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        createBasePath();
        createFiles();
        createHeaders();
    }

    public static void flush() {
        try {
            Files.write(CLIENTS_PATH.toPath(), "".getBytes());
            Files.write(COMPTES_PATH.toPath(), "".getBytes());
            Files.write(LOGS_PATH.toPath(), "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seed() {
        seedComptes();
        seedClients();
    }
}