import View.Swing.SwingFacade;

import java.io.FileNotFoundException;

public class SwingApp {
    public static void main(String[] args) {
        try {
            SwingFacade.run();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}