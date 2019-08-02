package lista;

import arbol.NodoArbol;

//Clase NodoLista para el manejo de los nodos de la lista
public class NodoLista {
    //Atributos
    private NodoArbol dato;
    private NodoLista enlace;
    
    //Constructor para crear un nodo al final
    public NodoLista(NodoArbol dato){
        this(dato,null);
    }
 
    //Constructor para crear un nodo que tenga enlace
    public NodoLista(NodoArbol dato,NodoLista enlace){
        this.dato=dato;
        this.enlace=enlace;
    }
    
    
    public NodoArbol getDato(){
        return this.dato;
    }
    public NodoLista getEnlace(){
        return this.enlace;
    }
    public void setEnlace(NodoLista enlace){
        this.enlace=enlace;
    }
}
