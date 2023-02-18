package DAO.FilesDAO;

import DAO.ClientsDAO;
import DAO.ComptesDAO;
import DAO.LogsDAO;
import Model.Banque;
import Model.Client;
import Model.Compte;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FilesHandler implements FilesBasePaths {

    public static void lockAndWrite(Path filePath, String content, boolean append) throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            FileLock lock = channel.lock();
            try {
                if (append) {
                    channel.position(channel.size());
                }
                channel.write(StandardCharsets.UTF_8.encode(content));
            } finally {
                lock.release();
            }
        }
    }

    public static void lockAndWriteList(Path filePath, List<String> lines, boolean append) throws IOException {
        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            FileLock lock = channel.lock();
            try {
                if (append) {
                    channel.position(channel.size());
                }

                for (String line : lines) {
                    channel.write(StandardCharsets.UTF_8.encode(line + System.lineSeparator()));
                }
            } finally {
                lock.release();
            }
        }
    }

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
            }
            if (!COMPTES_PATH.exists()) {
                if ((COMPTES_PATH.createNewFile())) {
                    System.out.println("Fichier " + COMPTES_PATH + " créé");
                } else {
                    System.out.println("Fichier " + COMPTES_PATH + " non créé");
                }
            }
            if (!LOGS_PATH.exists()) {
                if ((LOGS_PATH.createNewFile())) {
                    System.out.println("Fichier " + LOGS_PATH + " créé");
                } else {
                    System.out.println("Fichier " + LOGS_PATH + " non créé");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void createHeaders() {
        try {
            if (CLIENTS_PATH.length() == 0) {
                lockAndWrite(CLIENTS_PATH.toPath(), "id;nom;prenom;email;password;dateCreation"
                        + System.lineSeparator(), false);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (COMPTES_PATH.length() == 0) {
            try {
                lockAndWrite(COMPTES_PATH.toPath(), "id;idClient;solde;dateCreation"
                        + System.lineSeparator(), false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void seedClients() {
        try {
            List<String> seeds = List.of(
                    "1;1;1000;2023/02/13",
                    "2;1;2000;2023/02/12",
                    "3;2;3000;2023/02/11",
                    "4;2;4000;2023/02/10",
                    "5;3;5000;2023/02/09",
                    "6;3;6000;2023/02/08",
                    "7;4;7000;2023/02/07",
                    "8;4;8000;2023/02/06",
                    "9;5;9000;2023/02/05",
                    "10;5;10000;2023/02/04"
            );
            lockAndWriteList(COMPTES_PATH.toPath(), seeds, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void seedComptes() {
        try {
            List<String> seeds = List.of(
                    "1;Yassine;Laouina;client@test.com;123456;2023/02/13",
                    "2;John;Doe;johndoe@test.com;123456;2023/02/13",
                    "3;Jane;Doe;janedoe@test.com;123456;2023/02/13",
                    "4;Jack;Doe;jackdoe@test.com;123456;2023/02/13",
                    "5;Jill;Doe;jilldoe@test.com;123456;2023/02/13",
                    "6;Joe;Doe;joedoe@test.com;123456;2023/02/13"
            );
            lockAndWriteList(CLIENTS_PATH.toPath(), seeds, true);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(Banque banque) {
        FilesHandler.flush();
        FilesHandler.init();
        for (Client client : banque.getClients()) {
            ClientsDAO.writeClient(client);
            for (Compte compte : client.getComptes()) {
                ComptesDAO.writeCompte(compte, client.getId());
            }
        }
    }

    public static void drop() {
        try {
            Files.deleteIfExists(CLIENTS_PATH.toPath());
            Files.deleteIfExists(COMPTES_PATH.toPath());
            Files.deleteIfExists(LOGS_PATH.toPath());
            Files.deleteIfExists(BASE_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearLogs() {
        LogsDAO.clear();
    }

    public static void seed() {
        seedComptes();
        seedClients();
    }

    @SuppressWarnings("resource")
    public static void readLogs() {
        if (LOGS_PATH.exists()) {
            try {
                Files.lines(LOGS_PATH.toPath()).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}