/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sintactico;

import Identificador2.TipoDeDato;
import Identificador2.Token;
import java.util.ArrayList;

/**
 * 
 * @author Estuardo Ramos
 */
public class Pila2 {
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
    
    String sintaxis;
    
    
    public void receibeToken(ArrayList<Token> token) {
        String next;
        nT=noTerminal.E;
        
        
        for (Token token1 : token) {

            System.out.println(token1.getNombre());
            if (nT==noTerminal.E) {
                sintaxis+=" \n";
            }
            
            if (esSiguiente(token1) || nT==noTerminal.E) {
                // pasmos al siguiente
                System.out.println(token1.getNombre() + " token aceptado");
                sintaxis+=token1.getLexema()+" ";

                // este debe de ser el token siguiente
                System.out.println("El siguiente token debe de ser un " + tokensSiguientes);
            } else {
                if (tokenSiguiente == "") {
                    System.out.println("INICIANDO SINTAXIS");
                    System.out.println("El siguiente token debe de ser un " + tokensSiguientes);
                    
                } else {

                    String msjError = "Token error se esperaba un " + tokensSiguientes;
                    Token tokCorrec = new Token(tokenSiguiente, "");
                    esSiguiente(tokCorrec);
                    //movimientos(tokCorrec);
                    System.out.println(msjError);
                    sintaxis+=token1.getLexema()+" <-error ";
                }
            }
            

        }
        System.out.println("----------Sintaxis-------------------------------");
        System.out.println(sintaxis);
        
        next = tokenSiguiente;

    }
    
    
    
    public boolean esSiguiente(Token token) {
        if (nT == noTerminal.E) {
            if (token.getNombre().equals(TipoDeDato.ESCRIBIR.getTipo())) {
                nT = noTerminal.S;
                tokenSiguiente = TipoDeDato.LITERAL.getTipo();
                tokensSiguientes = TipoDeDato.LITERAL.getTipo() + " 0 " + TipoDeDato.IDENTIFICADOR.getTipo();
                return true;
            } else {
                switch (token.getNombre()) {
                    case "ESCRIBIR":
                        nT = noTerminal.S;
                        tokenSiguiente = TipoDeDato.LITERAL.getTipo();
                        break;
                    case "REPETIR":
                        nT = noTerminal.P;
                        tokenSiguiente = TipoDeDato.ENTERO.getTipo();
                        return true;
                    case "SI":
                        nT = noTerminal.P;
                       return true;
                    case "IDENTIFICADOR":
                        nT = noTerminal.EP;
                        break;
                    case "NUMERO ENTERO":
                        nT = noTerminal.X;
                        tokenSiguiente = TipoDeDato.OPERADOR.getTipo();
                        tokensSiguientes = TipoDeDato.OPERADOR.getTipo();
                       return true;
                }
            }
        } else {

            // entramos a mno terminal S
            if (nT == noTerminal.S) {
                System.out.println("----estasmos en S");
                 return Escribir(token);

                /*if (token.getNombre().equals(TipoDeDato.LITERAL.getTipo()) || token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                    nT = noTerminal.S;
                    tokenSiguiente = TipoDeDato.FIN.getTipo();
                    tokensSiguientes = TipoDeDato.FIN.getTipo();
                    return true;
                } else {
                    if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                        if (nTAnterior == noTerminal.P) {
                            nT = nTAnterior;
                        } else {
                            nT = noTerminal.E;
                            tokenSiguiente = "";
                            tokensSiguientes = "";
                            System.out.println("-------------------------------------");

                        }
                        return true;
                    }
                }*/
            } else {
                // entramos al no terminal P Repetir o condicion
                if (nT == noTerminal.P) {
                    
                    if (!Repetir(token)) {
                        return Condicion(token);
                    }else{
                        return Repetir(token);
                    }
                     

                   /* if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo()) || token.getNombre().equals(TipoDeDato.ENTERO.getTipo())) {
                        nT = noTerminal.P;
                        tokenSiguiente = TipoDeDato.INICIAR.getTipo();
                        tokensSiguientes = TipoDeDato.INICIAR.getTipo();
                        return true;
                    } else {
                        if (token.getNombre().equals(TipoDeDato.INICIAR.getTipo())) {
                            nT = noTerminal.S;
                            tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                            tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                                nT = noTerminal.E;
                                tokenSiguiente = "";
                                tokensSiguientes = "Iniciar";
                            }
                        }

                    }*/
                } else {
                    if (nT == noTerminal.X) {
                        return Expresion(token);
                    }
                }

            }

        }
        return false;
    }
    
