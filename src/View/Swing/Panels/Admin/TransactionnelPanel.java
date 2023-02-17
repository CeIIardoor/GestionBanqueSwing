package View.Swing.Panels.Admin;

import DAO.FilesDAO.FilesHandler;
import DAO.LogsDAO;
import Model.Banque;
import Model.Compte;
import Service.ServiceTransactionnel;
import View.Swing.Frames.Admin.AdminFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class TransactionnelPanel extends JPanel {
    public TransactionnelPanel(Banque banque) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Set margin

        // Add title label to the north
        JLabel titleLabel = new JLabel("Service Transactionnel");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
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
        btnRetrait.addActionListener(e -> {
            ArrayList<Compte> comptes = banque.getComptes();
            String[] accountStrings = new String[comptes.size()];
            for (int i = 0; i < comptes.size(); i++) {
                accountStrings[i] = "<>ID Compte : " + comptes.get(i).getId() + " -  Propriétaire : "
                        + comptes.get(i).getProprietaire().getNom() + " " + comptes.get(i).getProprietaire().getPrenom();
            }
            JComboBox<String> account = new JComboBox<>(accountStrings);
            JTextField amount = new JTextField(10);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Compte:"));
            panel.add(account);
            panel.add(new JLabel("Montant:"));
            panel.add(amount);
            int result = JOptionPane.showConfirmDialog(null, panel, "Effectuer un retrait",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (amount.getText() == null || amount.getText().equals("") || Double.parseDouble(amount.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Le montant doit être un nombre positif",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    int id = Integer.parseInt(account.getSelectedItem().toString().split(" ")[3]);
                    double amountValue = Double.parseDouble(amount.getText());
                    ServiceTransactionnel serviceTransactionnel = new ServiceTransactionnel(banque);
                    if (serviceTransactionnel.retirer(amountValue, banque.getCompteById(id))) {
                        JOptionPane.showMessageDialog(null, "Retrait effectué avec succès",
                                "Succès", JOptionPane.INFORMATION_MESSAGE);
                        FilesHandler.save(banque);
                        LogsDAO.write("Retrait effectué sur le compte " + id + " pour un montant de " + amountValue);
                    } else {
                        JOptionPane.showMessageDialog(null, "Solde insuffisant",
                                "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPanel.add(btnRetrait, c);
        c.gridx = 1;
        JButton btnDepot = new JButton("Depot");
        btnDepot.setPreferredSize(new Dimension(100, 50));
        btnDepot.setFocusPainted(false);
        btnDepot.addActionListener(e -> {
            ArrayList<Compte> comptes = banque.getComptes();
            String[] accountStrings = new String[comptes.size()];
            for (int i = 0; i < comptes.size(); i++) {
                accountStrings[i] = "<>ID Compte : " + comptes.get(i).getId() + " -  Propriétaire : "
                        + comptes.get(i).getProprietaire().getNom() + " " + comptes.get(i).getProprietaire().getPrenom();
            }
            JComboBox<String> account = new JComboBox<>(accountStrings);
            JTextField amount = new JTextField(10);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Compte:"));
            panel.add(account);
            panel.add(new JLabel("Montant:"));
            panel.add(amount);
            int result = JOptionPane.showConfirmDialog(null, panel, "Effectuer un depot",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (amount.getText() == null || amount.getText().equals("") || Double.parseDouble(amount.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Le montant doit être un nombre positif",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double amountValue = Double.parseDouble(amount.getText());
                int id = Integer.parseInt(((String) Objects.requireNonNull(account.getSelectedItem())).split(" ")[3]);
                ServiceTransactionnel serviceTransactionnel = new ServiceTransactionnel(banque);
                serviceTransactionnel.deposer(amountValue, banque.getCompteById(id));
                FilesHandler.save(banque);
                LogsDAO.write("Depot de " + amountValue + "MAD sur le compte " + id);
                JOptionPane.showMessageDialog(null, "Le depot de " +
                                amountValue + "MAD sur le compte " + id + " a été effectué avec succès", "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonPanel.add(btnDepot, c);
        c.gridx = 2;
        JButton btnVirement = new JButton("Virement");
        btnVirement.setPreferredSize(new Dimension(100, 50));
        btnVirement.setFocusPainted(false);
        btnVirement.addActionListener(e -> {
            ArrayList<Compte> comptes = banque.getComptes();
            String[] accountStrings = new String[comptes.size()];
            for (int i = 0; i < comptes.size(); i++) {
                accountStrings[i] = "<>ID Compte : " + comptes.get(i).getId() + " -  Propriétaire : "
                        + comptes.get(i).getProprietaire().getNom() + " " + comptes.get(i).getProprietaire().getPrenom();
            }
            JComboBox<String> fromAccount = new JComboBox<>(accountStrings);
            JComboBox<String> toAccount = new JComboBox<>(accountStrings);
            JTextField amount = new JTextField(10);
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Compte Source:"));
            panel.add(fromAccount);
            panel.add(new JLabel("Compte Destinataire:"));
            panel.add(toAccount);
            panel.add(new JLabel("Montant:"));
            panel.add(amount);
            int result = JOptionPane.showConfirmDialog(null, panel, "Effectuer un virement",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (Objects.equals(fromAccount.getSelectedItem(), toAccount.getSelectedItem())) {
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas effectuer un virement" +
                                    " sur le même compte",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String from = (String) fromAccount.getSelectedItem();
                String to = (String) toAccount.getSelectedItem();
                if (amount.getText() == null || amount.getText().equals("") || Double.parseDouble(amount.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "Le montant doit être > de 0", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double amountValue = Double.parseDouble(amount.getText());
                ServiceTransactionnel st = new ServiceTransactionnel(banque);
                if (from == null || to == null) {
                    JOptionPane.showMessageDialog(null, "Merci de ne pas injecter des valeurs nulles",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                st.effectuerVirement(amountValue,
                        banque.getCompteById(Integer.parseInt((Objects.requireNonNull(from)).split(" ")[3])),
                        banque.getCompteById(Integer.parseInt((Objects.requireNonNull(to)).split(" ")[3]))
                );
                FilesHandler.save(banque);
                String message = "Virement effectué avec succès de " + amountValue + "MAD du compte " +
                        Integer.parseInt(from) + " vers le compte " + Integer.parseInt(to);
                JOptionPane.showMessageDialog(null, message, "Succès",
                        JOptionPane.INFORMATION_MESSAGE);
                LogsDAO.write(message);
            }
        });
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
        backButton.addActionListener(e -> {
            new AdminFrame(banque);
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        add(backButton, BorderLayout.SOUTH);
    }
}