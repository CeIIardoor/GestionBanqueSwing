package Swing;

import javax.swing.*;

public class MainFacade {
    public static void run() {
        JFrame frame = new JFrame("Dire Straits Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}