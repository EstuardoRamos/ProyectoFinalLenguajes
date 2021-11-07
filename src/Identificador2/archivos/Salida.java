
package Identificador2.archivos;

import Identificador2.*;
import java.util.ArrayList;
import sintactico.Estructura;

/**
 * 
 * @author Estuardo Ramos
 */
public class Salida {
    
    
    String txt;
    
    public void Repetir(int cant, String literal){
        for (int i = 0; i < cant; i++) {
            //EscribirTexto(literal);
        }
    }
    
    public void EscribirTexto(ArrayList<Token>  token ){
        for (Token token1 : token) {
            if (token1.getNombre().equals(TipoDeDato.IDENTIFICADOR.getTipo())) {
            txt+=token1.getLexema()+"\n";
        }else{
            if (token1.getNombre().equals(TipoDeDato.LITERAL.getTipo())) {
                int end = token1.getLexema().length()-1;
                String txt1 = token1.getLexema().substring(1,end);
                txt+=txt1+"\n";
            }else{
                System.out.println("ignoramos");
            }
        }
            
        }
        
        System.out.println(txt);
        
    }
    
    public void revisarEstructura(ArrayList<Estructura> es){
        for (Estructura e : es) {
            EscribirTexto(e.getTokens());
        }
        ManejadorArchivos man = new ManejadorArchivos();
        man.seleccionarUbicacionArchivo();
        man.escribirNuevo(txt);
    }

}
