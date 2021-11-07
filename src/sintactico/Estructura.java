/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sintactico;

import Identificador2.*;
import java.util.ArrayList;

/**
 * 
 * @author Estuardo Ramos
 */
public class Estructura {
    
    String tipo;
    ArrayList<Token> tokens;
    boolean aceptada;

    public Estructura() {
    }
    
    

    public Estructura(String tipo, ArrayList<Token> tokens, boolean aceptada) {
        this.tipo = tipo;
        this.tokens = tokens;
        this.aceptada = aceptada;
    }
    
    
    

    public Estructura(String tipo, boolean aceptada) {
        this.tipo = tipo;
        this.aceptada = aceptada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public void setAceptada(boolean aceptada) {
        this.aceptada = aceptada;
    }
    
    
    

}
