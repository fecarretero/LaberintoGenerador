package arbol;

/**
 *
 * @author Jeremias Reyes, Federico Carretero
 */

//Clase encargada del manejo de los nodos de los arboles
public class NodoArbol {
    private boolean visitado;
    private NodoArbol enlaceArriba;
    private NodoArbol enlaceAbajo;
    private NodoArbol enlaceIzquierdo;
    private NodoArbol enlaceDerecho;
    private NodoArbol anterior;
    
    public NodoArbol(){
        visitado=false;
        enlaceArriba=null;
        enlaceAbajo=null;
        enlaceIzquierdo=null;
        enlaceDerecho=null;
        anterior=null;
    }

    public void setEnlaceAbajo(NodoArbol enlaceAbajo) {
        this.enlaceAbajo = enlaceAbajo;
    }

    public void setEnlaceArriba(NodoArbol enlaceArriba) {
        this.enlaceArriba = enlaceArriba;
    }

    public void setEnlaceDerecho(NodoArbol enlaceDerecho) {
        this.enlaceDerecho = enlaceDerecho;
    }

    public void setEnlaceIzquierdo(NodoArbol enlaceIzquierdo) {
        this.enlaceIzquierdo = enlaceIzquierdo;
    }

    public NodoArbol getEnlaceAbajo() {
        return enlaceAbajo;
    }

    public NodoArbol getEnlaceArriba() {
        return enlaceArriba;
    }

    public NodoArbol getEnlaceDerecho() {
        return enlaceDerecho;
    }

    public NodoArbol getEnlaceIzquierdo() {
        return enlaceIzquierdo;
    }
    
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public NodoArbol getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoArbol anterior) {
        this.anterior = anterior;
    }
    
    public boolean isVisitado() {
        return visitado;
    }
    
}
