package View.Swing.Panels.Admin;

import Model.Banque;

import javax.swing.*;
import java.awt.*;

public class TransactionnelPanel extends JPanel {
    public TransactionnelPanel(Banque banque) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Set margin

        // Add title label to the north
        JLabel titleLabel = new JLabel("My Title");
        add(titleLabel, BorderLayout.NORTH);

        // Add three large buttons to the center
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Set background to be transparent
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10); // Set padding
        JButton btnRetrait = new JButton("Retrait");
        btnRetrait.setPreferredSize(new Dimension(100, 50));
        btnRetrait.setFocusPainted(false);
        buttonPanel.add(btnRetrait, c);
        c.gridx = 1;
        JButton btnDepot = new JButton("Depot");
        btnDepot.setPreferredSize(new Dimension(100, 50));
        btnDepot.setFocusPainted(false);
        buttonPanel.add(btnDepot, c);
        c.gridx = 2;
        JButton btnVirement = new JButton("Virement");
        btnVirement.setPreferredSize(new Dimension(100, 50));
        btnVirement.setFocusPainted(false);
        buttonPanel.add(btnVirement, c);
        add(buttonPanel, BorderLayout.CENTER);

        // Add back button to the south
        JButton backButton = new JButton("Retour");
        backButton.setBackground(new Color(0x1E90FF));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Roboto", Font.BOLD, 16));
        backButton.setPreferredSize(new Dimension(150, 50));
        backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setFocusPainted(false);
        add(backButton, BorderLayout.SOUTH);
    }
}