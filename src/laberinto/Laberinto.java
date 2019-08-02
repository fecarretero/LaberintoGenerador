package laberinto;
import arbol.Arbol;
import arbol.NodoArbol;
import datos.InfoNodoActual;
import lista.Lista;
import java.util.Random;

/**
 *
 * @author Jeremias Reyes, Federico Carretero
 */

//Clase Laberinto para creacion , solucion de laberintos cuadrados
public class Laberinto {

    //Atributos
    private int tam;
    private NodoArbol[][] matrizDeNodos;
    private Arbol arbol;

    //Constructor para declarar la matriz de nodos con un tamaño especifico
    public Laberinto(int tam) {
        this.tam = tam;
        this.matrizDeNodos = new NodoArbol[tam][tam];
        inicializarNodos();
        this.arbol = new Arbol(matrizDeNodos[0][0]);
    }

    //Constructor para declarar la matriz de nodos con un tamaño de 10X10
    public Laberinto() {
        this.tam = 10;
        this.matrizDeNodos = new NodoArbol[10][10];
        inicializarNodos();
        this.arbol = new Arbol(matrizDeNodos[0][0]);
    }

    //Metodo para inicializar los nodos del arbol
    private void inicializarNodos() {
        for (int i = 0; i < matrizDeNodos.length; i++) {
            for (int j = 0; j < matrizDeNodos[0].length; j++) {
                matrizDeNodos[i][j] = new NodoArbol();
            }
        }
    }

    //Metodo recursivo que establece los nodos y a sus enlaces directos como no visitados si no son null
    public void noVisitados(NodoArbol actual) {
        if (actual != null) {
            actual.setVisitado(false);
            noVisitados(actual.getEnlaceAbajo());
            noVisitados(actual.getEnlaceArriba());
            noVisitados(actual.getEnlaceIzquierdo());
            noVisitados(actual.getEnlaceDerecho());
        }
    }

    //Metodo que nos retorna una Lista con la solucion del laberinto
    public Lista listaSoluciones(NodoArbol actual) {
        noVisitados(actual);
        Lista listaDeSoluciones = new Lista();                                    //Lista de soluciones
        actual.setVisitado(true);
        while (actual != arbol.getHojaFin()) {                                      //Mientras no sea el final del arbol
            if (actual.getEnlaceAbajo() != null) {
                if (actual.getEnlaceAbajo().isVisitado()) {
                } else {
                    listaDeSoluciones.insertarAlFinalDeLista(actual);
                    actual = actual.getEnlaceAbajo();
                    actual.setVisitado(true);
                    continue;
                }
            }
            if (actual.getEnlaceArriba() != null) {
                if (actual.getEnlaceArriba().isVisitado()) {
                } else {
                    listaDeSoluciones.insertarAlFinalDeLista(actual);
                    actual = actual.getEnlaceArriba();
                    actual.setVisitado(true);
                    continue;
                }
            }
            if (actual.getEnlaceDerecho() != null) {
                if (actual.getEnlaceDerecho().isVisitado()) {
                } else {
                    listaDeSoluciones.insertarAlFinalDeLista(actual);
                    actual = actual.getEnlaceDerecho();
                    actual.setVisitado(true);
                    continue;
                }
            }
            if (actual.getEnlaceIzquierdo() != null) {
                if (actual.getEnlaceIzquierdo().isVisitado()) {
                } else {
                    listaDeSoluciones.insertarAlFinalDeLista(actual);
                    actual = actual.getEnlaceIzquierdo();
                    actual.setVisitado(true);
                    continue;
                }
            } else {
                listaDeSoluciones.eliminarFinalDeLista();
                actual = actual.getAnterior();
            }
        }
        
        listaDeSoluciones.insertarAlFinalDeLista(matrizDeNodos[tam - 1][tam - 1]);
        listaDeSoluciones.imprimirLista();
        return listaDeSoluciones;
    }

