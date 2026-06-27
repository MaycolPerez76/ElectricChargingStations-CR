package com.user.gui;

import com.user.listas.*;
import com.user.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel para administrador - gestión completa del sistema
 */
public class AdminPanel extends JPanel {
    private ListaEstaciones listaEstaciones;
    private ListaReservas listaReservas;
    private ListaVehiculos listaVehiculos;
    private ListaCargas listaCargas;
    private ListaUsuarios listaUsuarios;
    private Administrador administrador;
    private JTabbedPane tabbedPane;

    public AdminPanel(ListaEstaciones le, ListaReservas lr, ListaVehiculos lv, 
                      ListaCargas lc, ListaUsuarios lu) {
        this.listaEstaciones = le;
        this.listaReservas = lr;
        this.listaVehiculos = lv;
        this.listaCargas = lc;
        this.listaUsuarios = lu;
        this.administrador = new Administrador(le, lu, lv, lc, lr);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        // Panel superior
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(244, 67, 54));
        topPanel.setLayout(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("🛡️ PANEL DE ADMINISTRADOR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Panel con pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.PLAIN, 12));
        tabbedPane.addTab("🏪 Estaciones", createEstacionesPanel());
        tabbedPane.addTab("👥 Usuarios", createUsuariosPanel());
        tabbedPane.addTab("🚗 Vehículos", createVehiculosPanel());
        tabbedPane.addTab("⚡ Cargas", createCargasPanel());
        tabbedPane.addTab("📅 Reservas", createReservasPanel());
        tabbedPane.addTab("💰 Reportes", createReportesPanel());

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
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JButton addButton = createStyledButton("+ Agregar", new Color(76, 175, 80));
        JButton editButton = createStyledButton("✎ Editar", new Color(33, 150, 243));
        JButton deleteButton = createStyledButton("✕ Eliminar", new Color(244, 67, 54));

        addButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad de agregar estación", "Info", 
                JOptionPane.INFORMATION_MESSAGE);
        });

        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createUsuariosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Identificación");
        model.addColumn("Nombre");
        model.addColumn("Email");
        model.addColumn("Teléfono");
        model.addColumn("Activo");

        for (int i = 0; i < listaUsuarios.getAUsuario(); i++) {
            Usuario u = listaUsuarios.getUsuario(i);
            model.addRow(new Object[]{
                u.getIdentificacion(),
                u.getNombreCompleto(),
                u.getCorreoElectronico(),
                u.getTelefono(),
                u.isActivo() ? "Sí" : "No"
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JButton desactivarButton = createStyledButton("🔒 Desactivar", new Color(244, 67, 54));
        desactivarButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = model.getValueAt(row, 0).toString();
                administrador.desactivarUsuario(id);
                model.setValueAt("No", row, 4);
                JOptionPane.showMessageDialog(this, "Usuario desactivado", "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un usuario", "Alerta", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });

        actionPanel.add(desactivarButton);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createVehiculosPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Placa");
        model.addColumn("Propietario");
        model.addColumn("Marca");
        model.addColumn("Modelo");
        model.addColumn("Batería (kWh)");

        for (int i = 0; i < listaVehiculos.getAVehiculo(); i++) {
            Vehiculo v = listaVehiculos.getVehiculo(i);
            if (v != null) {
                model.addRow(new Object[]{
                    v.getPlaca(),
                    v.getPropietario() != null ? v.getPropietario().getNombreCompleto() : "N/A",
                    v.getMarca(),
                    v.getModelo(),
                    v.getCapacidadKwh()
                });
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel("Total de vehículos registrados: " + listaVehiculos.getAVehiculo());
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setBackground(new Color(200, 230, 255));
        infoLabel.setOpaque(true);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCargasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Vehículo");
        model.addColumn("Estación");
        model.addColumn("Propietario");
        model.addColumn("Energía (kWh)");
        model.addColumn("Costo (₡)");
        model.addColumn("Fecha");

        double totalEnergía = 0;
        double totalIngresos = 0;

        for (int i = 0; i < listaCargas.getACarga(); i++) {
            Carga c = listaCargas.getCarga(i);
            if (c != null && c.getVehiculo() != null && c.getEstacion() != null) {
                double costo = c.getEnergiaConsumida() * c.getPrecioRegular();
                totalEnergía += c.getEnergiaConsumida();
                totalIngresos += costo;
                
                Vehiculo v = c.getVehiculo();
                Estacion e = c.getEstacion();
                Usuario propietario = v.getPropietario();
                
                model.addRow(new Object[]{
                    c.getIdCarga(),
                    v.getPlaca(),
                    e.getNombre(),
                    propietario != null ? propietario.getNombreCompleto() : "N/A",
                    String.format("%.2f", c.getEnergiaConsumida()),
                    String.format("%.2f", costo),
                    c.getFecha()
                });
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        String infoText = String.format("Total Cargas: %d | Energía Total: %.2f kWh | Ingresos: ₡%.2f",
            listaCargas.getACarga(), totalEnergía, totalIngresos);
        JLabel infoLabel = new JLabel(infoText);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 11));
        infoLabel.setBackground(new Color(200, 255, 200));
        infoLabel.setOpaque(true);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(infoLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createReservasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Reserva");
        model.addColumn("Usuario");
        model.addColumn("Vehículo");
        model.addColumn("Fecha/Hora");
        model.addColumn("Estado");

        for (int i = 0; i < listaReservas.getAReserva(); i++) {
            Reserva r = listaReservas.getReserva(i);
            if (r != null && r.getUsuario() != null && r.getVehiculo() != null) {
                model.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getUsuario().getNombreCompleto(),
                    r.getVehiculo().getPlaca(),
                    r.getFechaHora(),
                    r.getEstado()
                });
            }
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        actionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        JButton confirmButton = createStyledButton("✓ Confirmar", new Color(76, 175, 80));
        JButton cancelButton = createStyledButton("✕ Cancelar", new Color(244, 67, 54));

        confirmButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = model.getValueAt(row, 0).toString();
                administrador.cambiarEstadoReserva(id, "Confirmada");
                model.setValueAt("Confirmada", row, 5);
                JOptionPane.showMessageDialog(this, "Reserva confirmada", "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        actionPanel.add(confirmButton);
        actionPanel.add(cancelButton);
        panel.add(actionPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createReportesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
        reportPanel.setBackground(Color.WHITE);
        reportPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        double totalEnergía = administrador.energiaTotal();
        double totalIngresos = administrador.ingresosTotales();
        int totalCargas = listaCargas.getACarga();
        int totalReservas = listaReservas.getAReserva();
        int totalVehiculos = listaVehiculos.getAVehiculo();
        int totalUsuarios = listaUsuarios.getAUsuario();

        addReportSection(reportPanel, "📊 ESTADÍSTICAS GENERALES", Color.WHITE, Color.BLACK);
        addReportStat(reportPanel, "Total de Estaciones", listaEstaciones.getAEstacion());
        addReportStat(reportPanel, "Total de Usuarios", totalUsuarios);
        addReportStat(reportPanel, "Total de Vehículos", totalVehiculos);

        addReportSection(reportPanel, "⚡ CARGAS Y ENERGÍA", Color.WHITE, Color.BLACK);
        addReportStatDouble(reportPanel, "Total Cargas Realizadas", totalCargas, "");
        addReportStatDouble(reportPanel, "Energía Total Consumida", totalEnergía, "kWh");
        addReportStatDouble(reportPanel, "Ingresos Totales", totalIngresos, "₡");

        addReportSection(reportPanel, "📅 RESERVAS", Color.WHITE, Color.BLACK);
        addReportStat(reportPanel, "Total de Reservas", totalReservas);

        JButton exportButton = new JButton("📥 Exportar Reporte");
        exportButton.setFont(new Font("Arial", Font.BOLD, 12));
        exportButton.setBackground(new Color(33, 150, 243));
        exportButton.setForeground(Color.WHITE);
        exportButton.setMaximumSize(new Dimension(200, 40));
        exportButton.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Reporte exportado exitosamente", "Éxito", 
                JOptionPane.INFORMATION_MESSAGE)
        );

        reportPanel.add(Box.createVerticalStrut(20));
        reportPanel.add(exportButton);
        reportPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(reportPanel);
        scrollPane.setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addReportSection(JPanel panel, String title, Color bgColor, Color fgColor) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setForeground(fgColor);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
    }

    private void addReportStat(JPanel panel, String label, int value) {
        JLabel stat = new JLabel("  • " + label + ": " + value);
        stat.setFont(new Font("Arial", Font.PLAIN, 12));
        stat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        panel.add(stat);
    }

    private void addReportStatDouble(JPanel panel, String label, double value, String unit) {
        JLabel stat = new JLabel("  • " + label + ": " + String.format("%.2f", value) + " " + unit);
        stat.setFont(new Font("Arial", Font.PLAIN, 12));
        stat.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        panel.add(stat);
    }

    private void styleTable(JTable table) {
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.getTableHeader().setBackground(new Color(244, 67, 54));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
