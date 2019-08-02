package datos;

import arbol.NodoArbol;


//Clase encargada de la informacion de movimientos
public class InfoMovimiento {
    public NodoArbol actual;
    public int i,j,cantMovimientos;
    public String direccion;

    public InfoMovimiento(NodoArbol actual) {
        this.actual = actual;
        this.i = 0;
        this.j = 0;
        this.cantMovimientos=0;
    }    
}
