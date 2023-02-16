package View.Swing.Frames;

import Model.Banque;

import javax.swing.*;

public class AdminFrame extends JFrame {
    public AdminFrame(Banque banque) {
        super("Admin");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}