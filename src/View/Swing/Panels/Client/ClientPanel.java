package View.Swing.Panels.Client;

import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import Model.Client;
import Model.Compte;
import Service.ServiceTransactionnel;
import View.Swing.Frames.Auth.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ClientPanel extends JPanel {
    public ClientPanel(Banque banque, Client client) {

        setLayout(new BorderLayout());

        // Create a panel for the title
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel(" | " + "ID : " + client.getId() + " | Client : " + client.getNom() + " " + client.getPrenom()
                + " | Solde Total : " + client.getSoldeTotal() + "MAD");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titlePanel.add(titleLabel);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));

        JButton btnConsulerMesComptes = new JButton("Consulter mes comptes");
        btnConsulerMesComptes.setFocusPainted(false);
        StringBuilder infoJournalisation = new StringBuilder();
        infoJournalisation.append("****************************************");
        for (Compte compte : client.getComptes()) {
            infoJournalisation.append("\n").append("<>ID Compte : ").append(compte.getId()).append("\n");
            infoJournalisation.append("Solde : ").append(compte.getSolde()).append("MAD").append("\n");
            infoJournalisation.append("Journalisation : ").append("\n");
            for (int i = 0; i < compte.getJournalisation().size(); i++) {
                infoJournalisation.append("     ").append(compte.getJournalisation().get(i)).append("\n");
            }
            infoJournalisation.append("****************************************");
        }
        btnConsulerMesComptes.addActionListener(e -> {
            LogsDAO.write("Ouverture de la fenêtre de consultation des opérations du client " + client.getNom() + " " + client.getPrenom());
            JOptionPane.showMessageDialog(null, infoJournalisation, "Infos Opérations", JOptionPane.INFORMATION_MESSAGE);
        });
        buttonPanel.add(btnConsulerMesComptes);

        JButton btnRetirerArgent = new JButton("Retirer de l'argent");
        btnRetirerArgent.setFocusPainted(false);
        btnRetirerArgent.addActionListener(e -> {
            //option pane with number input and account combo box
            String[] accounts = new String[client.getComptes().size()];
            for (int i = 0; i < client.getComptes().size(); i++) {
                accounts[i] = "<>ID Compte : " + client.getComptes().get(i).getId() +
                        " Solde : " + client.getComptes().get(i).getSolde() + "MAD";
            }
            JComboBox<String> comboBox = new JComboBox<>(accounts);
            JTextField textField = new JTextField();
            Object[] message = {
                    "Compte :", comboBox,
                    "Montant :", textField
            };
            int option = JOptionPane.showConfirmDialog(null, message,
                    "Retrait d'argent", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int idCompte = Integer.parseInt(Objects.requireNonNull(comboBox.getSelectedItem()).toString().split(" ")[3]);
                double montant = Double.parseDouble(textField.getText());
                if (ServiceTransactionnel.retirer(montant, client.getCompteByID(idCompte))) {

                    JOptionPane.showMessageDialog(null, "Retrait effectué avec succès",
                            "Retrait d'argent", JOptionPane.INFORMATION_MESSAGE);
                    LogsDAO.write("Retrait de " + montant + "MAD du compte " + idCompte);
                    FilesHandler.save(banque);
                    ClientPanel.this.removeAll();
                    ClientPanel.this.add(new ClientPanel(banque, client));
                    ClientPanel.this.revalidate();
                    ClientPanel.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Retrait échoué",
                            "Retrait d'argent", JOptionPane.ERROR_MESSAGE);
                    LogsDAO.write("Retrait de " + montant + "MAD du compte " + idCompte + " a échoué");
                }
            }
        });
        buttonPanel.add(btnRetirerArgent);

        JButton btnDeposerArgent = new JButton("Déposer de l'argent");
        btnDeposerArgent.setFocusPainted(false);
        btnDeposerArgent.addActionListener(e -> {
            //option pane with number input and account combo box
            String[] accounts = new String[client.getComptes().size()];
            for (int i = 0; i < client.getComptes().size(); i++) {
                accounts[i] = "<>ID Compte : " + client.getComptes().get(i).getId() +
                        " Solde : " + client.getComptes().get(i).getSolde() + "MAD";
            }
            JComboBox<String> comboBox = new JComboBox<>(accounts);
            JTextField textField = new JTextField();
            Object[] message = {
                    "Compte :", comboBox,
                    "Montant :", textField
            };
            int option = JOptionPane.showConfirmDialog(null, message,
                    "Dépôt d'argent", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int idCompte = Integer.parseInt(Objects.requireNonNull(comboBox.getSelectedItem()).toString().split(" ")[3]);
                double montant = Double.parseDouble(textField.getText());
                if (ServiceTransactionnel.deposer(montant, client.getCompteByID(idCompte))) {
                    JOptionPane.showMessageDialog(null, "Dépôt effectué avec succès",
                            "Dépôt d'argent", JOptionPane.INFORMATION_MESSAGE);
                    LogsDAO.write("Dépôt de " + montant + "MAD au compte " + idCompte);
                    FilesHandler.save(banque);
                    ClientPanel.this.removeAll();
                    ClientPanel.this.add(new ClientPanel(banque, client));
                    ClientPanel.this.revalidate();
                    ClientPanel.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Dépôt échoué",
                            "Dépôt d'argent", JOptionPane.ERROR_MESSAGE);
                    LogsDAO.write("Dépôt de " + montant + "MAD au compte " + idCompte + " a échoué");
                }
            }
        });
        buttonPanel.add(btnDeposerArgent);

        JButton btnVirementArgent = new JButton("Effectuer un virement");
        btnVirementArgent.setFocusPainted(false);
        btnVirementArgent.addActionListener(e -> {
            //option pane with number input and account combo box
            String[] comptesClient = new String[client.getComptes().size()];
            for (int i = 0; i < client.getComptes().size(); i++) {
                comptesClient[i] = "<>ID Compte : " + client.getComptes().get(i).getId() +
                        " Solde : " + client.getComptes().get(i).getSolde() + "MAD";
            }
            String[] comptesBanque = new String[banque.getComptes().size()];
            for (int i = 0; i < banque.getComptes().size(); i++) {
                comptesBanque[i] = "<>ID Compte : " + banque.getComptes().get(i).getId() +
                        " Propriétaire : " + banque.getComptes().get(i).getProprietaire().getNom() +
                        " " + banque.getComptes().get(i).getProprietaire().getPrenom();
            }
            JComboBox<String> comboBoxClient = new JComboBox<>(comptesClient);
            JComboBox<String> comboBoxBanque = new JComboBox<>(comptesBanque);
            JTextField textField = new JTextField();
            Object[] message = {
                    "Compte client :", comboBoxClient,
                    "Compte banque :", comboBoxBanque,
                    "Montant :", textField
            };
            int option = JOptionPane.showConfirmDialog(null, message,
                    "Virement d'argent", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int idCompteClient = Integer.parseInt(Objects.requireNonNull(comboBoxClient.getSelectedItem()).toString().split(" ")[3]);
                int idCompteBanque = Integer.parseInt(Objects.requireNonNull(comboBoxBanque.getSelectedItem()).toString().split(" ")[3]);
                double montant = Double.parseDouble(textField.getText());
                if (montant <= 0) {
                    JOptionPane.showMessageDialog(null, "Montant invalide",
                            "Virement d'argent", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (montant > client.getCompteByID(idCompteClient).getSolde()) {
                    JOptionPane.showMessageDialog(null, "Solde insuffisant",
                            "Virement d'argent", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (idCompteClient == idCompteBanque) {
                    JOptionPane.showMessageDialog(null, "Virement impossible",
                            "Virement d'argent", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (ServiceTransactionnel.verser(montant, client.getCompteByID(idCompteClient), banque.getCompteById(idCompteBanque))) {
                    JOptionPane.showMessageDialog(null, "Virement effectué avec succès",
                            "Virement d'argent", JOptionPane.INFORMATION_MESSAGE);
                    LogsDAO.write("Virement de " + montant + "MAD du compte " + idCompteClient + " au compte " + idCompteBanque);
                    FilesHandler.save(banque);
                    ClientPanel.this.removeAll();
                    ClientPanel.this.add(new ClientPanel(banque, client));
                    ClientPanel.this.revalidate();
                    ClientPanel.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Virement échoué",
                            "Virement d'argent", JOptionPane.ERROR_MESSAGE);
                    LogsDAO.write("Virement de " + montant + "MAD du compte " + idCompteClient + " au compte " + idCompteBanque + " a échoué");
                }
            }
        });
        buttonPanel.add(btnVirementArgent);

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
            new LoginFrame(banque);
            (SwingUtilities.getWindowAncestor(this)).dispose();
            LogsDAO.write("_______________________________________________________Fin Session");
        });
        logoutPanel.add(logoutButton);

        // Add the panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(logoutPanel, BorderLayout.SOUTH);

        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
}