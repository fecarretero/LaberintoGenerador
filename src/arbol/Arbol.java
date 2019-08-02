package arbol;
/**
 *
 * @author Jeremias Reyes, Federico Carretero
 */


//Clase para el manejo del arbol
public class Arbol {
    private NodoArbol raiz;
    private NodoArbol hojaFin;
    
    public Arbol(){
        this.raiz=null;
        this.hojaFin=null;
    }
    public Arbol(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }

    public void setFin(NodoArbol fin) {
        this.hojaFin = fin;
    }
    
    public NodoArbol getHojaFin() {
        return hojaFin;
    }
    
}
