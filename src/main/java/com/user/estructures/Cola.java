/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pilas;

/**
 *
 * @author mayco
 */
public class Cola {
    private int cola[];
    private int E;
    private int S;
    private int TOP;
    
    
    public Cola() {
        TOP = 20;
        cola = new int[TOP+1];
        E = 0;
    }
    
    public Cola(int tamaño) {
        TOP = tamaño;
        cola = new int[TOP+1];
        E = 0;
        S = 0;
    }
    
    
    public boolean colaVacia() {
        return E == 0 && S == 0;
    }
  public boolean colaLlena() {
      return E == TOP && S == 1 || E+1 == S;
  }
  public boolean eTOP() {
      return E==TOP;
  }
  public boolean sTOP() {
      return S==TOP; 
  }
  public boolean soloUnElemento() {
      return E > 0 && S==E;
  }  
    public void agregarElemento(int y) {
        if (colaVacia()) {
            S = 1;
            E = 1;
        } else {
            if (eTOP() && !colaLlena()) {
              E = 0;
            }
                    E++;
                }
     cola[E] = y;
    }
    
    
    public int eliminarElemento() {
        int y = 0;
        if (!colaVacia()) {
            if (soloUnElemento()) {
                y = cola[S];
                S = 0;
                E = 0;
                return y;
            }
            y = cola[S++];
        }
        return y;
    }

    public String toString(){
String salida="cola\n";
for(int i=cola.length-1;i>0;i--){
  salida+=i+"["+cola[i]+"]";
   if(i==S){
   salida+="<==S";
   }
 if(i==E){
    salida+="<==E";
 }
  
salida+="\n";
}

 salida+="E="+E+"\n";
 salida+="S="+S+"\n";
return salida;
}
    
    
       public static void main (String args[]) {
        Cola cola = new Cola();
        cola.agregarElemento(8);
        cola.agregarElemento(3);
        cola.agregarElemento(56);     
        cola.agregarElemento(16);     
        cola.agregarElemento(34);  
        cola.agregarElemento(16); 
        cola.agregarElemento(55); 
        cola.agregarElemento(21); 
        cola.agregarElemento(8); 
        cola.agregarElemento(12); 
        cola.agregarElemento(42); 
        cola.agregarElemento(65); 
        cola.agregarElemento(63); 
        cola.agregarElemento(88); 
        cola.agregarElemento(23); 
        cola.agregarElemento(98); 
        System.out.println(cola);
        
        System.out.println("Eliminado el elemento" + cola.eliminarElemento());
        System.out.println("Eliminado el elemento" + cola.eliminarElemento());
        System.out.println("Eliminado el elemento" + cola.eliminarElemento());  
        
        System.out.println(cola);
    }
    
    
    
    
    
    
    
    
}
