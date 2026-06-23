package com.user.listas;

import com.user.model.Reserva;
import com.user.model.Estacion;
import com.user.model.Usuario;

/**
 *
 * @author myava
 */
public class ListaReservas {

    private Reserva listaReservas[];
    private int tamMaximo;
    private int aReserva;

    public ListaReservas() {
        tamMaximo = 30;
        listaReservas = new Reserva[tamMaximo];
        aReserva = 0;
    }

    public ListaReservas(int tam) {
        tamMaximo = tam;
        listaReservas = new Reserva[tamMaximo];
        aReserva = 0;
    }

    public void agregarReserva(Reserva nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede agregar una reserva nula.");
            return;
        }
        if (aReserva < tamMaximo) {
            listaReservas[aReserva++] = nueva;
        } else {
            System.out.println("Error: Lista de reservas llena.");
        }
    }

    public void modificarReserva(int pos, Reserva nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede modificar con una reserva nula.");
            return;
        }
        if (pos >= 0 && pos < aReserva) {
            listaReservas[pos] = nueva;
        } else {
            System.out.println("Error: Posición inválida.");
        }
    }

    public void reemplazarReserva(String idReserva, Reserva nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede reemplazar con una reserva nula.");
            return;
        }
        int pos = consultarReservaXID(idReserva);
        if (pos >= 0) {
            listaReservas[pos] = nueva;
        } else {
            System.out.println("Error: Reserva con ID " + idReserva + " no encontrada.");
        }
    }

    public int consultarReservaXID(String idReserva) {
        if (idReserva == null) return -1;
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null && listaReservas[i].getIdReserva().equals(idReserva)) {
                return i;
            }
        }
        return -1;
    }

    public Reserva getReserva(int pos) {
        if (pos >= 0 && pos < aReserva) {
            return listaReservas[pos];
        }
        return null;
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getAReserva() {
        return aReserva;
    }

    public ListaReservas filtroXEstacion(Estacion estacion) {
        if (estacion == null) return new ListaReservas(tamMaximo);
        ListaReservas temporal = new ListaReservas(tamMaximo);
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null && 
                listaReservas[i].getEstacion() != null &&
                listaReservas[i].getEstacion().getIdEstacion() == estacion.getIdEstacion()) {
                temporal.agregarReserva(listaReservas[i]);
            }
        }
        return temporal;
    }

    public ListaReservas filtroXUsuario(Usuario usuario) {
        if (usuario == null) return new ListaReservas(tamMaximo);
        ListaReservas temporal = new ListaReservas(tamMaximo);
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null && 
                listaReservas[i].getUsuario() != null &&
                listaReservas[i].getUsuario().getIdPersona().equals(usuario.getIdPersona())) {
                temporal.agregarReserva(listaReservas[i]);
            }
        }
        return temporal;
    }

    public ListaReservas filtroXEstado(String estado) {
        if (estado == null) return new ListaReservas(tamMaximo);
        ListaReservas temporal = new ListaReservas(tamMaximo);
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null && 
                listaReservas[i].getEstado().equalsIgnoreCase(estado)) {
                temporal.agregarReserva(listaReservas[i]);
            }
        }
        return temporal;
    }

    public ListaReservas filtroXVehiculo(String placa) {
        if (placa == null) return new ListaReservas(tamMaximo);
        ListaReservas temporal = new ListaReservas(tamMaximo);
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null && 
                listaReservas[i].getVehiculo() != null &&
                listaReservas[i].getVehiculo().getPlaca().equalsIgnoreCase(placa)) {
                temporal.agregarReserva(listaReservas[i]);
            }
        }
        return temporal;
    }

    public ListaReservas unirLista(ListaReservas listaA, ListaReservas listaB) {
        int tamañoUnion = listaA.getAReserva() + listaB.getAReserva();
        ListaReservas temporal = new ListaReservas(tamañoUnion);
        for (int i = 0; i < listaA.getAReserva(); i++) {
            temporal.agregarReserva(listaA.getReserva(i));
        }
        for (int i = 0; i < listaB.getAReserva(); i++) {
            temporal.agregarReserva(listaB.getReserva(i));
        }
        return temporal;
    }

    public String toReporte() {
        String sal = "Total de reservas: " + aReserva + "\n";
        for (int i = 0; i < aReserva; i++) {
            if (listaReservas[i] != null) {
                sal = sal + listaReservas[i].toDetalle() + "\n";
            }
        }
        return sal;
    }

    public void cargarListaReservas() {
        System.out.println("Lista de reservas inicializada vacía.");
    }

    @Override
    public String toString() {
        return toReporte();
    }
}
