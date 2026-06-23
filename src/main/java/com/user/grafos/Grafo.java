package com.user.grafos;

public class Grafo {

    private String ciudades[];
    private int matrizDistancias[][];
    private int cantidadCiudades;

    public Grafo(int maxCiudades) {
        ciudades = new String[maxCiudades];
        matrizDistancias = new int[maxCiudades][maxCiudades];
        cantidadCiudades = 0;
    }

    public void agregarCiudad(String ciudad) {
        if (cantidadCiudades < ciudades.length) {
            ciudades[cantidadCiudades++] = ciudad;
        }
    }

    public void conectarCiudades(String origen, String destino, int distancia) {

        int i = buscarCiudad(origen);
        int j = buscarCiudad(destino);

        if (i != -1 && j != -1) {
            matrizDistancias[i][j] = distancia;
            matrizDistancias[j][i] = distancia;
        }
    }

    public int buscarCiudad(String ciudad) {

        for (int i = 0; i < cantidadCiudades; i++) {
            if (ciudades[i].equalsIgnoreCase(ciudad)) {
                return i;
            }
        }

        return -1;
    }

    public String getCiudad(int pos) {
        return ciudades[pos];
    }

    public int getCantidadCiudades() {
        return cantidadCiudades;
    }

    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }
}