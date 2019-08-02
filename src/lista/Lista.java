package lista;

import arbol.NodoArbol;

//Clase Lista para el manejo de datos tipo NodoArbol
public class Lista {
    //Atributos
    private NodoLista inicioNodo;
    private String nombre;

    //Constructor que pone un nombre por defecto
    public Lista() {
        this("Lista");
    }

    //Constructor para ponerle un nombre a la lista
    public Lista(String nombre) {
        this.nombre = nombre;
        this.inicioNodo = null;
    }

    //Metodo para saber si la lista esta vacia
    public boolean estaVacia() {
        //return this.inicioNodo==null;
        if (inicioNodo == null) {
            return true;
        } else {
            return false;
        }
    }

    //Metodo para buscar un elemento 
    public NodoLista buscarNodo(NodoArbol elementoBuscar) {
        NodoLista actual = this.inicioNodo;
        NodoLista encontrado = null;
        while (actual != null) {
            if (actual.getDato().equals(elementoBuscar)) {
                encontrado = actual;
            }
            actual = actual.getEnlace();
        }
        return encontrado;
    }

    //Metodo que nos devuelve el ultimo elemento de la lista
    public NodoLista nodoFinal() {
        NodoLista actual = this.inicioNodo;
        while (actual.getEnlace() != null) {
            actual = actual.getEnlace();
        }
        return actual;
    }

    //Metodo para insertar al inicio de la lista
    public void insertarInicioLista(NodoArbol elemento) {
        if (estaVacia()) {
            this.inicioNodo = new NodoLista(elemento);
        } else {
            NodoLista nuevo = new NodoLista(elemento);
            nuevo.setEnlace(this.inicioNodo);
            this.inicioNodo = nuevo;
        }
    }

    //Metodo para insertar un elemento al final de la lista
    public void insertarAlFinalDeLista(NodoArbol elemento) {
        if (estaVacia()) {
            this.inicioNodo = new NodoLista(elemento);
        } else {
            NodoLista ultimo;
            ultimo = this.nodoFinal();
            ultimo.setEnlace(new NodoLista(elemento));
        }
    }

    //Metodo que inserta un elemento en un lugar especifico de la lista.
    public void insertarElementoLista(NodoArbol nuevoElemento, NodoArbol elementoBuscar) {
        if (estaVacia()) {
            this.inicioNodo = new NodoLista(nuevoElemento);
        }
        NodoLista encontrado = buscarNodo(elementoBuscar);
        if (encontrado == null) {
            System.out.println("No se ha encontrado el dato en donde se va a insertar");
        } else {
            NodoLista nuevo = new NodoLista(nuevoElemento, encontrado.getEnlace());
            encontrado.setEnlace(nuevo);
        }
    }

    //Metodo que elimina el elemento final de una lista
    public void eliminarFinalDeLista() {
        NodoLista cola = nodoFinal();
        NodoLista actual = this.inicioNodo;
        while (actual != null) {
            if (actual.getEnlace().equals(cola)) {
                actual.setEnlace(null);
            }
            actual = actual.getEnlace();
        }
    }

    //Metodo que elimina el inicio de una lista
    public void eliminarInicio() {
        this.inicioNodo = this.inicioNodo.getEnlace();
    }

    //Metodo que imprime la lista
    public void imprimirLista() {
        if (estaVacia()) {
            System.out.println("La lista " + this.nombre + " esta vacia");
        } else {
            System.out.println("La lista " + this.nombre + " es:");
            NodoLista actual = this.inicioNodo;
            while (actual != null) {
                System.out.print(actual.getDato() + "\t");
                actual = actual.getEnlace();
            }
        }
    }

    public NodoLista getInicioNodo() {
        return inicioNodo;
    }

}
