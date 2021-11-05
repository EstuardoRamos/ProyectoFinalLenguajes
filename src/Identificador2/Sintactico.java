/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Identificador2;

import java.util.ArrayList;


public class Sintactico {
    int hola;
    int matrizEst[][]= new int[4][4];
    
    {
        matrizEst[0][0]=1;
        matrizEst[0][1]=-1;
        matrizEst[0][2]=-1;
        matrizEst[0][3]=-1;
        
        matrizEst[1][0]=-1;
        matrizEst[1][1]=2;
        matrizEst[1][2]=-1;
        matrizEst[1][3]=-1;
        
        matrizEst[2][0]=-1;
        matrizEst[2][1]=-1;
        matrizEst[2][2]=3;
        matrizEst[2][3]=-1;
        
        
    }
    
    
    public  void getSintactico(ArrayList<Token> token){
        int estadoActual;
            int estadoAnterior = 0;
        for (Token token1 : token) {
            estadoActual= matrizEst[getEstado(token1)][estadoAnterior];
            System.out.println("token "+token1.getNombre()+"me lleva de "+estadoAnterior+" a "+estadoActual);
            if (estadoActual==3) {
                System.out.println("sintaxis aceptada");
                estadoActual=0;
            }else{
                if (estadoActual==-1) {
                    System.out.println("sintaxis no aceptada");
                }
            }
            estadoAnterior=estadoActual;
            
            
        }
    }
    
    public int getEstado(Token token1){
        int estado=-1;
            switch(token1.getNombre()){
                case "ESCRIBIR":
                    estado=0;
                    break;
                case "LITERAL":
                    estado=1;
                    break;
                case "FIN":
                    estado=2;
                    break;
                
            }
        return estado;
    }
    
    public static void Escribir(Token token){
        String txt = null;
        while (token.getNombre()!="FIN") {            
            // RECIBIMOS TOKEN QUE SEA INDE O LITERAL
            // SI ES LITERAL A MANDAMOS  AUN METODOQ QUE QUITE LA S COMILLAS Y AGREGAMOS LO DEMAS 
            // SI ES IDENTIFICADOR AGRAGAMOS EL LEXEMA AL TXT
            
            if (token.getNombre()=="LIERAL") {
                
            }else{
                if (token.getNombre()=="IDENTIFICADOR") {
                    txt+=token.getLexema();
                    
                }
            }
        }
        
    }
    public void repetir(int cantRep){
        for (int i = 0; i < cantRep; i++) {
            //Escribir();
        }
    }
    
    
    public static void recibirTokens(ArrayList<Token> token){
        System.out.println("entrando a metodo");
        
        String texto = "";
        
        for (Token token1 : token) {
            String var = "";
            System.out.println("tok "+token1.getLexema());
            if (token1.getLexema().equals("ESCRIBIR")) {
                Escribir(token1);
                var="";
            }
            if (token1.getNombre().equals("IDENTICADOR")) {
                var= token1.getLexema()+" ";
            }
            if (token1.getNombre().equals("FIN")) {
                var = "";
            }
            texto+=var;
        }
        System.out.println(texto);
        
    }

}
