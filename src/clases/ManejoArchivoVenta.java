/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import static clases.ManejoArchivo.DELIMITADOR_CAMPOS;
import static clases.ManejoArchivo.NOMBRE_ARC_BOLETOS;
import static clases.ManejoArchivo.PREFIJO_TEMPORAL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import validaciones.Fecha;

/**
 *
 * @author Walter Mora
 */
public class ManejoArchivoVenta extends ManejoArchivo {

    public int registrarVenta(int codigo,
            String identificacionCliente,
            String nombresCliente,
            String apellidosCliente,
            String fechaNacimiento,
            String tipoCliente,
            int codigoRuta,
            String fecha,
            String hora,
            double total,
            String username) throws Exception {
        //Si el codigo enviado es cero es un ingreso de datos caso contrario actualizacion
        if (codigo == 0) {
            codigo = ingresarVenta(codigo,
                    identificacionCliente,
                    nombresCliente,
                    apellidosCliente,
                    fechaNacimiento,
                    tipoCliente,
                    codigoRuta,
                    fecha,
                    hora,
                    total,
                    username);
        
        }
        return codigo;
    }

    private int ingresarVenta(int codigo,
            String identificacionCliente,
            String nombresCliente,
            String apellidosCliente,
            String fechaNacimiento,
            String tipoCliente,
            int codigoRuta,
            String fecha,
            String hora,
            double total,
            String username) throws Exception {

        
      
         
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(obtieneArchivo(NOMBRE_ARC_BOLETOS)))) {

        codigo = obtenerNumeroLineasArchivo(NOMBRE_ARC_BOLETOS);
        codigo = codigo + 1;

        bw.write(codigo + DELIMITADOR_CAMPOS
                + identificacionCliente + DELIMITADOR_CAMPOS
                + nombresCliente + DELIMITADOR_CAMPOS
                + apellidosCliente + DELIMITADOR_CAMPOS
                + fechaNacimiento + DELIMITADOR_CAMPOS
                + tipoCliente + DELIMITADOR_CAMPOS
                + codigoRuta + DELIMITADOR_CAMPOS
                + fecha + DELIMITADOR_CAMPOS
                + hora + DELIMITADOR_CAMPOS
                + total + DELIMITADOR_CAMPOS
                + username);
        bw.newLine();
        }

        return codigo;
    }

    public int actualizarVenta(int codigo,
            String identificacionCliente,
            String nombresCliente,
            String apellidosCliente,
            String fechaNacimiento,
            String tipoCliente,
            int codigoRuta,
            String fecha,
            String hora,
            double total,
            String username) throws Exception {
        String linea;
        String lineaNueva;
        String[] registro;

        File archivo = obtieneArchivo(NOMBRE_ARC_BOLETOS);

        //Se crea un archivo adicional que nos servira para pasar los datos
        //del archivo original con el cambio realizado
        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_BOLETOS);

        //Se arma la linea nueva con la sumatoria de horas
        lineaNueva = codigo + DELIMITADOR_CAMPOS
                + identificacionCliente + DELIMITADOR_CAMPOS
                + nombresCliente + DELIMITADOR_CAMPOS
                + apellidosCliente + DELIMITADOR_CAMPOS
                + fechaNacimiento + DELIMITADOR_CAMPOS
                + tipoCliente + DELIMITADOR_CAMPOS
                + codigoRuta + DELIMITADOR_CAMPOS
                + fecha + DELIMITADOR_CAMPOS
                + hora + DELIMITADOR_CAMPOS
                + total + DELIMITADOR_CAMPOS
                + username;
        
       

        try(BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter bw = new BufferedWriter(new FileWriter(tmpArchivo))){

        while ((linea = br.readLine()) != null) {
            if (!linea.equals(CABECERAARCBOLETOS)) {
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

    public void eliminarVenta(int codigo) throws Exception {
        String linea;
        String[] registro;
        boolean escribirLinea;

        File archivo = obtieneArchivo(NOMBRE_ARC_BOLETOS);

        //Se crea un archivo adicional que nos servira para pasar los datos
        //del archivo original con el cambio realizado
        File tmpArchivo = obtieneArchivo(PREFIJO_TEMPORAL + NOMBRE_ARC_BOLETOS);

       
        try (BufferedReader br = new BufferedReader(new FileReader(archivo));BufferedWriter bw = new BufferedWriter(new FileWriter(tmpArchivo))){
        

        //Se recorre el archivo hasta encontrar el codigo
        while ((linea = br.readLine()) != null) {
            escribirLinea = true;
            if (!linea.equals(CABECERAARCBOLETOS)) {
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

    public Venta buscarVenta(int codigo) throws Exception {
        String[] registro;
        Venta venta = null;

        registro = obtieneRegistroArchivo(NOMBRE_ARC_BOLETOS, Integer.toString(codigo), 0);
        if (registro != null) {
            venta = new Venta();
            venta.setIntIdVenta(Integer.parseInt(registro[0]));

            Cliente cliente = new Cliente();
            cliente.setStrIdentificacion(registro[1]);
            cliente.setStrNombres(registro[2]);
            cliente.setStrApellidos(registro[3]);
            final String formato = "dd/MM/yyyy";
			cliente.setDatFechaNacimiento(Fecha.getFecha(formato, registro[4]));
            cliente.setStrTipoCliente(registro[5]);
            venta.setCliente(cliente);

            ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
            Ruta ruta = manejoArchivoRuta.buscarRuta(Integer.parseInt(registro[6]));
            venta.setRuta(ruta);

            venta.setDatFecha(Fecha.getFecha(formato, registro[7]));
            venta.setStrHora(registro[8]);
            venta.setDobCosto(Double.parseDouble(registro[9]));

            ManejoArchivoUsuario manejoArchivoUsuario = new ManejoArchivoUsuario();
            Usuario usuario = manejoArchivoUsuario.buscarUsuario(registro[10]);
            venta.setUsuario(usuario);
        }
        return venta;
    }

    public Object[][] obtieneVentas() throws Exception {
        Object[][] ventas = null;
        String linea;
        String[] registro;
        int numFilas;
        int i = 0;

        numFilas = obtenerNumeroLineasArchivo(NOMBRE_ARC_BOLETOS);
        if (numFilas > 0) {
            File archivo = obtieneArchivo(NOMBRE_ARC_BOLETOS);
            if (archivo != null) {
                try (BufferedReader bf = new BufferedReader(new FileReader(archivo))){

                //Creamos un arreglo con la cantidad de lineas que se obtienen del arreglo
                ventas = new Object[numFilas][11];
                //Se recorre el archivo hasta encontrar el codigo
                while ((linea = bf.readLine()) != null) {
                    if (!linea.equals(CABECERAARCBOLETOS)) {
                        registro = linea.split("\\" + DELIMITADOR_CAMPOS);

                        ventas[i][0] = registro[0];
                        ventas[i][1] = registro[1];
                        ventas[i][2] = registro[2];
                        ventas[i][3] = registro[3];
                        ventas[i][4] = registro[4];
                        ventas[i][5] = registro[5];
                        ventas[i][6] = registro[6];
                        ventas[i][7] = registro[7];
                        ventas[i][8] = registro[8];
                        ventas[i][9] = registro[9];
                        ventas[i][10] = registro[10];

                        i++;
                    }
                }
                }
            }
        }
        return ventas;
    }

    public Object[][] obtieneVentasCriterios(String identificacion,
            int codigoRuta,
            String fecha,
            String tipoPasajero) throws Exception {
        
        Object[][] ventasAux = null;
        Object[][] ventas = null;
        String linea;
        String[] registro;
        int numFilas;
        int numFilasValidas = 0;
        int i = 0;
        boolean considerarLinea;
        String etiquetaTipoPasajero = "";

        numFilas = obtenerNumeroLineasArchivo(NOMBRE_ARC_BOLETOS);
        if (numFilas > 0) {
            File archivo = obtieneArchivo(NOMBRE_ARC_BOLETOS);
            if (archivo != null) {
                try (BufferedReader bf = new BufferedReader(new FileReader(archivo))){

                //Creamos un arreglo con la cantidad de lineas que se obtienen del arreglo
                ventasAux = new Object[numFilas][12];
                //Se recorre el archivo hasta encontrar el codigo
                while ((linea = bf.readLine()) != null) {
                    if (!linea.equals(CABECERAARCBOLETOS)) {
                        registro = linea.split("\\" + DELIMITADOR_CAMPOS);

                        considerarLinea = true;

                        //Si coincide la identificacion
                        if (identificacion.trim().length() > 0 && !identificacion.equals(registro[1])) {
                          
                                considerarLinea = false;
                        
                        }

                        //Si coincide la ruta
                        if (codigoRuta > 0 &&  codigoRuta != Integer.parseInt(registro[6])) {
                                considerarLinea = false;
                        }

                        //Si coincide la fecha
                        if (fecha.trim().length() > 0 && !fecha.equals(registro[7])) {                   
                                considerarLinea = false;
                        }

                        //Si coincide la tipo Pasajero
                        if (!tipoPasajero.equals("0")&&!tipoPasajero.equals(registro[5])) {
                                considerarLinea = false;
                        }
                        
                        if (considerarLinea) {
                            numFilasValidas = numFilasValidas + 1;
                            ventasAux[i][0] = false;
                            ventasAux[i][1] = registro[0];
                            ventasAux[i][2] = registro[1];
                            ventasAux[i][3] = registro[2];
                            ventasAux[i][4] = registro[3];
                            ventasAux[i][5] = registro[4];
                            
                            //A traves del codigo obtenemos la etiqueta del tipo de pasajero
                            //Ya que necesitamos al combo del grid ponerle la 
                            //descripcion seleccionada
                            if (registro[5].equals("P"))
                                etiquetaTipoPasajero = "P - PERSONA";
                            if (registro[5].equals("S"))
                                etiquetaTipoPasajero = "S - SOCIO";
                            if (registro[5].equals("V"))
                                etiquetaTipoPasajero = "V - CLIENTE VIP";
                            ventasAux[i][6] = etiquetaTipoPasajero;
                            
                            //A traves del codigo obtenemos la etiqueta de la ruta
                            //Ya que necesitamos al combo del grid ponerle la 
                            //descripcion seleccionada
                            ManejoArchivoRuta manejoArchivoRuta = new ManejoArchivoRuta();
                            Ruta ruta = manejoArchivoRuta.buscarRuta(Integer.parseInt(registro[6]));
                            ventasAux[i][7] = ruta.getIntIdRuta() + " - " + ruta.getStrNombre();
                            
                            ventasAux[i][8] = registro[7];
                            ventasAux[i][9] = registro[8];
                            ventasAux[i][10] = registro[9];
                            ventasAux[i][11] = registro[10];
                            i++;
                        }
                    }
                }
                }
            }
            if (numFilasValidas > 0) {
                ventas = new Object[numFilasValidas][12];
                java.lang.System.arraycopy(ventasAux,0,ventas,0, numFilasValidas);
            }
        }
        return ventas;
    }

    public Cliente buscarCliente(String identificacion) throws Exception {
        String[] registro;
        Cliente cliente = null;

        registro = obtieneRegistroArchivo(NOMBRE_ARC_BOLETOS, identificacion, 1);
        if (registro != null) {
            cliente = new Cliente();
            cliente.setStrIdentificacion(registro[1]);
            cliente.setStrNombres(registro[2]);
            cliente.setStrApellidos(registro[3]);
            cliente.setDatFechaNacimiento(Fecha.getFecha("dd/MM/yyyy", registro[4]));
            cliente.setStrTipoCliente(registro[5]);
        }
        return cliente;
    }
}
