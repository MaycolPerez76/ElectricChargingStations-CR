/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user.listas;
import com.user.model.Vehiculo;
/**
 *
 * @author mayco
 */
public class ListaVehiculos {
    private Vehiculo listaVehiculos[];
    private int tamMaximo;
    private int aContacto;
    
    
    public ListaVehiculos() {
        tamMaximo = 40;
        listaVehiculos = new Vehiculo[tamMaximo];
        aContacto = 0;
    }

    public ListaVehiculos(int tam) {
        tamMaximo = tam;
        listaVehiculos = new Vehiculo[tamMaximo];
        aContacto = 0;
    }
//..............................................................................

    public void agregarContacto(Vehiculo nuevo) {
        if (aContacto < tamMaximo) { // Debes validar siempre
            listaVehiculos[aContacto++] = nuevo;
        } else {
            System.out.println("Error: Lista llena");
        }
    }

    public void modificarContacto(int pos, Vehiculo nuevo) {
        listaVehiculos[pos] = nuevo;
    }

    public void remplazarContacto(String idNuevo, Vehiculo nuevo) {
        if (consultarVehiculoXID(idNuevo) >= 0) {
            listaVehiculos[consultarVehiculoXID(idNuevo)] = nuevo;
        }
    }

    public void cambiarEstado(String idNuevo, int estadoNuevo) {
        if (consultarVehiculoXID(idNuevo) >= 0) {
            listaVehiculos[consultarVehiculoXID(idNuevo)].setEstado(estadoNuevo);
        }
    }

    public int consultarVehiculoXID(int id) {
        int resultado = -1;
        for (int i = 0; i < aContacto; i++) {
            if (listaVehiculos[i].getIdVehiculo() == id) {
                resultado = i;
                i = aContacto;
            }
        }
        return resultado;
    }
//..............................................................................

    public String getPlaca(int pos) {
        return listaVehiculos[pos].getPlaca();
    }

    public Vehiculo getVehiculo(int pos) {
        return listaVehiculos[pos];
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getAContacto() {
        return aContacto;
    }
//..............................................................................

    public ListaVehiculos filtroXActivos(String lugar) {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        for (int i = 0; i < aContacto; i++) {
            if (listaVehiculos[i].getEstado() == 1) {
                System.out.println("Lista de carros activos");  
                temporal.agregarContacto(listaVehiculos[i]);
            }
        }
        return temporal;
    }
    public ListaVehiculos filtroXDefectuosos() {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        for (int i = 0; i < aContacto; i++) {
            if (listaVehiculos[i].getEstado()== 3) {
                System.out.println("Lista de carros defectuosos o en mantenimiento");
                temporal.agregarContacto(listaVehiculos[i]);
            }
        }
        return temporal;
    }
    
    public ListaVehiculos filtroXPlaca(String placa) {
        ListaVehiculos temporal = new ListaVehiculos(tamMaximo);
        for (int i = 0; i < aContacto; i++) {
            if (listaVehiculos[i].getPlaca().equals(placa)) {
                temporal.agregarContacto(listaVehiculos[i]);
            }
              System.out.println("Placa no encontrada");  
        }
        return temporal;
    }


    public String toReporte() {
        String sal = "LISTA DE CONTACTOS GENERAL";
        for (int i = 0; i < aContacto; i++) {
            sal += listaVehiculos[i].toDetalle();
        }
        return sal;
    }

    public void cargarListaCarros() {
        
        
    }

    public String toString() {
        String sal = "LISTA DE VEHICULOS GENERAL";
        for (int i = 0; i < aContacto; i++) {
            sal += listaVehiculos[i].toString();
        }
        return sal;
    }


}