    //Metodo que establece los datos de los nodos del laberinto
    public InfoNodoActual datos(NodoArbol actual) {
        Random rnd = new Random();
        InfoNodoActual datos = new InfoNodoActual();
        //Establecemos los limites de los laberintos
        //Salida
        if (actual == matrizDeNodos[0][0]) {
            datos.setTipoNodo("nodo00");
            datos.setSiguienteNodo(rnd.nextInt(2) + 2);
            datos.setFila(0);
            datos.setColumna(0);
            return datos;
        //Pared derecha
        } else if (actual == matrizDeNodos[0][tam - 1]) {
            int num = rnd.nextInt(2);
            if (num == 0) {
                datos.setSiguienteNodo(0);
            }
            if (num == 1) {
                datos.setSiguienteNodo(3);
            }
            
            datos.setTipoNodo("nodo0n");
            datos.setFila(0);
            datos.setColumna(tam - 1);
            return datos;
        //Pared izquierda
        } else if (actual == matrizDeNodos[tam - 1][0]) {
            datos.setSiguienteNodo(rnd.nextInt(2) + 1);
            datos.setTipoNodo("nodon0");
            datos.setFila(tam - 1);
            datos.setColumna(0);
            return datos;
            
        //Llegada
        } else if (actual == matrizDeNodos[tam - 1][tam - 1]) {
            datos.setSiguienteNodo(rnd.nextInt(2));
            datos.setTipoNodo("nodonn");
            datos.setFila(tam - 1);
            datos.setColumna(tam - 1);
            return datos;
        }
        
        
        for (int i = 0; i < matrizDeNodos.length; i++) {
            for (int j = 0; j < matrizDeNodos[0].length; j++) {
                if (actual == matrizDeNodos[0][j]) {
                    int aleatorio = rnd.nextInt(3);
                    if (aleatorio == 0) {
                        datos.setSiguienteNodo(0);
                    }
                    if (aleatorio == 1) {
                        datos.setSiguienteNodo(2);
                    }
                    if (aleatorio == 2) {
                        datos.setSiguienteNodo(3);
                    }
                    datos.setTipoNodo("nodo0j");    
                    datos.setFila(0);
                    datos.setColumna(j);
                    return datos;
                } else if (actual == matrizDeNodos[i][0]) {
                    datos.setSiguienteNodo(rnd.nextInt(3) + 1);
                    datos.setTipoNodo("nodoi0");
                    datos.setFila(i);
                    datos.setColumna(0);
                    return datos;
                } else if (actual == matrizDeNodos[i][tam - 1]) {
                    int num = rnd.nextInt(3);
                    if (num == 0) {
                        datos.setSiguienteNodo(1);
                    }
                    if (num == 1) {
                        datos.setSiguienteNodo(0);
                    }
                    if (num == 2) {
                        datos.setSiguienteNodo(3);
                    }
                    datos.setTipoNodo("nodoin");
                    datos.setFila(i);
                    datos.setColumna(tam - 1);
                    return datos;
                } else if (actual == matrizDeNodos[tam - 1][j]) {
                    datos.setSiguienteNodo(rnd.nextInt(3));
                    datos.setTipoNodo("nodonj");
                    datos.setFila(tam - 1);
                    datos.setColumna(j);
                    return datos;
                } else if (actual == matrizDeNodos[i][j]) {
                    datos.setSiguienteNodo(rnd.nextInt(4));
                    datos.setTipoNodo("nodonormal");
                    datos.setFila(i);
                    datos.setColumna(j);
                    return datos;
                }
            }
        }
        return datos;
    }

