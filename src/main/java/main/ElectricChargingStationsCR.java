/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import com.user.gui.MainWindow;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación
 * @author mayco
 */
public class ElectricChargingStationsCR {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
