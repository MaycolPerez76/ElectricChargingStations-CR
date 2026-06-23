package com.user.listas;

import com.user.model.Carga;
import com.user.model.Estacion;

/**
 *
 * @author myava
 */
public class ListaCargas {

    private Carga listaCargas[];
    private int tamMaximo;
    private int aCarga;

    public ListaCargas() {
        tamMaximo = 30;
        listaCargas = new Carga[tamMaximo];
        aCarga = 0;
    }

    public ListaCargas(int tam) {
        tamMaximo = tam;
        listaCargas = new Carga[tamMaximo];
        aCarga = 0;
    }

    public void agregarCarga(Carga nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede agregar una carga nula.");
            return;
        }
        if (aCarga < tamMaximo) {
            listaCargas[aCarga++] = nueva;
        } else {
            System.out.println("Error: Lista de cargas llena.");
        }
    }

    public void modificarCarga(int pos, Carga nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede modificar con una carga nula.");
            return;
        }
        if (pos >= 0 && pos < aCarga) {
            listaCargas[pos] = nueva;
        } else {
            System.out.println("Error: Posición inválida.");
        }
    }

    public void reemplazarCarga(String idCarga, Carga nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede reemplazar con una carga nula.");
            return;
        }
        int pos = consultarCargaXID(idCarga);
        if (pos >= 0) {
            listaCargas[pos] = nueva;
        } else {
            System.out.println("Error: Carga con ID " + idCarga + " no encontrada.");
        }
    }

    public int consultarCargaXID(String idCarga) {
        if (idCarga == null) return -1;
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null && listaCargas[i].getIdCarga().equals(idCarga)) {
                return i;
            }
        }
        return -1;
    }

    public Carga getCarga(int pos) {
        if (pos >= 0 && pos < aCarga) {
            return listaCargas[pos];
        }
        return null;
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getACarga() {
        return aCarga;
    }

    public ListaCargas filtroXEstacion(Estacion estacion) {
        if (estacion == null) return new ListaCargas(tamMaximo);
        ListaCargas temporal = new ListaCargas(tamMaximo);
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null && 
                listaCargas[i].getEstacion() != null &&
                listaCargas[i].getEstacion().getIdEstacion() == estacion.getIdEstacion()) {
                temporal.agregarCarga(listaCargas[i]);
            }
        }
        return temporal;
    }

    public ListaCargas filtroXEstado(int estado) {
        ListaCargas temporal = new ListaCargas(tamMaximo);
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null && listaCargas[i].getEstado() == estado) {
                temporal.agregarCarga(listaCargas[i]);
            }
        }
        return temporal;
    }
    
        public void cargarListaCargas(ListaUsuarios usuarios) {
        };

    public ListaCargas unirLista(ListaCargas listaA, ListaCargas listaB) {
        int tamañoUnion = listaA.getACarga() + listaB.getACarga();
        ListaCargas temporal = new ListaCargas(tamañoUnion);
        for (int i = 0; i < listaA.getACarga(); i++) {
            temporal.agregarCarga(listaA.getCarga(i));
        }
        for (int i = 0; i < listaB.getACarga(); i++) {
            temporal.agregarCarga(listaB.getCarga(i));
        }
        return temporal;
    }

    public String toReporte() {
        String sal = "Total de cargas: " + aCarga + "\n";
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null) {
                sal = sal + listaCargas[i].toDetalle() + "\n";
            }
        }
        return sal;
    }

    @Override
    public String toString() {
        return toReporte();
    }
}
