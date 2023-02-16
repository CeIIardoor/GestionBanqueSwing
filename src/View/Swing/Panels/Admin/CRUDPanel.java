package View.Swing.Panels.Admin;

import Model.Banque;
import Model.Client;
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

        JButton btnEdit = new JButton("Edit");

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Nom", "Email", "Nbr. Comptes", "Solde"}, 0);

        for (Client client : banque.getClients()) {
            model.addRow(new Object[]{client.getId(), client.getNom() + " " + client.getPrenom(), client.getEmail(),
                    client.getComptes().size(), client.getSoldeTotal()});
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
        btnAdd.addActionListener(e -> {
            // TODO
        });
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

    }
}