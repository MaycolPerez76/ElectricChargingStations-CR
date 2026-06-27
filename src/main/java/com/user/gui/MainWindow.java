package com.user.gui;

import com.user.listas.*;
import com.user.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Ventana principal - gestiona el login y la navegación entre paneles
 */
public class MainWindow extends JFrame {
    private ListaEstaciones listaEstaciones;
    private ListaReservas listaReservas;
    private ListaVehiculos listaVehiculos;
    private ListaCargas listaCargas;
    private ListaUsuarios listaUsuarios;
    private LoginFrame loginFrame;
    private JPanel mainContentPanel;
    private Usuario usuarioActual;

    public MainWindow() {
        initializeListas();
        initComponents();
        showLoginFrame();
    }

    private void initializeListas() {
        listaUsuarios = new ListaUsuarios();
        listaEstaciones = new ListaEstaciones();
        listaVehiculos = new ListaVehiculos();
        listaCargas = new ListaCargas();
        listaReservas = new ListaReservas();

        listaUsuarios.cargarListaUsuarios();
        listaEstaciones.cargarListaEstaciones();
        listaVehiculos.cargarListaVehiculos(listaUsuarios);
        listaCargas.cargarListaCargas(listaUsuarios, listaVehiculos, listaEstaciones);
        listaReservas.cargarListaReservas();
    }

    private void initComponents() {
        setTitle("Sistema de Estaciones de Carga Eléctrica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        mainContentPanel = new JPanel();
        setContentPane(mainContentPanel);
    }

    private void showLoginFrame() {
        loginFrame = new LoginFrame(new LoginFrame.LoginListener() {
            @Override
            public void onLoginSuccess(String usuario, String contrasena, String tipo) {
                handleLogin(usuario, contrasena, tipo);
            }

            @Override
            public void onCancel() {
                System.exit(0);
            }
        });
        loginFrame.setVisible(true);
    }

    private void handleLogin(String usuario, String contrasena, String tipo) {
        if (tipo.equals("Administrador")) {
            handleAdminLogin(usuario, contrasena);
        } else {
            handleUsuarioLogin(usuario, contrasena);
        }
    }

    private void handleAdminLogin(String usuario, String contrasena) {
        if (usuario.equals("admin") && contrasena.equals("admin123")) {
            loginFrame.dispose();
            showAdminPanel();
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(loginFrame, 
                "Usuario o contraseña incorrectos para administrador", 
                "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUsuarioLogin(String usuario, String contrasena) {
        int pos = listaUsuarios.consultarUsuarioXIdentificacion(usuario);
        if (pos >= 0) {
            Usuario u = listaUsuarios.getUsuario(pos);
            if (u.isActivo()) {
                if (contrasena.equals("user123")) {
                    usuarioActual = u;
                    loginFrame.dispose();
                    showUsuarioPanel();
                    setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, 
                        "Contraseña incorrecta", 
                        "Error de Login", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, 
                    "Este usuario está desactivado", 
                    "Acceso Denegado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(loginFrame, 
                "Usuario no encontrado", 
                "Error de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAdminPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        AdminPanel adminPanel = new AdminPanel(listaEstaciones, listaReservas, 
                                               listaVehiculos, listaCargas, listaUsuarios);
        mainContentPanel.add(adminPanel, BorderLayout.CENTER);

        JPanel menuBar = createMenuBar(true);
        mainContentPanel.add(menuBar, BorderLayout.SOUTH);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private void showUsuarioPanel() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BorderLayout());

        UsuarioPanel usuarioPanel = new UsuarioPanel(usuarioActual, listaEstaciones, listaReservas,
                                                      listaVehiculos, listaCargas, listaUsuarios);
        mainContentPanel.add(usuarioPanel, BorderLayout.CENTER);

        JPanel menuBar = createMenuBar(false);
        mainContentPanel.add(menuBar, BorderLayout.SOUTH);

        mainContentPanel.revalidate();
        mainContentPanel.repaint();
    }

    private JPanel createMenuBar(boolean isAdmin) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 33, 33));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel userLabel = new JLabel(isAdmin ? "👤 Administrador" : "👤 Usuario: " + usuarioActual.getNombreCompleto());
        userLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        userLabel.setForeground(Color.WHITE);
        panel.add(userLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("🚪 Cerrar Sesión");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 11));
        logoutButton.setBackground(new Color(244, 67, 54));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });

        panel.add(logoutButton, BorderLayout.EAST);

        return panel;
    }

    private void handleLogout() {
        usuarioActual = null;
        mainContentPanel.removeAll();
        mainContentPanel.revalidate();
        mainContentPanel.repaint();
        setVisible(false);
        showLoginFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
