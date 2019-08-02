package datos;

/**
 *
 * @author Jeremias Reyes, Federico Carretero
 */

//Clase que nos da la informacion del nodo donde se esta posicionado
public class InfoNodoActual {
    private String tipoNodo;
    private int siguienteNodo;
    private int fila;
    private int columna;

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setSiguienteNodo(int siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }

    public void setTipoNodo(String tipoNodo) {
        this.tipoNodo = tipoNodo;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public int getSiguienteNodo() {
        return siguienteNodo;
    }

    public String getTipoNodo() {
        return tipoNodo;
    }
    
    
}
