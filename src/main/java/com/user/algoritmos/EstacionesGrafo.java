package com.user.algoritmos;

import com.user.estructures.Grafos;
import com.user.listas.ListaEstaciones;
import com.user.model.Estacion;
import java.util.ArrayList;
import java.util.List;

public class EstacionesGrafo {
    private Grafos grafo;
    private ListaEstaciones estaciones;
    private int totalEstaciones;

    public EstacionesGrafo() {
        estaciones = new ListaEstaciones();
        estaciones.cargarListaEstaciones();
        totalEstaciones = estaciones.getAEstacion();
        grafo = new Grafos(totalEstaciones);
        construirGrafo();
    }

    private int obtenerDistancia(String ubi1, String ubi2) {
        if (ubi1.equalsIgnoreCase(ubi2)) return 0;
        if (ubi1.equalsIgnoreCase("San José")) {
            if (ubi2.equalsIgnoreCase("Cartago")) return 22;
            if (ubi2.equalsIgnoreCase("Heredia")) return 11;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 18;
        }
        if (ubi1.equalsIgnoreCase("Cartago")) {
            if (ubi2.equalsIgnoreCase("San José")) return 22;
            if (ubi2.equalsIgnoreCase("Heredia")) return 30;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 40;
        }
        if (ubi1.equalsIgnoreCase("Heredia")) {
            if (ubi2.equalsIgnoreCase("San José")) return 11;
            if (ubi2.equalsIgnoreCase("Cartago")) return 30;
            if (ubi2.equalsIgnoreCase("Alajuela")) return 10;
        }
        if (ubi1.equalsIgnoreCase("Alajuela")) {
            if (ubi2.equalsIgnoreCase("San José")) return 18;
            if (ubi2.equalsIgnoreCase("Cartago")) return 40;
            if (ubi2.equalsIgnoreCase("Heredia")) return 10;
        }
        return 999;
    }

    private void construirGrafo() {
        for (int i = 0; i < totalEstaciones; i++) {
            Estacion e1 = estaciones.getEstacion(i);
            for (int j = i + 1; j < totalEstaciones; j++) {
                Estacion e2 = estaciones.getEstacion(j);
                int peso = obtenerDistancia(e1.getUbicacion(), e2.getUbicacion());
                grafo.agregarAristaConPeso(i, j, peso);
            }
        }
    }

    public void recorridoDijkstra(int inicio) {
        boolean[] visitado = new boolean[totalEstaciones];
        List<Integer> orden = new ArrayList<>();
        int actual = inicio;
        visitado[actual] = true;
        orden.add(actual);

        System.out.println("Recorrido desde " + estaciones.getEstacion(inicio).getNombre());
        System.out.println("----------------------------------------");

        for (int paso = 1; paso < totalEstaciones; paso++) {
            int[] distancias = grafo.dijkstra(actual);
            int masCercano = -1;
            int menorDist = Integer.MAX_VALUE;

            for (int i = 0; i < totalEstaciones; i++) {
                if (!visitado[i] && distancias[i] < menorDist) {
                    menorDist = distancias[i];
                    masCercano = i;
                }
            }

            if (masCercano == -1) break;

            visitado[masCercano] = true;
            orden.add(masCercano);

            Estacion desde = estaciones.getEstacion(actual);
            Estacion hasta = estaciones.getEstacion(masCercano);
            System.out.println(paso + ". " + desde.getNombre() + " (" + desde.getUbicacion() + ")"
                    + "  ->  " + hasta.getNombre() + " (" + hasta.getUbicacion() + ")"
                    + "  [" + menorDist + " km]");

            actual = masCercano;
        }

        System.out.println("----------------------------------------");
        System.out.print("\nOrden de visita: ");
        for (int i = 0; i < orden.size(); i++) {
            System.out.print(estaciones.getEstacion(orden.get(i)).getNombre());
            if (i < orden.size() - 1) System.out.print("  ->  ");
        }
        System.out.println();
    }

}
