package View.Swing.Panels.Admin;

import DAO.FilesDAO.FilesHandler;
import Model.Banque;
import Model.Client;
import Validator.FormValidator;
import View.Swing.Frames.Admin.AdminFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CRUDPanel extends JPanel {

    public CRUDPanel(Banque banque) {

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Dashboard Admin : CRUD");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nom", "Prénom", "Email", "Date Ajout"}, 0);

        for (Client client : banque.getClients()) {
            model.addRow(new Object[]{client.getId(), client.getNom(), client.getPrenom(), client.getEmail(),
                    client.getDateAjout()
            });
        }

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JButton btnBack = new JButton("Retour");
        btnBack.addActionListener(e -> {
            new AdminFrame(banque);
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        buttonPanel.add(btnBack);
        JButton btnAdd = new JButton("Ajouter");
        btnAdd.setBackground(new Color(75, 255, 75));
        btnAdd.addActionListener(e -> {
                    JTextField textFieldNom = new JTextField(10);
                    JTextField textFieldPrenom = new JTextField(10);
                    JTextField textFieldEmail = new JTextField(10);
                    JPasswordField textFieldPassword = new JPasswordField(10);

                    JPanel panel = new JPanel();
                    panel.add(new JLabel("Nom:"));
                    panel.add(textFieldNom);
                    panel.add(new JLabel("Prénom:"));
                    panel.add(textFieldPrenom);
                    panel.add(new JLabel("Email:"));
                    panel.add(textFieldEmail);
                    panel.add(new JLabel("Password:"));
                    panel.add(textFieldPassword);

                    int result = JOptionPane.showConfirmDialog(null, panel,
                            "Entrer infos nouveau Client", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        String passwordValue = String.valueOf(textFieldPassword.getPassword());
                        if (FormValidator.validateEmail(textFieldEmail.getText()) &&
                                FormValidator.validatePassword(passwordValue) &&
                                (textFieldNom.getText().length() > 0) &&
                                (textFieldPrenom.getText().length() > 0)

                        ) {
                            banque.ajouterClient(
                                    new Client(textFieldNom.getText(), textFieldPrenom.getText(),
                                            textFieldEmail.getText(), passwordValue));

                            FilesHandler.update(banque);
                            CRUDPanel.this.removeAll();
                            CRUDPanel.this.add(new CRUDPanel(banque));
                            CRUDPanel.this.revalidate();
                            CRUDPanel.this.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Erreur de saisie");
                        }
                    }
                }
        );
        JButton btnEdit = new JButton("Modifier");
        btnEdit.setBackground(new Color(75, 255, 255));
        btnEdit.addActionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                int id = (int) table.getValueAt(selectedRowIndex, 0);
                Client client = banque.getClientById(id);
                JTextField textFieldNom = new JTextField(client.getNom(), 10);
                JTextField textFieldPrenom = new JTextField(client.getPrenom(), 10);
                JTextField textFieldEmail = new JTextField(client.getEmail(), 10);
                JPasswordField textFieldPassword = new JPasswordField(client.getPassword(), 10);

                JPanel panel = new JPanel();
                panel.add(new JLabel("Nom:"));
                panel.add(textFieldNom);
                textFieldNom.setText(client.getNom());
                panel.add(new JLabel("Prénom:"));
                panel.add(textFieldPrenom);
                textFieldPrenom.setText(client.getPrenom());
                panel.add(new JLabel("Email:"));
                panel.add(textFieldEmail);
                textFieldEmail.setText(client.getEmail());
                panel.add(new JLabel("Password:"));
                panel.add(textFieldPassword);
                textFieldPassword.setText(client.getPassword());

                int result = JOptionPane.showConfirmDialog(null, panel,
                        "Entrer infos nouveau Client", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String passwordValue = String.valueOf(textFieldPassword.getPassword());
                    if (FormValidator.validateEmail(textFieldEmail.getText()) &&
                            FormValidator.validatePassword(passwordValue) &&
                            (textFieldNom.getText().length() > 0) &&
                            (textFieldPrenom.getText().length() > 0)) {
                        client.setNom(textFieldNom.getText());
                        client.setPrenom(textFieldPrenom.getText());
                        client.setEmail(textFieldEmail.getText());
                        client.setPassword(passwordValue);

                        FilesHandler.update(banque);
                        CRUDPanel.this.removeAll();
                        CRUDPanel.this.add(new CRUDPanel(banque));
                        CRUDPanel.this.revalidate();
                        CRUDPanel.this.repaint();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur de saisie");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client");
            }
        });
        JButton btnDelete = new JButton("Supprimer");
        btnDelete.setBackground(new Color(255, 75, 75));
        btnDelete.addActionListener(e -> {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
                int id = (int) table.getValueAt(selectedRowIndex, 0);
                Client client = banque.getClientById(id);
                int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer le client " + client.getNom() + " " + client.getPrenom() + " ?",
                        "Supprimer Client", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    banque.supprimerClient(client);
                    FilesHandler.update(banque);
                    CRUDPanel.this.removeAll();
                    CRUDPanel.this.add(new CRUDPanel(banque));
                    CRUDPanel.this.revalidate();
                    CRUDPanel.this.repaint();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un client");
            }
        });
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}