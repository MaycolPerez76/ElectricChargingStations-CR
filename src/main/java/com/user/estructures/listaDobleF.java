package dobles;

import pilas.Pila;

/**
 *
 * @author myava
 */
public class listaDobleF {

    private int TOP;     
    private int AI[];    
    private int AD[];    
    private int INFO[];  
    private int PTR;     
    private Pila DISP;  

    public listaDobleF() {
        TOP = 20;
        INFO = new int[TOP];
        AI = new int[TOP];
        AD = new int[TOP];
        PTR = 0;
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    public listaDobleF(int max) {
        TOP = max + 1;
        INFO = new int[TOP];
        AI = new int[TOP];
        AD = new int[TOP];
        PTR = 0;
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    public void inicializarPila() {
        for (int i = 1; i < TOP; i++) {
            DISP.agregarElemento(i);
        }
    }

    public String desplegarPila() {
        return DISP.toString();
    }

    public void agregarElemento(int elemento) {
        int temp = DISP.eliminarElemento();
        INFO[temp] = elemento;
        AI[temp] = 0;       
        AD[temp] = PTR;     
        if (PTR != 0) {
            AI[PTR] = temp; 
        }
        PTR = temp;         
    }

    public void agregarElementoFinal(int elemento) {
        int temp = DISP.eliminarElemento();
        INFO[temp] = elemento;
        AD[temp] = 0;       

        if (PTR == 0) {
            AI[temp] = 0;
            PTR = temp;
        } else {
            int R = PTR;
            while (AD[R] != 0) {
                R = AD[R];
            }
            AD[R] = temp;   
            AI[temp] = R;   
        }
    }

    public void agregarElementoDK(int elemento, int K) {
        if (PTR != 0 && existeK(K)) {
            int temp = DISP.eliminarElemento();
            INFO[temp] = elemento;
            AD[temp] = AD[K];       
            AI[temp] = K;           
            if (AD[K] != 0) {
                AI[AD[K]] = temp;   
            }
            AD[K] = temp;           
        }
    }

    public void agregarElementoIK(int elemento, int K) {
        if (PTR != 0 && existeK(K)) {
            int temp = DISP.eliminarElemento();
            INFO[temp] = elemento;
            AI[temp] = AI[K];       
            AD[temp] = K;           
            if (AI[K] != 0) {
                AD[AI[K]] = temp;   
            } else {
                PTR = temp;         
            }
            AI[K] = temp;           
        }
    }

    public int eliminarElemento() {
        int temp = PTR;
        int elemento = INFO[temp];
        INFO[temp] = 0;
        PTR = AD[temp];         
        if (PTR != 0) {
            AI[PTR] = 0;        
        }
        AD[temp] = 0;
        DISP.agregarElemento(temp);
        return elemento;
    }

    public int eliminarElementoFinal() {
        int R = PTR;
        while (AD[R] != 0) {
            R = AD[R];
        }
        int elemento = INFO[R];
        INFO[R] = 0;
        if (AI[R] != 0) {
            AD[AI[R]] = 0;  
        } else {
            PTR = 0;        
        }
        AI[R] = 0;
        DISP.agregarElemento(R);
        return elemento;
    }

    public int eliminarElementoK(int K) {
        int elemento = 0;
        if (PTR != 0 && existeK(K)) {
            elemento = INFO[K];
            if (AI[K] != 0) {
                AD[AI[K]] = AD[K];  
            } else {
                PTR = AD[K];        
            }
            if (AD[K] != 0) {
                AI[AD[K]] = AI[K];  
            }
            INFO[K] = 0;
            AI[K] = 0;
            AD[K] = 0;
            DISP.agregarElemento(K);
        }
        return elemento;
    }

    public int localizarElementoD(int elemento) {
        int R = PTR;
        int Enodo = 0;
        while (R != 0) {
            if (INFO[R] == elemento) {
                Enodo = R;
                break;
            }
            R = AD[R];
        }
        return Enodo;
    }

    public int localizarElementoI(int elemento) {
        int R = PTR;
        while (AD[R] != 0) {
            R = AD[R];
        }
        int Enodo = 0;
        while (R != 0) {
            if (INFO[R] == elemento) {
                Enodo = R;
                break;
            }
            R = AI[R];
        }
        return Enodo;
    }

    public boolean existeK(int K) {
        int R = PTR;
        boolean respuesta = false;
        while (R != 0) {
            if (R == K) {
                respuesta = true;
                break;
            }
            R = AD[R];
        }
        return respuesta;
    }

    public int cantidadElementos() {
        int R = PTR;
        int cantidad = 0;
        while (R != 0) {
            cantidad++;
            R = AD[R];
        }
        return cantidad;
    }

    public String desplegarListaD() {
        String sal = "{";
        int R = PTR;
        while (R != 0) {
            sal += INFO[R];
            if (AD[R] != 0) sal += ", ";
            R = AD[R];
        }
        sal += "}\n";
        sal += " # elementos = " + cantidadElementos();
        return sal;
    }

    public String desplegarListaI() {
        int R = PTR;
        while (AD[R] != 0) {
            R = AD[R];
        }
        String sal = "{";
        while (R != 0) {
            sal += INFO[R];
            if (AI[R] != 0) sal += ", ";
            R = AI[R];
        }
        sal += "}\n";
        sal += " # elementos = " + cantidadElementos();
        return sal;
    }

    public String toString() {
        String salida = "";
        for (int i = 1; i < TOP; i++) {
            salida += i + " ";
            salida += "[" + AI[i] + "]";
            salida += "[" + INFO[i] + "]";
            salida += "[" + AD[i] + "]";
            if (i == PTR) {
                salida += " <--- PTR";
            }
            salida += "\n";
        }
        salida += "Estado de la pila\n";
        salida += DISP.toString();
        return salida;
    }

    public String toStringI() {
        String salida = "";
        for (int i = TOP - 1; i > 0; i--) { 
            salida += i + " ";
            salida += "[" + AI[i] + "]";
            salida += "[" + INFO[i] + "]";
            salida += "[" + AD[i] + "]";
            if (i == PTR) {
                salida += " <--- PTR";
            }
            salida += "\n";
        }
        salida += "PTR: " + PTR + "\n";
        salida += "Estado de la pila\n";
        salida += DISP.toString();
        return salida;
    }
}
