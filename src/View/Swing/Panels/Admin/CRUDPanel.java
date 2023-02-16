package View.Swing.Panels.Admin;

import Model.Banque;
import View.Swing.Frames.Admin.AdminFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CRUDPanel extends JPanel {
    private final DefaultTableModel tableModel;

    public CRUDPanel(Banque banque) {

        setLayout(new BorderLayout());

        // Add title to the north
        JLabel titleLabel = new JLabel("Dashboard Admin : CRUD");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Create a scrollable table with 4 fields
        String[] columnNames = {"ID", "Nom", "Email", "Comptes", "Solde", "Actions"};
        Object[][] data = {};
        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Add input fields and buttons to the south
        JPanel inputPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(nameField);
        inputPanel.add(emailField);
        inputPanel.add(phoneField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addRow(idField.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateRow(table.getSelectedRow(), idField.getText(), nameField.getText(), emailField.getText(), phoneField.getText()));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteRow(table.getSelectedRow()));

        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Add a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new AdminFrame(banque);
            SwingUtilities.getWindowAncestor(this).dispose();
        });

        add(backButton, BorderLayout.SOUTH);
    }

    private void addRow(String id, String name, String email, String phone) {
        Vector<String> row = new Vector<>();
        row.add(id);
        row.add(name);
        row.add(email);
        row.add(phone);
        tableModel.addRow(row);
    }

    private void updateRow(int index, String id, String name, String email, String phone) {
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Update Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.setValueAt(id, index, 0);
        tableModel.setValueAt(name, index, 1);
        tableModel.setValueAt(email, index, 2);
        tableModel.setValueAt(phone, index, 3);
    }

    private void deleteRow(int index) {
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.", "Delete Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(index);
    }
}