/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import com.user.algoritmos.EstacionesGrafo;
import com.user.listas.ListaCargas;
import com.user.listas.ListaEstaciones;
import com.user.listas.ListaReservas;
import com.user.listas.ListaUsuarios;
import com.user.listas.ListaVehiculos;
import com.user.model.Administrador;

/**
 *
 * @author mayco
 */
public class ElectricChargingStationsCR {

    public static void main(String[] args) {
        ListaUsuarios lu = new ListaUsuarios();
        ListaEstaciones le = new ListaEstaciones();
        ListaVehiculos lv = new ListaVehiculos();
        ListaCargas lc = new ListaCargas();
        ListaReservas ls = new ListaReservas();
 
        lu.cargarListaUsuarios();
        le.cargarListaEstaciones();
        lv.cargarListaVehiculos(lu);
        lc.cargarListaCargas(lu, lv, le);
        ls.cargarListaReservas();
        
        
        Administrador ad = new Administrador(le, lu, lv, lc, ls);
        ad.menu();
    }
}
