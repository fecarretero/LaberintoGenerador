package laberinto;


import laberinto.Laberinto;
import arbol.Arbol;
import arbol.NodoArbol;
import datos.InfoMovimiento;
import datos.InfoPintar;
import lista.Lista;
import java.awt.Color;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Federico Carretero, Jeremias Reyes
 */

//Clase encargada de la parte grafica del laberinto
public class PanelLaberinto extends javax.swing.JPanel {
    
    //Atributos
    private Laberinto laberinto;
    private int ancho;
    private boolean solucion;
    private boolean movimiento;
    private boolean solucionar;
    private InfoMovimiento datosMov;

    
    //Constructor
    public PanelLaberinto() {
        this.laberinto = new Laberinto(5);
        this.ancho = 500;
        datosMov = new InfoMovimiento(laberinto.getArbol().getRaiz());
        initComponents();
    }

    //Pintamos el laberinto
    public void pintarLaberinto(Graphics g, NodoArbol actual, InfoPintar datos, NodoArbol[][] nodos) {
        if (actual != null) {
            for (int i = 0; i < nodos.length; i++) {
                for (int j = 0; j < nodos[0].length; j++) {
                    if (actual == nodos[i][j]) {
                        datos.x = j + 1;
                        datos.y = i + 1;
                        break;
                    }
                }
            }
            if (actual.getEnlaceAbajo() != null) {
                g.drawLine(datos.espacio * (datos.x - 1) + 1, datos.espacio * (datos.y), datos.espacio * (datos.x) - 1, datos.espacio * (datos.y));
            }
            if (actual.getEnlaceArriba() != null) {
                g.drawLine(datos.espacio * (datos.x - 1) + 1, datos.espacio * (datos.y - 1), datos.espacio * datos.x - 1, datos.espacio * (datos.y - 1));
            }
            if (actual.getEnlaceDerecho() != null) {
                g.drawLine(datos.espacio * datos.x, datos.espacio * (datos.y - 1) + 1, datos.espacio * datos.x, datos.espacio * datos.y - 1);
            }
            if (actual.getEnlaceIzquierdo() != null) {
                g.drawLine(datos.espacio * (datos.x - 1), datos.espacio * (datos.y - 1) + 1, datos.espacio * (datos.x - 1), datos.espacio * datos.y - 1);
            }
            
            pintarLaberinto(g, actual.getEnlaceAbajo(), datos, nodos);
            pintarLaberinto(g, actual.getEnlaceArriba(), datos, nodos);
            pintarLaberinto(g, actual.getEnlaceIzquierdo(), datos, nodos);
            pintarLaberinto(g, actual.getEnlaceDerecho(), datos, nodos);
        }
    }
    
    //Pintamos la solucion del laberinto
    public void pintarSolucion(Graphics g) {
        Arbol arbol = laberinto.getArbol();
        NodoArbol[][] matNodos = laberinto.getMatrizDeNodos();
        NodoArbol actual = arbol.getRaiz();
        g.setColor(Color.GREEN);
        int espacio = ancho / laberinto.getTam();
        laberinto.noVisitados(arbol.getRaiz());
        Lista listaSolucion = new Lista();
        actual.setVisitado(true);
        while (actual != arbol.getHojaFin()) {
            if (actual.getEnlaceAbajo() == null && actual.getEnlaceArriba() == null && actual.getEnlaceDerecho() == null && actual.getEnlaceIzquierdo() == null) {
                g.setColor(Color.GREEN);    //Black
            }
            if (actual.getEnlaceAbajo() != null) {
                if (actual.getEnlaceAbajo().isVisitado()) {
                    g.setColor(Color.GREEN);   //BLAKC
                } else {
                    g.setColor(Color.GREEN);
                }
            }
            if (actual.getEnlaceArriba() != null) {
                if (actual.getEnlaceArriba().isVisitado()) {
                    g.setColor(Color.GREEN);    //bLACK
                } else {
                    g.setColor(Color.GREEN);
                }
            }
            if (actual.getEnlaceDerecho() != null) {
                if (actual.getEnlaceDerecho().isVisitado()) {
                    g.setColor(Color.GREEN);    //Black
                } else {
                    g.setColor(Color.GREEN);
                }
            }
            if (actual.getEnlaceIzquierdo() != null) {
                if (actual.getEnlaceIzquierdo().isVisitado()) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.GREEN);
                }
            }
            int i, j = 0;
            BuclePrincipal:
            for (i = 0; i < matNodos.length; i++) {
                for (j = 0; j < matNodos[0].length; j++) {
                    if (actual == matNodos[i][j]) {
                        g.fillRect(j * espacio + espacio / 4, i * espacio + espacio / 4, espacio / 2, espacio / 2);
                        break BuclePrincipal;
                    }
                }
            }
            
