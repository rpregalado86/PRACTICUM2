/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;



/**
 *
 * @author Walter Mora
 */
public class ManejoArchivoRuta extends ManejoArchivo {
    public int registrarRuta(int codigo, 
                            String nombre, 
                            String origen, 
                            String destino, 
                            double costo) throws Exception {
        //Si el codigo enviado es cero es un ingreso de datos caso contrario actualizacion
        if (codigo == 0) {
            codigo = ingresarRuta(codigo, nombre, origen, destino, costo);
        } 
        return codigo;
    }

    private int ingresarRuta(int codigo, 
                            String nombre, 
                            String origen, 
                            String destino, 
                            double costo) throws Exception {
        
        File archivo = obtieneArchivo(NOMBRE_ARC_RUTAS);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))){

        codigo = obtenerNumeroLineasArchivo(NOMBRE_ARC_RUTAS);
        codigo = codigo + 1;
        
         bw.write(codigo + DELIMITADOR_CAMPOS
                + nombre + DELIMITADOR_CAMPOS
                + origen + DELIMITADOR_CAMPOS
                + destino + DELIMITADOR_CAMPOS
                + costo);
         bw.newLine();
        }

        return codigo;
    }

    public int actualizarRuta(int codigo, 
                            String nombre, 
                            String origen, 
                            String destino, 
                            double costo) throws Exception {
        String linea = "";
        String lineaNueva = "";
        String[] registro = null;

        File archivo = obtieneArchivo(NOMBRE_ARC_RUTAS);

        //Se crea un archivo adicional que nos servira para pasar los datos
        //del archivo original con el cambio realizado
        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_RUTAS);

        //Se arma la linea nueva con la sumatoria de horas
        lineaNueva = codigo + DELIMITADOR_CAMPOS
                + nombre + DELIMITADOR_CAMPOS
                + origen + DELIMITADOR_CAMPOS
                + destino + DELIMITADOR_CAMPOS
                + costo;

       
        try (BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter  bw = new BufferedWriter(new FileWriter(tmpArchivo))){
   
        while ((linea = br.readLine()) != null) {
            if (!linea.equals(CABECERAARCRUTAS)) {
                registro = linea.split("\\" + DELIMITADOR_CAMPOS);
                if (Integer.parseInt(registro[0]) == codigo) {
                    linea = lineaNueva;
                }
            }
            bw.write(linea);
            bw.newLine();
        }
        }  

        return codigo;
    }

    public void eliminarRuta(int codigo) throws Exception {
        String linea = "";
        String[] registro = null;
        boolean escribirLinea;
        
	        File archivo = obtieneArchivo(NOMBRE_ARC_RUTAS);
	
	        //Se crea un archivo adicional que nos servira para pasar los datos
	        //del archivo original con el cambio realizado
	        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_RUTAS);

	        try (BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter bw = new BufferedWriter(new FileWriter(tmpArchivo))){
	        
	
	        //Se recorre el archivo hasta encontrar el codigo
	        while ((linea = br.readLine()) != null) {
	            escribirLinea = true;
	            if (!linea.equals(CABECERAARCRUTAS)) {
	                registro = linea.split("\\" + DELIMITADOR_CAMPOS);
	                //Se escriben las lineas que son diferentes al codigo enviado
	                if (Integer.parseInt(registro[0]) == codigo) {
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

    public Ruta buscarRuta(int codigo) throws Exception {
        String[] registro;
        Ruta ruta = null;

        registro = obtieneRegistroArchivo(NOMBRE_ARC_RUTAS, Integer.toString(codigo), 0);
        if (registro != null) {
            ruta = new Ruta();
            ruta.setIntIdRuta(Integer.parseInt(registro[0]));
            ruta.setStrNombre(registro[1]);
            ruta.setStrOrigen(registro[2]);
            ruta.setStrDestino(registro[3]);
            ruta.setDouCosto(Double.parseDouble(registro[4]));
        }
        return ruta;
    }

    public Object[][] obtieneRutas() throws Exception {
        Object[][] rutas = null;
        String linea;
        String[] registro = null;
        int numFilas = 0;
        int i = 0;

        numFilas = obtenerNumeroLineasArchivo(NOMBRE_ARC_RUTAS);
        if (numFilas > 0) {
            File archivo = obtieneArchivo(NOMBRE_ARC_RUTAS);
            if (archivo != null) {
                try (BufferedReader bf = new BufferedReader(new FileReader(archivo))){

                //Creamos un arreglo con la cantidad de lineas que se obtienen del arreglo
                rutas = new Object[numFilas][5];
                //Se recorre el archivo hasta encontrar el codigo
                while ((linea = bf.readLine()) != null) {
                    if (!linea.equals(CABECERAARCRUTAS)) {
                        registro = linea.split("\\" + DELIMITADOR_CAMPOS);

                        rutas[i][0] = registro[0];
                        rutas[i][1] = registro[1];
                        rutas[i][2] = registro[2];
                        rutas[i][3] = registro[3];
                        rutas[i][4] = Double.parseDouble(registro[4]);
                        i++;
                    }
                }
                }
            }
        }
        return rutas;
    }
}
