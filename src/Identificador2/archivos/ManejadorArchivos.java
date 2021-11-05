/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Identificador2.archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Estuardo Ramos
 */
public class ManejadorArchivos {
    private String contenido;
    private String direccion;
    File archivo;

    public ManejadorArchivos() {
    }
    
    
    
     //Lee un archivo de entrada y lo carga al text area
    public void cargarArchivos(JTextArea cadenaTxt, JButton guardar){
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        archivo = fc.getSelectedFile();
        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br= new BufferedReader(fr);
            String texto="";
            String linea="";
            while((linea=br.readLine()) != null) {
                texto+=linea+"\n";
            }
            cadenaTxt.setText(texto);
            guardar.setEnabled(true);
            JOptionPane.showMessageDialog(null, "Archivo cargado puede analizarlo");
            contenido = texto;
        } catch (Exception e) {
        }
    }
    
    
    //guarda los cambios si se carga un archivo de entrada
    public void guardarCambios( String modificado){
        PrintWriter printWriter = null;
        String textToBeWritten = modificado;
        {
            try {
                printWriter = new PrintWriter(archivo);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error archivo no puede guasrdarse");
            }
            Objects.requireNonNull(printWriter).println(textToBeWritten);
            printWriter.close();
        }
    
}
    
    

}
