/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Identificador2;

/**
 * 
 * @author Estuardo Ramos
 */
public  enum TipoDeDato {
     IDENTIFICADOR("IDENTIFICADOR"),
        ENTERO("NUMERO ENTERO"),
        DECIMAL("NUMERO DECIMAL"),
        AGRUPACION("SIGNO DE AGRUPACION"),
        PUNTUACION("SIGNOS DE PUNTUACION"),
        OPERADOR("OPERADOR"),
        ERROR,
        FIN("FIN"),
        ESCRIBIR("ESCRIBIR"),
        LITERAL("LITERAL"),
        SI("SI"),
        ENTONCES("ENTONCES"),
        SIGNO_MAS("SIGNO_MAS"),
        SIGNO_POR("SGNO_POR"),
        REPETIR("REPETIR"),
        INICIAR("INICIAR"),
        VERDADERO("VERDADERO"),
        IGUAL("IGUAL"),
        FALSO("FALSO"),
        PARENTESIS_CIERRA("PARENTESIS_CIERRA"),
        PARENTESIS_ABRE("PARENTESIS_ABRE");
    
    private String tipo;

    private TipoDeDato(String tipo) {
        this.tipo = tipo;
    }

    private TipoDeDato() {
    }

    public static TipoDeDato getIDENTIFICADOR() {
        return IDENTIFICADOR;
    }

    public static TipoDeDato getENTERO() {
        return ENTERO;
    }

    public static TipoDeDato getDECIMAL() {
        return DECIMAL;
    }

    public static TipoDeDato getAGRUPACION() {
        return AGRUPACION;
    }

    public static TipoDeDato getPUNTUACION() {
        return PUNTUACION;
    }

    public static TipoDeDato getOPERADOR() {
        return OPERADOR;
    }

    public static TipoDeDato getERROR() {
        return ERROR;
    }

    public String getTipo() {
        return tipo;
    }
    
    

}
