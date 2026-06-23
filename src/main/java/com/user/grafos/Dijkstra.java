package com.user.grafos;

import java.util.Arrays;

public class Dijkstra {

    private Grafo grafo;
    private int numVertices;

    public Dijkstra(Grafo grafo, int numVertices) {
        this.grafo = grafo;
        this.numVertices = numVertices;
    }

    public int[] calcular(int inicio) {

        int[][] matriz = grafo.getMatrizDistancias();

        int[] dist = new int[numVertices];
        boolean[] visitado = new boolean[numVertices];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[inicio] = 0;

        for (int i = 0; i < numVertices - 1; i++) {

            int u = minDist(dist, visitado);

            if (u == -1) break;

            visitado[u] = true;

            for (int v = 0; v < numVertices; v++) {

                if (!visitado[v] &&
                        matriz[u][v] > 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + matriz[u][v] < dist[v]) {

                    dist[v] = dist[u] + matriz[u][v];
                }
            }
        }

        return dist;
    }

    private int minDist(int[] dist, boolean[] visitado) {

        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < numVertices; i++) {
            if (!visitado[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }

        return index;
    }
}