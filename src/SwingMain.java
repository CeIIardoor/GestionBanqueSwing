import Swing.MainFacade;

import javax.swing.*;

public class SwingMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFacade::run);
    }
}