    public  boolean Escribir(Token token){
        if (token.getNombre().equals(TipoDeDato.LITERAL.getTipo()) || token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                    nT = noTerminal.S;
                    tokenSiguiente = TipoDeDato.FIN.getTipo();
                    tokensSiguientes = TipoDeDato.FIN.getTipo();
                    return true;
                } else {
                    if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                        if (nTAnterior == noTerminal.P) {
                            nT = nTAnterior;
                        } else {
                            nT = noTerminal.E;
                            tokenSiguiente = "";
                            tokensSiguientes = "";
                            System.out.println("-------------------------------------");
                            

                        }
                        return true;
                    }
                    if (token.getNombre().equals(TipoDeDato.ESCRIBIR.getTipo())) {
                                    nT = noTerminal.S;
                                    nT = noTerminal.S;
                                    tokenSiguiente = TipoDeDato.LITERAL.getTipo();
                                    return true;
                                    
                                }
                }
        return false;
    }
    
    public boolean Repetir(Token token){
        if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo()) || token.getNombre().equals(TipoDeDato.ENTERO.getTipo())) {
                        nT = noTerminal.P;
                        tokenSiguiente = TipoDeDato.INICIAR.getTipo();
                        tokensSiguientes = TipoDeDato.INICIAR.getTipo();
                        return true;
                    } else {
                        if (token.getNombre().equals(TipoDeDato.INICIAR.getTipo())) {
                            nTAnterior=noTerminal.P;
                            nT = noTerminal.S;
                            tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                            tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                                nT = noTerminal.E;
                                nTAnterior = noTerminal.E;
                                tokenSiguiente = "";
                                tokensSiguientes = "Iniciar";
                                //sintaxis+="\n";
                                return true;
                            }
                        }

                    }
        return false;
    }
    
    public boolean Condicion (Token token){
        
        if (token.getNombre().equals(TipoDeDato.VERDADERO.getTipo()) || token.getNombre().equals(TipoDeDato.FALSO.getTipo())) {
                            nT = noTerminal.P;
                            tokenSiguiente = TipoDeDato.ENTONCES.getTipo();
                            tokensSiguientes = TipoDeDato.ENTONCES.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.ENTONCES.getTipo())) {
                                nTAnterior=noTerminal.P;
                                nT = noTerminal.S;
                                
                                tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                                tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                                return true;
                            } else {
                                if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                                    nT = noTerminal.E;
                                    tokenSiguiente = "";
                                    tokensSiguientes = "Iniciar";
                                    
                                    return true;
                                    
                                }
                            }

                        }
        
        return false;
    }
    
    public boolean Asignacion (Token token){
        
        if (token.getNombre().equals(TipoDeDato.IGUAL.getTipo())) {
                            nT = noTerminal.X;
                            tokenSiguiente = TipoDeDato.ENTONCES.getTipo();
                            tokensSiguientes = TipoDeDato.ENTONCES.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.ENTONCES.getTipo())) {
                                nT = noTerminal.S;
                                tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                                tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                                return true;
                            } else {
                                if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                                    nT = noTerminal.E;
                                    tokenSiguiente = "";
                                    tokensSiguientes = "Iniciar";
                                    return true;
                                    
                                }
                                
                            }

                        }
        
        return false;
    }
    
    public boolean Expresion (Token token){
        
        if (token.getNombre().equals(TipoDeDato.SIGNO_MAS.getTipo()) || token.getNombre().equals(TipoDeDato.SIGNO_POR.getTipo()) || token.getNombre().equals(TipoDeDato.OPERADOR.getTipo())) {
                            nT = noTerminal.X;
                            tokenSiguiente = TipoDeDato.ENTERO.getTipo();
                            tokensSiguientes = TipoDeDato.ENTERO.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.ENTERO.getTipo()) || token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                                nT = noTerminal.X;
                                tokenSiguiente = TipoDeDato.SIGNO_MAS.getTipo();
                                tokensSiguientes = TipoDeDato.SIGNO_POR.getTipo();
                                return true;
                            } else {
                                if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                                    nT = noTerminal.E;
                                    tokenSiguiente = "";
                                    tokensSiguientes = "Iniciar";
                                    return true;
                                    
                                }
                                
                            }

                        }
        
        return false;
    }

}
