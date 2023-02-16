package View.Swing.Panels.Admin;

import DAO.LogsDAO;
import Model.Banque;
import Model.Client;
import View.Swing.Frames.Auth.LoginFrame;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JPanel {
    public AdminPanel(Banque banque) {

        setLayout(new BorderLayout());

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Dashboard Admin");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titlePanel.add(titleLabel);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4));
        JButton btnInfoBanque = new JButton("Afficher Infos Banque");
        StringBuilder infoBanque = new StringBuilder();
        infoBanque.append("****************************************\n")
                .append("Nom: ").append(banque.getNomAgence()).append("\n").append("Email: ").append(banque.getEmailAgence()).append("\n")
                .append("****************************************\n");
        for (Client client : banque.getClients()) {
            infoBanque.append("\n").append("Nom: ").append(client.getNom()).append("\n").append("Email: ")
                    .append(client.getEmail()).append("\n").append("Solde Total : ").append(client.getSoldeTotal())
                    .append("\n").append("Nombre de comptes: ").append(client.getComptes().size()).append("\n")
                    .append("****************************************\n");
        }
        btnInfoBanque.addActionListener(e -> {
            LogsDAO logsDAO = new LogsDAO();
            LogsDAO.write("Affichage des infos de la banque");
            JOptionPane.showMessageDialog(null, infoBanque, "Infos Banque", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(btnInfoBanque);
        JButton btnInfoClients = new JButton("Afficher Infos Clients");
        buttonPanel.add(btnInfoClients);
        JButton btnInfoComptes = new JButton("Afficher Infos Comptes");
        buttonPanel.add(btnInfoComptes);
        JButton btnServiceCrud = new JButton("Service CRUD");
        buttonPanel.add(btnServiceCrud);
        JButton btnServiceTransactionnel = new JButton("Service Transactionnel");
        buttonPanel.add(btnServiceTransactionnel);
        JButton btnServiceUtilitaire = new JButton("Service Utilitaire");
        buttonPanel.add(btnServiceUtilitaire);

        // Create a panel for the logout button
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(0x8BFF1E1E));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Roboto", Font.BOLD, 16));
        logoutButton.setPreferredSize(new Dimension(150, 50));
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(e -> {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                new LoginFrame(banque);
                (SwingUtilities.getWindowAncestor(this)).dispose();
                LogsDAO.write("_______________________________________________________Fin Session");
            }
        });
        logoutPanel.add(logoutButton);

        // Add the panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

}