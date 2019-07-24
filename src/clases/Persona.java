/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Timestamp;

/**
 *
 * @author Walter Mora
 */
public abstract class Persona {
    private int intIdPersona;
    private String strIdentificacion;
    private String strNombres;
    private String strApellidos;
    private Timestamp datFechaNacimiento;

    /**
     * @return the intIdPersona
     */
    public int getIntIdPersona() {
        return intIdPersona;
    }

    /**
     * @param intIdPersona the intIdPersona to set
     */
    public void setIntIdPersona(int intIdPersona) {
        this.intIdPersona = intIdPersona;
    }

    /**
     * @return the strIdentificacion
     */
    public String getStrIdentificacion() {
        return strIdentificacion;
    }

    /**
     * @param strIdentificacion the strIdentificacion to set
     */
    public void setStrIdentificacion(String strIdentificacion) {
        this.strIdentificacion = strIdentificacion;
    }

    /**
     * @return the strNombres
     */
    public String getStrNombres() {
        return strNombres;
    }

    /**
     * @param strNombres the strNombres to set
     */
    public void setStrNombres(String strNombres) {
        this.strNombres = strNombres;
    }

    /**
     * @return the strApellidos
     */
    public String getStrApellidos() {
        return strApellidos;
    }

    /**
     * @param strApellidos the strApellidos to set
     */
    public void setStrApellidos(String strApellidos) {
        this.strApellidos = strApellidos;
    }

    /**
     * @return the datFechaNacimiento
     */
    public Timestamp getDatFechaNacimiento() {
        return datFechaNacimiento;
    }

    /**
     * @param datFechaNacimiento the datFechaNacimiento to set
     */
    public void setDatFechaNacimiento(Timestamp datFechaNacimiento) {
        this.datFechaNacimiento = datFechaNacimiento;
    }
}
