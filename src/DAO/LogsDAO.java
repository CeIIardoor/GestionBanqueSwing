package DAO;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static DAO.FilesDAO.FilesBasePaths.LOGS_PATH;

public class LogsDAO {
    public static void write(String message) {
        try {
            Files.write(LOGS_PATH.toPath(), (message + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}