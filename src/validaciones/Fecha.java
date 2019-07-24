/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Walter Mora
 */
public class Fecha {

    public static int edad(int anio, int mes, int dia) {
        Calendar cal = new GregorianCalendar(anio, mes, dia);
        Calendar now = new GregorianCalendar();
        int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
                || (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && cal.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) {
            res--;
        }
        return res;
    }

    public static Timestamp getFecha(String formato, String pFecha) throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
        return new Timestamp(formatoFecha.parse(pFecha).getTime());
    }

    public static String getFecha(String formato, Timestamp pFecha) {
        SimpleDateFormat formatoFecha;
        formatoFecha = new SimpleDateFormat(formato);
        return formatoFecha.format(pFecha);
    }
}
