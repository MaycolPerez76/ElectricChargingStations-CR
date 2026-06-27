package com.user.gui;

import com.user.listas.*;
import com.user.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel para usuario regular - visualiza estaciones, reservas, vehículos y cargas
 */
public class UsuarioPanel extends JPanel {
    private ListaEstaciones listaEstaciones;
    private ListaReservas listaReservas;
    private ListaVehiculos listaVehiculos;
    private ListaCargas listaCargas;
    private ListaUsuarios listaUsuarios;
    private Usuario usuarioActual;
    private JTabbedPane tabbedPane;

    public UsuarioPanel(Usuario usuario, ListaEstaciones le, ListaReservas lr, 
                        ListaVehiculos lv, ListaCargas lc, ListaUsuarios lu) {
        this.usuarioActual = usuario;
        this.listaEstaciones = le;
        this.listaReservas = lr;
        this.listaVehiculos = lv;
        this.listaCargas = lc;
        this.listaUsuarios = lu;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel superior con bienvenida
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(33, 150, 243));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("👤 Bienvenido: " + usuarioActual.getNombreCompleto());
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        // Panel con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));
        tabbedPane.addTab("🏪 Estaciones", createEstacionesPanel());
        tabbedPane.addTab("🚗 Mi Vehículos", createMiVehiculos());
        tabbedPane.addTab("📅 Mis Reservas", createMisReservas());
        tabbedPane.addTab("⚡ Mis Cargas", createMisCargas());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createEstacionesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Ubicación");
        model.addColumn("Cargadores");
        model.addColumn("Estado");

        for (int i = 0; i < listaEstaciones.getAEstacion(); i++) {
            Estacion e = listaEstaciones.getEstacion(i);
            model.addRow(new Object[]{
                e.getIdEstacion(),
                e.getNombre(),
                e.getUbicacion(),
                e.getCantidadCargadores(),
                e.getEstado()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Total de estaciones: " + listaEstaciones.getAEstacion());
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMiVehiculos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Placa");
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Batería (kWh)");

        for (int i = 0; i < listaVehiculos.getAVehiculo(); i++) {
            Vehiculo v = listaVehiculos.getVehiculo(i);
            if (v != null && v.getPropietario() != null && 
                v.getPropietario().getIdentificacion().equals(usuarioActual.getIdentificacion())) {
                model.addRow(new Object[]{
                    v.getPlaca(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getCapacidadKwh()
                });
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Total de vehículos: " + model.getRowCount());
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMisReservas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Reserva");
        model.addColumn("Vehículo");
        model.addColumn("Fecha/Hora");
        model.addColumn("Estado");

        for (int i = 0; i < listaReservas.getAReserva(); i++) {
            Reserva r = listaReservas.getReserva(i);
            if (r != null && r.getUsuario() != null && 
                r.getUsuario().getIdentificacion().equals(usuarioActual.getIdentificacion())) {
                model.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getVehiculo() != null ? r.getVehiculo().getPlaca() : "N/A",
                    r.getFechaHora(),
                    r.getEstado()
                });
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Total de reservas: " + model.getRowCount());
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMisCargas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Carga");
        model.addColumn("Vehículo");
        model.addColumn("Estación");
        model.addColumn("Energía (kWh)");
        model.addColumn("Costo (₡)");
        model.addColumn("Fecha");

        double totalEnergía = 0;
        double totalCosto = 0;

        for (int i = 0; i < listaCargas.getACarga(); i++) {
            Carga c = listaCargas.getCarga(i);
            if (c != null && c.getVehiculo() != null && c.getEstacion() != null) {
                Vehiculo v = c.getVehiculo();
                if (v.getPropietario() != null && 
                    v.getPropietario().getIdentificacion().equals(usuarioActual.getIdentificacion())) {
                    Estacion e = c.getEstacion();
                    double costo = c.getEnergiaConsumida() * c.getPrecioRegular();
                    totalEnergía += c.getEnergiaConsumida();
                    totalCosto += costo;
                    model.addRow(new Object[]{
                        c.getIdCarga(),
                        v.getPlaca(),
                        e.getNombre(),
                        String.format("%.2f", c.getEnergiaConsumida()),
                        String.format("%.2f", costo),
                        c.getFecha()
                    });
                }
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        String infoText = String.format("Total cargas: %d | Energía Total: %.2f kWh | Costo Total: ₡%.2f",
            model.getRowCount(), totalEnergía, totalCosto);
        JLabel infoLabel = new JLabel(infoText);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setBackground(new Color(200, 230, 255));
        infoLabel.setOpaque(true);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }
}
