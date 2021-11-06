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

    ArrayList<Token> tokensErrores = new ArrayList<>();

    String sintaxis;

    public void receibeToken(ArrayList<Token> token) {
        String next;
        nT = noTerminal.E;

        for (Token token1 : token) {

            System.out.println(token1.getNombre());
            System.out.println("estamos en el no terminal --> "+nT);
            System.out.println("  el no terminal anterior es --> "+nTAnterior);
            if (nT == noTerminal.E) {
                sintaxis += " \n";
            }

            if (esSiguiente(token1) ) {
                // pasmos al siguiente
                System.out.println(token1.getNombre() + " token aceptado");
                sintaxis += token1.getLexema() + " ";

                // este debe de ser el token siguiente
                System.out.println("El siguiente token debe de ser un " + tokensSiguientes+ "\n");
            } else {
                 

                    String msjError = "se esperaba un " + tokensSiguientes;
                    Token tokCorrec = new Token(tokenSiguiente, "");
                    token1.setMsj(msjError);
                    tokensErrores.add(token1);
                    esSiguiente(tokCorrec);
                    //movimientos(tokCorrec);
                    System.out.println(msjError);
                    sintaxis += token1.getLexema() + " <-error ";
                
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
                if (token.getNombre().equals(TipoDeDato.REPETIR.getTipo())) {
                    nT = noTerminal.P;
                    tokenSiguiente = TipoDeDato.ENTERO.getTipo();
                    return true;
                } else {
                    if (token.getNombre().equals(TipoDeDato.SI.getTipo())) {
                        nT = noTerminal.EP;
                        tokenSiguiente = TipoDeDato.ENTERO.getTipo();
                        tokensSiguientes = TipoDeDato.VERDADERO.getTipo() + " 0 " + TipoDeDato.FALSO.getTipo();
                        return true;
                    } else {
                        if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                            nT = noTerminal.A;
                            tokenSiguiente = TipoDeDato.IGUAL.getTipo();
                            tokensSiguientes = TipoDeDato.IGUAL.getTipo();
                            return true;
                        } else {
                            if (token.getNombre().equals(TipoDeDato.ENTERO.getTipo())) {
                                nT = noTerminal.X;
                                tokenSiguiente = TipoDeDato.OPERADOR.getTipo();
                                tokensSiguientes = TipoDeDato.OPERADOR.getTipo();
                                return true;
                            }else{
                                System.out.print(" -->No es inicial");
                                return false;
                            }
                        }
                    }
                }

            }
        } else {

            // entramos a mno terminal S
            if (nT == noTerminal.S) {
                System.out.println("----estasmos en S");
                return Escribir(token);

            } else {
                // entramos al no terminal P Repetir o condicion
                if (nT == noTerminal.P) {
                    return Repetir(token);
                } else {
                    if (nT == noTerminal.X) {
                        return Expresion(token);
                    } else {
                        if (nT == noTerminal.A) {
                            return A(token);
                        }else{
                            if (nT == noTerminal.EP) {
                            return Condicion(token);
                        }
                        }
                    }
                }

            }

        }
        return false;
    }

    public boolean Escribir(Token token) {
        if (token.getNombre().equals(TipoDeDato.LITERAL.getTipo()) || token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
            nT = noTerminal.S;
            tokenSiguiente = TipoDeDato.FIN.getTipo();
            tokensSiguientes = TipoDeDato.FIN.getTipo();
            return true;
        } else {
            if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
                System.out.println("el no terminal anterior es "+nTAnterior);
                if (nTAnterior == noTerminal.P || nTAnterior == noTerminal.EP) {
                    nT = nTAnterior;
                    nTAnterior = noTerminal.S;
                } else {
                    nT = noTerminal.E;
                    tokenSiguiente = "";
                    tokensSiguientes = "";
                    System.out.println("-------------------------------------");

                }
                System.out.println("el no terminal actual es "+nT);
                return true;
            }
            if (token.getNombre().equals(TipoDeDato.ESCRIBIR.getTipo())) {
                nT = noTerminal.S;
                tokenSiguiente = TipoDeDato.LITERAL.getTipo();
                return true;

            }
        }
        return false;
    }

    public boolean Repetir(Token token) {
        if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo()) || token.getNombre().equals(TipoDeDato.ENTERO.getTipo())) {
            nT = noTerminal.P;
            tokenSiguiente = TipoDeDato.INICIAR.getTipo();
            tokensSiguientes = TipoDeDato.INICIAR.getTipo();
            return true;
        } else {
            if (token.getNombre().equals(TipoDeDato.INICIAR.getTipo())) {
                nTAnterior = noTerminal.P;
                nT = noTerminal.S;
                tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                return true;
            } else {
                if (token.getNombre().equals(TipoDeDato.FIN.getTipo()) && nTAnterior==noTerminal.S) {
                    nT = noTerminal.E;
                    nTAnterior = noTerminal.E;
                    tokenSiguiente = "";
                    tokensSiguientes = "Iniciar";
                    System.out.println("Fin de repetir");
                    //sintaxis+="\n";
                    return true;
                }else{
                    if (token.getNombre().equals(TipoDeDato.ESCRIBIR.getTipo()) && nTAnterior==noTerminal.S) {
                    nT = noTerminal.S;
                    nTAnterior=noTerminal.P;
                    //nTAnterior = noTerminal.E;
                    //tokenSiguiente = "";
                    //tokensSiguientes = "Iniciar";
                    //sintaxis+="\n";
                    return true;
                }else{
                        return false;
                    }
                }
            }

        }
        
    }

    public boolean Condicion(Token token) {

        if (token.getNombre().equals(TipoDeDato.VERDADERO.getTipo()) || token.getNombre().equals(TipoDeDato.FALSO.getTipo())) {
            nT = noTerminal.EP;
            tokenSiguiente = TipoDeDato.ENTONCES.getTipo();
            tokensSiguientes = TipoDeDato.ENTONCES.getTipo();
            return true;
        } else {
            if (token.getNombre().equals(TipoDeDato.ENTONCES.getTipo())) {
                nTAnterior = noTerminal.EP;
                nT = noTerminal.S;

                tokenSiguiente = TipoDeDato.ESCRIBIR.getTipo();
                tokensSiguientes = TipoDeDato.ESCRIBIR.getTipo();
                return true;
            } else {
                if (token.getNombre().equals(TipoDeDato.FIN.getTipo()) && nTAnterior==noTerminal.S) {
                    nT = noTerminal.E;
                    tokenSiguiente = "";
                    tokensSiguientes = "Iniciar";

                    return true;

                }else{
                    return false;
                }
            }

        }

        
    }

    public boolean Asignacion(Token token) {

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

    public boolean Expresion(Token token) {

        if (token.getNombre().equals(TipoDeDato.SIGNO_MAS.getTipo()) || token.getNombre().equals(TipoDeDato.SIGNO_POR.getTipo()) || token.getNombre().equals(TipoDeDato.OPERADOR.getTipo())) {
            nT = noTerminal.X;
            tokenSiguiente = TipoDeDato.ENTERO.getTipo();
            tokensSiguientes = TipoDeDato.ENTERO.getTipo();
            return true;
        } else {
            if (token.getNombre().equals(TipoDeDato.ENTERO.getTipo()) || token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                nT = noTerminal.X;
                tokenSiguiente = TipoDeDato.SIGNO_MAS.getTipo() + " o " + TipoDeDato.SIGNO_POR.getTipo() + " o " + TipoDeDato.FIN.getTipo();
                tokensSiguientes = TipoDeDato.SIGNO_MAS.getTipo() + " o " + TipoDeDato.SIGNO_POR.getTipo() + " o " + TipoDeDato.FIN.getTipo();
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

    public boolean Ep(Token token) {
        boolean term = false;
        if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
            nT = noTerminal.A;
            term = true;
        }
        return term;
    }

    public boolean A(Token token) {
        if (token.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
            nT = noTerminal.A;
            return true;
        }
        if (token.getNombre().equals(TipoDeDato.IGUAL.getTipo())) {
            nT = noTerminal.X;
            tokenSiguiente = TipoDeDato.ENTERO.getTipo();
            tokensSiguientes = TipoDeDato.ENTERO.getTipo();
            return true;
        }
        /*if (token.getNombre().equals(TipoDeDato.FIN.getTipo())) {
            nT = noTerminal.E;
            return true;
        }*/
        return false;
    }

    public ArrayList<Token> getTokensErrores() {
        return tokensErrores;
    }

}
