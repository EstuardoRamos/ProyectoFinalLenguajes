

package Identificador2;

/**
 * 
 * @author Estuardo Ramos
 */
public class Token {
    private String nombre;
    private String lexema;
    private int fila;
    private int columna;
    private String posicion;
    private String msj;
    private int cant;

    public Token(String nombre, String lexema, int fila, int columna, String posicion, String msj) {
        this.nombre = nombre;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
        this.posicion = posicion;
        this.msj = msj;
    }

    public Token() {
    }
    
    

    public Token(String nombre, String lexema, int fila, int columna) {
        this.nombre = nombre;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
        this.posicion="Fila: "+fila+", Columna "+columna;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Token(String nombre, String lexema) {
        this.nombre = nombre;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "Token{" + "nombre=" + nombre + ", lexema=" + lexema + ", fila=" + fila + ", columna=" + columna + ", posicion=" + posicion + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getMsj() {
        return msj;
    }

    public void setMsj(String msj) {
        this.msj = msj;
    }
    
    
    

}
