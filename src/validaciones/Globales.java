/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Walter Mora
 */
public class Globales {

    public static final int NUMERO_DE_PROVINCIAS = 24;
    public static final int EDAD_MENOR_EDAD = 5;
    public static final int EDAD_MAYOR_EDAD = 70;
    public static final double DESCTO_MENOR_EDAD = 5;
    public static final double DESCTO_MAYOR_EDAD = 10;
    public static final double DESCTO_SOCIO = 2;
    public static final double DESCTO_VIP = 4;
    
    public static String usuarioLogin;

    public static void ponerAnchoColumna(JTable tabla, int columna, int ancho, int minAncho, int maxAncho, boolean redimensionar) {
        TableColumnModel cm = tabla.getColumnModel();
        if (redimensionar == false) {
            cm.getColumn(columna).setMinWidth(minAncho);
            cm.getColumn(columna).setMaxWidth(maxAncho);
        }
        cm.getColumn(columna).setPreferredWidth(ancho);
    }

    public static void setValueCombo(JComboBox combo, String valor) {
        int longitud, posicion, i;
        boolean encontrado = false;
        String valorCombo, etiquetaCombo;

        longitud = combo.getItemCount();
        i = 0;
        while (i < longitud && encontrado == false) {
            combo.setSelectedIndex(i);
            etiquetaCombo = combo.getSelectedItem().toString();
            posicion = etiquetaCombo.indexOf("-");
            if (posicion > - 1) {
                if (posicion == 0) {
                    valorCombo = etiquetaCombo;
                } else {
                    valorCombo = etiquetaCombo.substring(0, posicion == 1 ? 1 : posicion - 1).trim();
                }
                if (valor.equals(valorCombo)) {
                    encontrado = true;
                }
            }
            i++;
        }
        if (encontrado == false) {
            combo.setSelectedIndex(0);
        }
    }

    public static String getValueCombo(JComboBox combo) {
        int posicion;
        String valorCombo, etiquetaCombo;

        etiquetaCombo = combo.getSelectedItem().toString();
        posicion = etiquetaCombo.indexOf("-");
        if (posicion > 0) {
            valorCombo = etiquetaCombo.substring(0, posicion == 1 ? 1 : posicion - 1).trim();
        } else {
            valorCombo = etiquetaCombo.substring(0, etiquetaCombo.length()).trim();
        }

        return valorCombo;
    }

    public static boolean esCedulaValida(String cedula) {
        //verifica que tenga 10 dígitos y que contenga solo valores numéricos
        if (!((cedula.length() == 10) && cedula.matches("^[0-9]{10}$"))) {
            return false;
        }
        //verifica que los dos primeros dígitos correspondan a un valor entre 1 y NUMERO_DE_PROVINCIAS
        int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= NUMERO_DE_PROVINCIAS))) {
            return false;
        }
        //verifica que el último dígito de la cédula sea válido
        int[] d = new int[10];

        //Asignamos el string a un array
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }
        int imp = 0;
        int par = 0;

        //sumamos los duplos de posición impar
        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }
        //sumamos los digitos de posición par
        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }
        //Sumamos los dos resultados
        int suma = imp + par;

        //Restamos de la decena superior
        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1) + "0") - suma;

        //Si es diez el décimo dígito es cero        
        d10 = (d10 == 10) ? 0 : d10;

        //si el décimo dígito calculado es igual al digitado la cédula es correcta
        return d10 == d[9];
    }
}
