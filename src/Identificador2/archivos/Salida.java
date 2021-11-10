package Identificador2.archivos;

import Identificador2.*;
import java.util.ArrayList;
import sintactico.Estructura;

/**
 *
 * @author Estuardo Ramos
 */
public class Salida {

    public static void main(String[] args) {
        String opString = "5";
        int opNum = Integer.valueOf(opString);
        System.out.println(opNum);
    }

    String txt;

    public void Repetir(ArrayList<Token> token) {
        int repeticiones = 0;
        for (Token token1 : token) {
            if (token1.getNombre().equals(TipoDeDato.ENTERO.getTipo())) {
                repeticiones = Integer.valueOf(token1.getLexema());
            }
        }
        escribirRepetir(token, repeticiones);
    }

    public void escribirRepetir(ArrayList<Token> token, int cant) {
        for (int i = 0; i < cant; i++) {
            EscribirTexto(token);
        }
    }

    public void EscribirTexto(ArrayList<Token> token) {

        for (Token token1 : token) {
            if (token1.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
                txt += token1.getLexema() + "\n";
            } else {
                if (token1.getNombre().equals(TipoDeDato.LITERAL.getTipo())) {
                    int end = token1.getLexema().length() - 1;
                    String txt1 = token1.getLexema().substring(1, end);
                    txt += txt1 + "\n";
                } else {

                }
            }

        }

        //System.out.println(txt);
    }

    public void revisarEstructura(ArrayList<Estructura> es) {
        for (Estructura e : es) {
            if (e.getTipo().equals(TipoDeDato.ESCRIBIR.getTipo())) {
                EscribirTexto(e.getTokens());
            } else {
                if (e.getTipo().equals(TipoDeDato.REPETIR.getTipo())) {
                    Repetir(e.getTokens());
                } else {
                    if (e.getTipo().equals("CONDICION")) {
                        escribirONo(e.getTokens());
                    }
                    System.out.println("nada");
                }
            }
        }
        ManejadorArchivos man = new ManejadorArchivos();
        //man.seleccionarUbicacionArchivo();
        man.escribirNuevo(txt);
    }

    public void escribirONo(ArrayList<Token> token) {
        for (Token token1 : token) {
            if (token1.getNombre().equals(TipoDeDato.VERDADERO.getTipo())) {
                EscribirTexto(token);
                System.out.println("se escribe");
            } else {
                System.out.println("no se escribe");
            }
        }
    }

}
