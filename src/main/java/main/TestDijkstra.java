package main;

import com.user.grafos.Dijkstra;
import com.user.grafos.Grafo;
import java.util.Scanner;

public class TestDijkstra {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // =========================
        // GRAFO (CIUDADES SAN JOSÉ)
        // =========================
        Grafo grafo = new Grafo(6);

        grafo.agregarCiudad("Escazú");
        grafo.agregarCiudad("San José");
        grafo.agregarCiudad("Curridabat");
        grafo.agregarCiudad("Desamparados");
        grafo.agregarCiudad("Moravia");
        grafo.agregarCiudad("Tibás");

        grafo.conectarCiudades("Escazú", "San José", 8);
        grafo.conectarCiudades("San José", "Curridabat", 6);
        grafo.conectarCiudades("San José", "Moravia", 5);
        grafo.conectarCiudades("San José", "Tibás", 3);
        grafo.conectarCiudades("San José", "Desamparados", 7);
        grafo.conectarCiudades("Curridabat", "Moravia", 4);

        // =========================
        // MENÚ DE PRUEBA
        // =========================
        System.out.println("==================================");
        System.out.println(" PRUEBA SISTEMA DE RUTAS");
        System.out.println("==================================");

        System.out.println("\nSeleccione su ubicación:");
        for (int i = 0; i < grafo.getCantidadCiudades(); i++) {
            System.out.println((i + 1) + ". " + grafo.getCiudad(i));
        }

        int opcion = sc.nextInt() - 1;
        String origen = grafo.getCiudad(opcion);

        // =========================
        // ESTACIONES (FIJAS)
        // =========================
        String[] estaciones = {
            "San José",
            "Curridabat",
            "Moravia"
        };

        System.out.println("\nEstaciones registradas:");
        for (String e : estaciones) {
            System.out.println("- " + e);
        }

        // =========================
        // DIJKSTRA
        // =========================
        Dijkstra dijkstra = new Dijkstra(grafo, grafo.getCantidadCiudades());

        int inicio = grafo.buscarCiudad(origen);
        int[] distancias = dijkstra.calcular(inicio);

        // =========================
        // BUSCAR ESTACIÓN MÁS CERCANA
        // =========================
        String mejorEstacion = null;
        int mejorDistancia = Integer.MAX_VALUE;

        System.out.println("\nCalculando rutas...\n");

        for (String est : estaciones) {

            int idx = grafo.buscarCiudad(est);

            if (idx != -1) {

                int dist = distancias[idx];

                System.out.println("Ruta a " + est + " = "
                        + (dist == Integer.MAX_VALUE ? "infinito" : dist)
                        + " km");

                if (dist < mejorDistancia) {
                    mejorDistancia = dist;
                    mejorEstacion = est;
                }
            }
        }

        // =========================
        // RESULTADO FINAL
        // =========================
        System.out.println("\n==================================");
        System.out.println(" RESULTADO FINAL");
        System.out.println("==================================");

        System.out.println("Ubicación: " + origen);
        System.out.println("Estación más cercana: " + mejorEstacion);
        System.out.println("Distancia: " + mejorDistancia + " km");

        System.out.println("==================================");
    }
}