    //Metodo que genera un laberinto
    public void generarLaberinto() {
        NodoArbol actual = arbol.getRaiz();
        actual.setVisitado(true);
        int cantVisitados = 1;
        InfoNodoActual datos;
        while (cantVisitados != tam * tam) {
            datos = datos(actual);
            
            switch (datos.getTipoNodo()) {
                //Salida
                case "nodo00":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                //Pared derecha
                case "nodo0n":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                //Pared izquierda
                case "nodon0":
                    if (matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                //Llegada
                case "nodonn":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                
                case "nodo0j":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                case "nodoi0":
                    if (matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                case "nodonj":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                case "nodoin":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
                case "nodonormal":
                    if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado() && matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado() && matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado()) {
                        actual = actual.getAnterior();
                        continue;
                    }
                    break;
            }
            
            //Establecemos el enlace izq
            if (datos.getSiguienteNodo() == 0) {
                if (matrizDeNodos[datos.getFila()][datos.getColumna() - 1].isVisitado()) {
                    continue;
                } else {
                    
                    actual.setEnlaceIzquierdo(matrizDeNodos[datos.getFila()][datos.getColumna() - 1]);
                    System.out.println(matrizDeNodos[datos.getFila()][datos.getColumna() - 1].getEnlaceIzquierdo());
                    NodoArbol anterior = actual;
                    actual = actual.getEnlaceIzquierdo();
                    actual.setAnterior(anterior);
                    actual.setVisitado(true);
                    cantVisitados++;
                }
            //Establecemos el enlace arriba
            } else if (datos.getSiguienteNodo() == 1) {
                if (matrizDeNodos[datos.getFila() - 1][datos.getColumna()].isVisitado()) {
                    continue;
                } else {
                    
                    actual.setEnlaceArriba(matrizDeNodos[datos.getFila() - 1][datos.getColumna()]);
                    System.out.println(matrizDeNodos[datos.getFila() - 1][datos.getColumna()].getEnlaceArriba());
                    NodoArbol anterior = actual;
                    actual = actual.getEnlaceArriba();
                    actual.setAnterior(anterior);
                    actual.setVisitado(true);
                    cantVisitados++;
                }
            //Establecemos el enlace derecho
            } else if (datos.getSiguienteNodo() == 2) {
                if (matrizDeNodos[datos.getFila()][datos.getColumna() + 1].isVisitado()) {
                    continue;
                } else {
                    
                    actual.setEnlaceDerecho(matrizDeNodos[datos.getFila()][datos.getColumna() + 1]);
                    System.out.println(matrizDeNodos[datos.getFila()][datos.getColumna() + 1].getEnlaceDerecho());
                    NodoArbol anterior = actual;
                    actual = actual.getEnlaceDerecho();
                    actual.setAnterior(anterior);
                    actual.setVisitado(true);
                    cantVisitados++;
                }
            //Establecemos el enlace de abajo
            } else if (datos.getSiguienteNodo() == 3) {
                if (matrizDeNodos[datos.getFila() + 1][datos.getColumna()].isVisitado()) {
                    continue;
                } else {
                    
                    actual.setEnlaceAbajo(matrizDeNodos[datos.getFila() + 1][datos.getColumna()]);
                    System.out.println(matrizDeNodos[datos.getFila() + 1][datos.getColumna()].getEnlaceAbajo());
                    NodoArbol anterior = actual;
                    actual = actual.getEnlaceAbajo();
                    actual.setAnterior(anterior);
                    actual.setVisitado(true);
                    cantVisitados++;
                }
            }
        }
        arbol.setFin(matrizDeNodos[tam - 1][tam - 1]);
        
        /*for (int i = 0; i < matrizDeNodos.length; i++) {
            for (int j = 0; j < matrizDeNodos.length; j++) {
                if(matrizDeNodos[i][j].equals(null)){
                    System.out.print(" ");
                }
                else{
                    System.out.print("*");
                }
                
            }
            System.out.println("");
        }*/
        
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int n) {
        this.tam = n;
    }

    public Arbol getArbol() {
        return arbol;
    }

    public NodoArbol[][] getMatrizDeNodos() {
        return matrizDeNodos;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }

}
