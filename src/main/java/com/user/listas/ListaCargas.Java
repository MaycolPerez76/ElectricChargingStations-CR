package com.user.listas;

/**
 *
 * @author myava
 */
import com.user.model.*;

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
        if (idCarga == null) {
            return -1;
        }
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
        if (estacion == null) {
            return new ListaCargas(tamMaximo);
        }
        ListaCargas temporal = new ListaCargas(tamMaximo);
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null
                    && listaCargas[i].getEstacion() != null
                    && listaCargas[i].getEstacion().getIdEstacion() == estacion.getIdEstacion()) {
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

    public ListaCargas filtroXVehiculo(String placa) {
        if (placa == null) {
            return new ListaCargas(tamMaximo);
        }
        ListaCargas temporal = new ListaCargas(tamMaximo);
        for (int i = 0; i < aCarga; i++) {
            if (listaCargas[i] != null) {
                Vehiculo v = listaCargas[i].getVehiculo();
                if (v != null && v.getPlaca().equalsIgnoreCase(placa)) {
                    temporal.agregarCarga(listaCargas[i]);
                }
            }
        }
        return temporal;
    }

    public void cargarListaCargas(ListaUsuarios usuarios, ListaVehiculos vehiculos, ListaEstaciones estaciones) {
        if (usuarios == null || vehiculos == null || estaciones == null) {
            System.out.println("Error: Listas de usuarios, vehículos o estaciones nulas.");
            return;
        }

        if (usuarios.getAUsuario() < 3 || vehiculos.getAVehiculo() < 3 || estaciones.getAEstacion() < 3) {
            System.out.println("Error: No hay suficientes datos para cargar cargas de prueba.");
            return;
        }

        Vehiculo v1 = vehiculos.getVehiculo(0);
        Vehiculo v2 = vehiculos.getVehiculo(1);
        Vehiculo v3 = vehiculos.getVehiculo(2);

        Estacion e1 = estaciones.getEstacion(0);
        Estacion e2 = estaciones.getEstacion(1);
        Estacion e3 = estaciones.getEstacion(2);

        Carga c1 = new Carga("C001", v1, e1, 5000.0, 25.5, 1.5, "2024-01-15 10:30", 2);
        Carga c2 = new Carga("C002", v2, e2, 6000.0, 40.0, 2.0, "2024-01-15 14:00", 2);
        Carga c3 = new Carga("C003", v3, e3, 4500.0, 18.0, 1.0, "2024-01-16 09:00", 2);
        Carga c4 = new Carga("C004", v1, e1, 5500.0, 30.0, 1.8, "2024-01-16 16:30", 2);
        Carga c5 = new Carga("C005", v2, e3, 7000.0, 55.0, 2.5, "2024-01-17 11:00", 1);
        Carga c6 = new Carga("C006", v3, e2, 4800.0, 22.0, 1.2, "2024-01-17 13:30", 2);
        Carga c7 = new Carga("C007", v1, e2, 5200.0, 35.0, 2.0, "2024-01-18 08:00", 2);
        Carga c8 = new Carga("C008", v2, e1, 6800.0, 45.0, 2.2, "2024-01-18 12:00", 3);
        Carga c9 = new Carga("C009", v3, e1, 4200.0, 20.0, 1.0, "2024-01-19 10:00", 2);
        Carga c10 = new Carga("C010", v1, e3, 7500.0, 50.0, 2.5, "2024-01-19 15:30", 2);

        agregarCarga(c1);
        agregarCarga(c2);
        agregarCarga(c3);
        agregarCarga(c4);
        agregarCarga(c5);
        agregarCarga(c6);
        agregarCarga(c7);
        agregarCarga(c8);
        agregarCarga(c9);
        agregarCarga(c10);

        System.out.println("Cargas de prueba cargadas exitosamente: " + aCarga + " cargas.");
    }

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
                sal = sal + listaCargas[i].toString()+ "\n";
            }
        }
        return sal;
    }

    @Override
    public String toString() {
        return toReporte();
    }
}
