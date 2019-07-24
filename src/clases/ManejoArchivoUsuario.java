/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.ManejoArchivo.DELIMITADOR_CAMPOS;
import static clases.ManejoArchivo.NOMBRE_ARC_ACCESOS;
import static clases.ManejoArchivo.PREFIJO_TEMPORAL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Walter Mora
 */
public class ManejoArchivoUsuario extends ManejoArchivo {
    public void registrarUsuario(String username, String nombres, String apellidos, String password) throws Exception {
        //Si no existe se ingresa caso contrario se actualiza
        Usuario usuario = buscarUsuario(username);
        
        if (usuario == null) {
            ingresarUsuario(username, nombres, apellidos, password);
        } else {
            actualizarUsuario(username, nombres, apellidos, password);
        }
    }

    private void ingresarUsuario(String username, String nombres, String apellidos, String password) throws Exception {
        
        File archivo = obtieneArchivo(NOMBRE_ARC_ACCESOS);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))){

        bw.write(username + DELIMITADOR_CAMPOS
                + nombres + DELIMITADOR_CAMPOS
                + apellidos + DELIMITADOR_CAMPOS
                + password);
        bw.newLine();
        }
    }

    private void actualizarUsuario(String username, String nombres, String apellidos, String password) throws Exception {
        String linea = "";
        String lineaNueva = "";
        String[] registro = null;

        File archivo = obtieneArchivo(NOMBRE_ARC_ACCESOS);

        //Se crea un archivo adicional que nos servira para pasar los datos
        //del archivo original con el cambio realizado
        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_ACCESOS);

        //Se arma la linea nueva con la sumatoria de horas
        lineaNueva = username + DELIMITADOR_CAMPOS
                + nombres + DELIMITADOR_CAMPOS
                + apellidos + DELIMITADOR_CAMPOS
                + password;

       

        try (BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter bw = new BufferedWriter(new FileWriter(tmpArchivo))){
        
        
        while ((linea = br.readLine()) != null) {
            if (!linea.equals(CABECERAARCACCESOS)) {
                registro = linea.split("\\" + DELIMITADOR_CAMPOS);
                if (registro[0].equals(username)) {
                    linea = lineaNueva;
                }
            }
            bw.write(linea);
            bw.newLine();
        }
        }
         
    }

    public void eliminarUsuario(String username) throws Exception {
        String linea = "";
        String[] registro = null;
        boolean escribirLinea;

        File archivo = obtieneArchivo(NOMBRE_ARC_ACCESOS);

        //Se crea un archivo adicional que nos servira para pasar los datos
        //del archivo original con el cambio realizado
        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_ACCESOS);

      

        try (BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter bw = new BufferedWriter(new FileWriter(tmpArchivo))){
        

        //Se recorre el archivo hasta encontrar el codigo
        while ((linea = br.readLine()) != null) {
            escribirLinea = true;
            if (!linea.equals(CABECERAARCACCESOS)) {
                registro = linea.split("\\" + DELIMITADOR_CAMPOS);
                //Se escriben las lineas que son diferentes al codigo enviado
                if (registro[0].equals(username)) {
                    escribirLinea = false;
                }
            }
            if (escribirLinea) {
                bw.write(linea);
                bw.newLine();
            }
        }
        }
        
    }

    public Usuario buscarUsuario(String username) throws Exception {
        String[] registro;
        Usuario usuario = null;

        registro = obtieneRegistroArchivo(NOMBRE_ARC_ACCESOS, username, 0);
        if (registro != null) {
            usuario = new Usuario();
            usuario.setStrUserName(registro[0]);
            usuario.setStrNombres(registro[1]);
            usuario.setStrApellidos(registro[2]);
            usuario.setStrPassword(registro[3]);
        }
        return usuario;
    }

    public Object[][] obtieneUsuarios() throws Exception {
        Object[][] rutas = null;
        String linea;
        String[] registro = null;
        int numFilas = 0;
        int i = 0;

        numFilas = obtenerNumeroLineasArchivo(NOMBRE_ARC_ACCESOS);
        if (numFilas > 0) {
            File archivo = obtieneArchivo(NOMBRE_ARC_ACCESOS);
            if (archivo != null) {
                
            	try (BufferedReader bf = new BufferedReader(new FileReader(archivo))){

                //Creamos un arreglo con la cantidad de lineas que se obtienen del arreglo
                rutas = new Object[numFilas][4];
                //Se recorre el archivo hasta encontrar el codigo
                while ((linea = bf.readLine()) != null) {
                    if (!linea.equals(CABECERAARCACCESOS)) {
                        registro = linea.split("\\" + DELIMITADOR_CAMPOS);

                        rutas[i][0] = registro[0];
                        rutas[i][1] = registro[1];
                        rutas[i][2] = registro[2];
                        rutas[i][3] = registro[3];
                        i++;
                    }
                }
            	}
            }
        }
        return rutas;
    }
}