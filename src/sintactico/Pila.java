/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintactico;

import Identificador2.TipoDeDato;
import Identificador2.Token;
import java.util.ArrayList;
import static sintactico.Pila.pop;

/**
 *
 * @author Estuardo Ramos
 */
public class Pila {

    Token siguiente;
    // estado no terminal
    String noTerminals;
    
    // terminales
    String tokenSiguiente;
    String tokenAnterior;
    String tokenActual;
    String tokensSiguientes;
    
    noTerminal nT;
    noTerminal nTAnterior;

    public void receivedToken(ArrayList<Token> token) {
        String next;
        nT=noTerminal.E;
        for (Token token1 : token) {

            System.out.println(token1.getNombre());
            if (token1.getNombre().equals(tokenSiguiente) || nT==noTerminal.E) {
                // pasmos al siguiente
                System.out.println(token1.getNombre() + " token aceptado");

                // este debe de ser el token siguiente
                movimientos(token1);
                System.out.println("El siguiente token debe de ser un " + tokenSiguiente);
            } else {
                if (tokenSiguiente == "") {
                    movimientos(token1);
                    System.out.println("INICIANDO SINTAXIS");
                    System.out.println("El siguiente token debe de ser un " + tokenSiguiente);
                    
                } else {

                    String msjError = "Token error se esperaba un " + tokenSiguiente;
                    Token tokCorrec = new Token(tokenSiguiente, "");
                    movimientos(tokCorrec);
                    System.out.println(msjError);
                }
            }

        }
        next = tokenSiguiente;

    }
    
    
    
    

    public noTerminal movimientos(Token token) {

        if (nT == noTerminal.E) {
            
            switch (token.getNombre()) {
                case "ESCRIBIR":
                    nT = noTerminal.S;
                    tokenSiguiente = TipoDeDato.LITERAL.getTipo();
                    break;
                case "REPETIR":
                    nT = noTerminal.P;
                    tokenSiguiente = TipoDeDato.ENTERO.getTipo();
                    break;
                case "SI":
                    nT = noTerminal.P;
                    break;
                case "IDENTIFICADOR":
                    nT = noTerminal.EP;
                    break;
                case "NUMERO ENTERO":
                    nT = noTerminal.EP;
                    break;
            }
        } else {
            if (nT == noTerminal.S) {
                System.out.println("----estasmos en S");
                switch (token.getNombre()) {
                    case "LITERAL":
                        nT = noTerminal.S;
                        tokenSiguiente = TipoDeDato.FIN.getTipo();
                        break;
                    case "IDENTIFICADOR":
                        nT = noTerminal.S;
                        tokenSiguiente = TipoDeDato.FIN.getTipo();
                        break;
                    
                    case "FIN":
                        if (nTAnterior == noTerminal.P) {
                            nT = nTAnterior;
                        } else {
                            nT = noTerminal.E;
                            tokenSiguiente = "";
                            System.out.println("-----------------------------------------------------------");
                            break;
                        }


                }
            } else {
                if (nT == noTerminal.P) {
                    switch (token.getNombre()) {
                    case "NUMERO ENTERO":
                        nT = noTerminal.P;
                        tokenSiguiente = TipoDeDato.INICIAR.getTipo();
                        break;
                    case "IDENTIFICADOR":
                        nT = noTerminal.P;
                        tokenSiguiente = TipoDeDato.INICIAR.getTipo();
                        break;
                    case "INICIAR":
                        nTAnterior=nT;
                        nT = noTerminal.S;
                    case "FIN":
                        nT = noTerminal.E;
                        tokenSiguiente="";
                        break;
                    }

                }
            }

        }
        return nT;
    }
    
    
    
     
    public boolean N(Token token){
        if (nT==noTerminal.S && (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) || token.getNombre().equals(TipoDeDato.LITERAL.getTipo())) {
                
            }
            return true;
    }

    //insertar token
    public void push(Token token) {

    }

    //extraer token
    public static Token pop() {
        return null;
    }

    public void escribir() {

    }

    public boolean estaVacia() {
        boolean vacia = true;
        return vacia;
    }

}