            if (actual.getEnlaceAbajo() != null) {
                if (!actual.getEnlaceAbajo().isVisitado()) {
                    listaSolucion.insertarAlFinalDeLista(actual);
                    g.fillRect(j * espacio + espacio / 4, i * espacio + espacio / 4, espacio / 2, espacio);
                    actual = actual.getEnlaceAbajo();
                    actual.setVisitado(true);
                    continue;
                } 
                
            }
            if (actual.getEnlaceArriba() != null) {
                if (!actual.getEnlaceArriba().isVisitado()) {
                    listaSolucion.insertarAlFinalDeLista(actual);
                    g.fillRect(j * espacio + espacio / 4, (i - 1) * espacio + espacio / 4, espacio / 2, espacio);
                    actual = actual.getEnlaceArriba();
                    actual.setVisitado(true);
                    continue;
                } 
            }
            if (actual.getEnlaceDerecho() != null) {
                if (!actual.getEnlaceDerecho().isVisitado()) {
                    listaSolucion.insertarAlFinalDeLista(actual);
                    g.fillRect(j * espacio + espacio / 4, i * espacio + espacio / 4, espacio, espacio / 2);
                    actual = actual.getEnlaceDerecho();
                    actual.setVisitado(true);
                    continue;
                } 
            }
            if (actual.getEnlaceIzquierdo() != null) {
                if (!actual.getEnlaceIzquierdo().isVisitado()) {
                    listaSolucion.insertarAlFinalDeLista(actual);
                    g.fillRect((j - 1) * espacio + espacio / 4, i * espacio + espacio / 4, espacio, espacio / 2);
                    actual = actual.getEnlaceIzquierdo();
                    actual.setVisitado(true);
                    continue;
                } 
            }
            listaSolucion.eliminarFinalDeLista();
            actual = actual.getAnterior();
        }
        listaSolucion.insertarAlFinalDeLista(actual);
        g.fillRect((laberinto.getTam() - 1) * espacio + espacio / 4, (laberinto.getTam() - 1) * espacio + espacio / 4, espacio / 2, espacio / 2);
    }
    
    
    public void pintarSolucion2(Graphics g){
        Arbol arbol = laberinto.getArbol();
        NodoArbol[][] matNodos = laberinto.getMatrizDeNodos();
        NodoArbol actual = arbol.getRaiz();
        g.setColor(Color.GREEN);
        int espacio = ancho / laberinto.getTam();
        laberinto.noVisitados(arbol.getRaiz());
        Lista listaSolucion = new Lista();
        actual.setVisitado(true);
        
        
        //Pintamos la matriz visual de nodos
        for (int i = 0; i < matNodos.length; i++) {
            for (int j = 0; j < matNodos.length; j++) {
                g.fillRect(j * espacio + espacio / 4, i * espacio + espacio / 4, espacio / 2, espacio / 2);
                
            }
            try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }       
        }
    }
    
    
    public void pintarMovimiento(Graphics g) {
        int espacio = ancho / laberinto.getTam();
        if (datosMov.direccion.equals("arriba")) {
            datosMov.actual = datosMov.actual.getEnlaceArriba();
            g.setColor(Color.BLACK);             //Color que del moviento anterior del movimiento
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.i--;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);   //Pintamos el cuadrado
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("der")) {
            datosMov.actual = datosMov.actual.getEnlaceDerecho();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.j++;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("izq")) {
            datosMov.actual = datosMov.actual.getEnlaceIzquierdo();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.j--;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("abajo")) {
            datosMov.actual = datosMov.actual.getEnlaceAbajo();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.i++;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("abajoAnt")) {
            datosMov.actual = datosMov.actual.getAnterior();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.i++;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("arribaAnt")) {
            datosMov.actual = datosMov.actual.getAnterior();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.i--;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("izqAnt")) {
            datosMov.actual = datosMov.actual.getAnterior();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.j++;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
        if (datosMov.direccion.equals("derAnt")) {
            datosMov.actual = datosMov.actual.getAnterior();
            g.setColor(Color.BLACK);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.j--;
            g.setColor(Color.GREEN);
            g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            datosMov.cantMovimientos++;
        }
    }
    
    
    //Metodo que pinta el frame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int espacio = ancho / laberinto.getTam();
        Arbol arbol = this.laberinto.getArbol();
        NodoArbol actual = arbol.getRaiz();
        g.setColor(Color.MAGENTA);
        g.fillRect(1, 1, espacio, espacio/5);           //Pintamos un rectangulo de salida
        g.setColor(Color.CYAN);
        g.fillRect(espacio * (laberinto.getTam() - 1), espacio * laberinto.getTam() - espacio / 4, espacio, espacio / 4);   //Pintamos el rectangulo de llegada
        g.setColor(Color.white);    //black
        
        //Empezamos a dibujar las lineas en forma de matriz
        for (int i = 0; i <= laberinto.getTam(); i++) {
            for (int j = 0; j < laberinto.getTam(); j++) {
                g.drawLine(espacio * j, espacio * i, espacio * (j + 1), espacio * i);
            }
        }
        
        for (int i = 0; i <= laberinto.getTam(); i++) {
            for (int j = 0; j < laberinto.getTam(); j++) {
                g.drawLine(espacio * i, espacio * j, espacio * i, espacio * (j + 1));
            }
        }
        
        g.setColor(Color.black);    //white
        
        
        if (arbol.getHojaFin() != null) {
            int x = 1, y = 1;
            InfoPintar datos = new InfoPintar(x, y, espacio);
            pintarLaberinto(g, arbol.getRaiz(), datos, laberinto.getMatrizDeNodos());
        }
        
        if (solucion) {
            pintarSolucion(g);
        }
        if (solucionar) {
            if (datosMov.i == 0 && datosMov.j == 0) {
                g.setColor(Color.GREEN);
                g.fillRect(datosMov.j * espacio + espacio / 4, datosMov.i * espacio + espacio / 4, espacio / 2, espacio / 2);
            }
        }
        if (movimiento) {
            pintarMovimiento(g);
        }
    }

    public void setLaberinto(int n) {
        Laberinto laberint = new Laberinto(n);
        this.laberinto = laberint;
    }

    public Laberinto getLaberinto() {
        return laberinto;
    }

    public void setSolucionar(boolean pintarOSolucionar) {
        this.solucion = pintarOSolucionar;
    }

    public boolean isSolucionar() {
        return solucion;
    }

    public InfoMovimiento getDatosMov() {
        return datosMov;
    }

    public boolean isJugar() {
        return solucionar;
    }

    public void setJugar(boolean jugar) {
        this.solucionar = jugar;
    }

    public void setDatosMov(InfoMovimiento datosMov) {
        this.datosMov = datosMov;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    

    public void setMovimiento(boolean movimiento) {
        this.movimiento = movimiento;
    }
}
