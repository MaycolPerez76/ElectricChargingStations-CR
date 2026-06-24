package com.user.listas;

/**
 *
 * @author myava
 */
import com.user.model.*;

public class ListaEstaciones {

    private Estacion listaEstaciones[];
    private int tamMaximo;
    private int aEstacion;

    public ListaEstaciones() {
        tamMaximo = 30;
        listaEstaciones = new Estacion[tamMaximo];
        aEstacion = 0;
    }

    public ListaEstaciones(int tam) {
        tamMaximo = tam;
        listaEstaciones = new Estacion[tamMaximo];
        aEstacion = 0;
    }

    public void agregarEstacion(Estacion nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede agregar una estación nula.");
            return;
        }
        if (aEstacion < tamMaximo) {
            listaEstaciones[aEstacion++] = nueva;

        } else {
            System.out.println("Error: Lista de estaciones llena.");
        }
    }

    public void modificarEstacion(int pos, Estacion nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede modificar con una estación nula.");
            return;
        }
        if (pos >= 0 && pos < aEstacion) {
            listaEstaciones[pos] = nueva;
        } else {
            System.out.println("Error: Posición inválida.");
        }
    }

    public void reemplazarEstacion(int idEstacion, Estacion nueva) {
        if (nueva == null) {
            System.out.println("Error: No se puede reemplazar con una estación nula.");
            return;
        }
        int pos = consultarEstacionXID(idEstacion);
        if (pos >= 0) {
            listaEstaciones[pos] = nueva;
        } else {
            System.out.println("Error: Estación con ID " + idEstacion + " no encontrada.");
        }
    }

    public int consultarEstacionXID(int idEstacion) {
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null && listaEstaciones[i].getIdEstacion() == idEstacion) {
                return i;
            }
        }
        return -1;
    }

    public int consultarEstacionXNombre(String nombre) {
        if (nombre == null) {
            return -1;
        }
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null
                    && listaEstaciones[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public int consultarEstacionXUbicacion(String ubicacion) {
        if (ubicacion == null) {
            return -1;
        }
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null
                    && listaEstaciones[i].getUbicacion().equalsIgnoreCase(ubicacion)) {
                return i;
            }
        }
        return -1;
    }

    public Estacion getEstacion(int pos) {
        if (pos >= 0 && pos < aEstacion) {
            return listaEstaciones[pos];
        }
        return null;
    }

    public String getNombreEstacion(int pos) {
        if (pos >= 0 && pos < aEstacion && listaEstaciones[pos] != null) {
            return listaEstaciones[pos].getNombre();
        }
        return null;
    }

    public int getTamMaximo() {
        return tamMaximo;
    }

    public int getAEstacion() {
        return aEstacion;
    }

    public ListaEstaciones filtroXEstado(String estado) {
        if (estado == null) {
            return new ListaEstaciones(tamMaximo);
        }
        ListaEstaciones temporal = new ListaEstaciones(tamMaximo);
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null
                    && listaEstaciones[i].getEstado().equalsIgnoreCase(estado)) {
                temporal.agregarEstacion(listaEstaciones[i]);
            }
        }
        return temporal;
    }

    public ListaEstaciones filtroXDisponibles() {
        ListaEstaciones temporal = new ListaEstaciones(tamMaximo);
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null
                    && listaEstaciones[i].getCargadoresOcupados()
                    < listaEstaciones[i].getCantidadCargadores()) {
                temporal.agregarEstacion(listaEstaciones[i]);
            }
        }
        return temporal;
    }

    public ListaEstaciones filtroXOcupadas() {
        ListaEstaciones temporal = new ListaEstaciones(tamMaximo);
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null
                    && listaEstaciones[i].getCargadoresOcupados()
                    == listaEstaciones[i].getCantidadCargadores()) {
                temporal.agregarEstacion(listaEstaciones[i]);
            }
        }
        return temporal;
    }

    public ListaEstaciones filtroXRequiereCita() {
        ListaEstaciones temporal = new ListaEstaciones(tamMaximo);
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null && listaEstaciones[i].isRequiereCita()) {
                temporal.agregarEstacion(listaEstaciones[i]);
            }
        }
        return temporal;
    }

    public ListaEstaciones filtroXSinCita() {
        ListaEstaciones temporal = new ListaEstaciones(tamMaximo);
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null && !listaEstaciones[i].isRequiereCita()) {
                temporal.agregarEstacion(listaEstaciones[i]);
            }
        }
        return temporal;
    }

    public ListaEstaciones unirLista(ListaEstaciones listaA, ListaEstaciones listaB) {
        int tamañoUnion = listaA.getAEstacion() + listaB.getAEstacion();
        ListaEstaciones temporal = new ListaEstaciones(tamañoUnion);
        for (int i = 0; i < listaA.getAEstacion(); i++) {
            temporal.agregarEstacion(listaA.getEstacion(i));
        }
        for (int i = 0; i < listaB.getAEstacion(); i++) {
            temporal.agregarEstacion(listaB.getEstacion(i));
        }
        return temporal;
    }

    public String toReporte() {
        String sal = "Total de estaciones: " + aEstacion + "\n";
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null) {
                Estacion e = listaEstaciones[i];
                sal = sal + "ID: " + e.getIdEstacion()
                        + " | " + e.getNombre()
                        + " | " + e.getUbicacion()
                        + " | Cargadores: " + e.getCantidadCargadores()
                        + " | Ocupados: " + e.getCargadoresOcupados()
                        + " | Estado: " + e.getEstado()
                        + " | Cita: " + (e.isRequiereCita() ? "Sí" : "No")
                        + "\n";
            }
        }
        return sal;
    }

    public String toReporteResumido() {
        String sal = "Total: " + aEstacion + "\n";
        for (int i = 0; i < aEstacion; i++) {
            if (listaEstaciones[i] != null) {
                Estacion e = listaEstaciones[i];
                int disponibles = e.getCantidadCargadores() - e.getCargadoresOcupados();
                sal = sal + e.getNombre()
                        + " - " + disponibles + "/"
                        + e.getCantidadCargadores() + " disponibles"
                        + " (" + e.getEstado() + ")\n";
            }
        }
        return sal;
    }

    public void cargarListaEstaciones() {
        agregarEstacion(new Estacion(1, "Estación Norte", "San José", 4, 0, "Disponible", true, "CCS2"));
        agregarEstacion(new Estacion(2, "Estación Sur", "Cartago", 3, 1, "Parcial", false, "CCS2"));
        agregarEstacion(new Estacion(3, "Estación Este", "Heredia", 5, 5, "Ocupada", true, "CHAdeMO"));
        agregarEstacion(new Estacion(4, "Estación Oeste", "Alajuela", 2, 0, "Mantenimiento", false, "CCS2"));
        agregarEstacion(new Estacion(5, "Estación Central", "San José", 6, 3, "Parcial", true, "CCS2"));
    }

    @Override
    public String toString() {
        return toReporte();
    }
}
