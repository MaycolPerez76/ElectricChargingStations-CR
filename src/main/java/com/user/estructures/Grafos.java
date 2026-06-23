package com.user.estructures;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
 
/**
 *
 * @author mayco
 */
public class Grafos {
 
    /*
     * Clase que representa un grafo mediante lista de adyacencia.
     * Soporta aristas sin peso (para BFS/distanciasMinimas)
     * y aristas con peso (para Dijkstra).
     */
    private int numVertices;
    private List<List<Integer>> adj;          // lista sin peso: para BFS
    private List<List<Node>> adjPeso;         // lista con peso: para Dijkstra
 
    // Constructor: recibe el número de nodos
    public Grafos(int numVertices) {
        this.numVertices = numVertices;
        adj = new ArrayList<>(numVertices);
        adjPeso = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adj.add(new LinkedList<>());
            adjPeso.add(new ArrayList<>());
        }
    }
 
    // Método para agregar una arista no dirigida sin peso (para BFS)
    public void agregarArista(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
 
    // Método para agregar una arista dirigida sin peso
    public void agregarAristaDirigida(int u, int v) {
        adj.get(u).add(v);
    }
 
    // Método para agregar una arista no dirigida CON PESO (para Dijkstra)
    public void agregarAristaConPeso(int u, int v, int peso) {
        adjPeso.get(u).add(new Node(v, peso));
        adjPeso.get(v).add(new Node(u, peso));
    }
 
    // Método para agregar una arista dirigida CON PESO (para Dijkstra)
    public void agregarAristaDirigidaConPeso(int u, int v, int peso) {
        adjPeso.get(u).add(new Node(v, peso));
    }
 
    /*
     * Algoritmo BFS: recorre el grafo a partir del nodo 'inicio'
     * y muestra el orden en que se visitan los nodos.
     *
     * Parámetros:
     *   inicio: el nodo desde donde comenzar la búsqueda
     *
     * Funcionamiento paso a paso:
     *   1. Crear una cola (Queue) para almacenar los nodos por visitar.
     *   2. Crear un arreglo booleano para marcar los nodos ya visitados.
     *   3. Marcar el nodo inicial como visitado y encolarlo.
     *   4. Mientras la cola no esté vacía:
     *       a) Sacar el primer nodo de la cola (desencolar).
     *       b) Procesar ese nodo (aquí lo imprimimos).
     *       c) Recorrer todos sus vecinos no visitados:
     *             - Marcarlos como visitados.
     *             - Encolarlos.
     *   5. Cuando la cola se vacía, hemos recorrido todos los nodos alcanzables.
     */
    public void bfs(int inicio) {
        // Cola para manejar el orden de visita (FIFO)
        Queue<Integer> cola = new LinkedList<>();
 
        // Arreglo para saber si un nodo ya fue visitado
        boolean[] visitado = new boolean[numVertices];
 
        // Marcar el nodo inicial y encolarlo
        visitado[inicio] = true;
        cola.add(inicio);
 
        System.out.print("Recorrido BFS desde el nodo " + inicio + ": ");
 
        // Mientras haya nodos en la cola
        while (!cola.isEmpty()) {
            // 1. Extraer el primer nodo de la cola
            int nodoActual = cola.poll();
 
            // 2. Procesarlo (en este caso, imprimirlo)
            System.out.print(nodoActual + " ");
 
            // 3. Recorrer todos los vecinos de nodoActual
            for (int vecino : adj.get(nodoActual)) {
                // Si el vecino no ha sido visitado...
                if (!visitado[vecino]) {
                    // ...marcarlo como visitado y encolarlo
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }
 
    // BFS que devuelve las distancias mínimas (en saltos) desde el nodo inicio
    public int[] distanciasMinimas(int inicio) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, -1); // -1 significa "no alcanzable"
        Queue<Integer> cola = new LinkedList<>();
 
        dist[inicio] = 0;
        cola.add(inicio);
 
        while (!cola.isEmpty()) {
            int u = cola.poll();
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {   // no visitado aún
                    dist[v] = dist[u] + 1;
                    cola.add(v);
                }
            }
        }
        return dist;
    }
 
    /**
     * Algoritmo de Dijkstra (Enfoque Greedy)
     * Encuentra la distancia más corta (con pesos) desde un nodo inicio
     * a todos los demás. Requiere haber agregado aristas con agregarAristaConPeso().
     *
     * Precondición: todos los pesos deben ser >= 0.
     */
    public int[] dijkstra(int inicio) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[inicio] = 0;
 
        // Cola de prioridad: procesa primero el nodo con menor distancia acumulada
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(inicio, 0));
 
        boolean[] visitado = new boolean[numVertices];
 
        while (!pq.isEmpty()) {
            // Extracción Greedy: tomamos el nodo con la menor distancia estimada
            Node actual = pq.poll();
            int u = actual.vertice;
 
            if (visitado[u]) continue;
            visitado[u] = true;
 
            // Relajación de aristas
            //Para cada vecino obtenemos sus caminos o pesos
            for (Node vecino : adjPeso.get(u)) {
                int v = vecino.vertice;
                int pesoArista = vecino.peso;
 
                if (!visitado[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + pesoArista < dist[v]) {
                    dist[v] = dist[u] + pesoArista;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        return dist;
    }
 
    // Clase auxiliar para Dijkstra: representa un nodo con su distancia/peso
    static class Node implements Comparable<Node> {
        int vertice;
        int peso;
 
        Node(int v, int p) {
            this.vertice = v;
            this.peso = p;
        }
 
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.peso, o.peso);
        }
    }
    }