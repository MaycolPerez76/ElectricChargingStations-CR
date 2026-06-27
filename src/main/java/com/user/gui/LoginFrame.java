package com.user.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Pantalla de login para usuario y administrador
 */
public class LoginFrame extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JComboBox<String> tipoUsuarioCombo;
    private JButton loginButton;
    private JButton cancelButton;
    private LoginListener loginListener;

    public interface LoginListener {
        void onLoginSuccess(String usuario, String contrasena, String tipo);
        void onCancel();
    }

    public LoginFrame(LoginListener listener) {
        this.loginListener = listener;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Estaciones de Carga Eléctrica - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con gradiente
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(33, 150, 243),
                        0, getHeight(), new Color(21, 101, 192));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // Panel de contenido blanco
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(30, 30, 390, 290);
        mainPanel.add(contentPanel);

        // Título
        JLabel titleLabel = new JLabel("⚡ Inicio de Sesión");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setBounds(100, 20, 200, 30);
        contentPanel.add(titleLabel);

        // Tipo de Usuario
        JLabel tipoLabel = new JLabel("Tipo de Usuario:");
        tipoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        tipoLabel.setBounds(20, 70, 100, 25);
        contentPanel.add(tipoLabel);

        tipoUsuarioCombo = new JComboBox<>(new String[]{"Usuario Regular", "Administrador"});
        tipoUsuarioCombo.setBounds(150, 70, 200, 25);
        tipoUsuarioCombo.setBackground(Color.WHITE);
        contentPanel.add(tipoUsuarioCombo);

        // Usuario
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usuarioLabel.setBounds(20, 120, 100, 25);
        contentPanel.add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(150, 120, 200, 25);
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPanel.add(usuarioField);

        // Contraseña
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        contrasenaLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        contrasenaLabel.setBounds(20, 170, 100, 25);
        contentPanel.add(contrasenaLabel);

        contrasenaField = new JPasswordField();
        contrasenaField.setBounds(150, 170, 200, 25);
        contrasenaField.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPanel.add(contrasenaField);

        // Botón Login
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(150, 230, 100, 35);
        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 12));
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener((ActionEvent e) -> performLogin());
        contentPanel.add(loginButton);

        // Botón Cancelar
        cancelButton = new JButton("Cancelar");
        cancelButton.setBounds(260, 230, 90, 35);
        cancelButton.setBackground(new Color(200, 200, 200));
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
        cancelButton.setBorder(BorderFactory.createEmptyBorder());
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener((ActionEvent e) -> performCancel());
        contentPanel.add(cancelButton);

        setContentPane(mainPanel);
    }

    private void performLogin() {
        String usuario = usuarioField.getText().trim();
        String contrasena = new String(contrasenaField.getPassword());
        String tipo = (String) tipoUsuarioCombo.getSelectedItem();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loginListener.onLoginSuccess(usuario, contrasena, tipo);
    }

    private void performCancel() {
        loginListener.onCancel();
    }
}
