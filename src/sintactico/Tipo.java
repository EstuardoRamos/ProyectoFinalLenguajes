/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sintactico;

import Identificador2.TipoDeDato;

/**
 * 
 * @author Estuardo Ramos
 */
public class Tipo {
    
    public static String tipoEstructura(noTerminal anterior){
        String tipo = "";
        if (anterior == noTerminal.P) {
                    tipo=TipoDeDato.REPETIR.getTipo();
                } else {
                    if (anterior == noTerminal.S) {
                        tipo=TipoDeDato.ESCRIBIR.getTipo();
                    } else {
                        if (anterior == noTerminal.EP) {
                            tipo="CONDICION";
                        }else{
                            if (anterior == noTerminal.X) {
                            tipo="ASIGNACION";
                        }else{
                                tipo=TipoDeDato.ESCRIBIR.getTipo();;
                            }
                            
                        }
                    }
                }
        
        return tipo;
    }

}
