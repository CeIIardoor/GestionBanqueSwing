package View.Swing.Panels.Auth;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        setLayout(new BorderLayout());

        // logo and title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        ImageIcon logo = new ImageIcon("src\\View\\Swing\\Assets\\logo.png");
        Image img = logo.getImage();
        Image newImg = img.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        logo = new ImageIcon(newImg);

        JLabel logoLabel = new JLabel(logo);
        JLabel titleLabel = new JLabel("Dire Straits Bank");
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titlePanel.add(logoLabel);
        titlePanel.add(titleLabel);

        // email and password input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 50, 10, 75);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(10);
        emailField.setPreferredSize(new Dimension(0, 25));
        inputPanel.add(emailLabel, c);
        c.gridx = 1;
        c.weightx = 2.0;
        inputPanel.add(emailField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(0, 25));
        inputPanel.add(passwordLabel, c);
        c.gridx = 1;
        c.weightx = 2.0;
        inputPanel.add(passwordField, c);

        // login button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton loginButton = new JButton("Login");
        // Set button styles
        loginButton.setBackground(new Color(0x1E90FF));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Roboto", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setFocusPainted(false);
        buttonPanel.add(loginButton);

        // Add components to main panel
        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set panel styles
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
}