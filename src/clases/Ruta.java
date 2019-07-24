/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Walter Mora
 */
public class Ruta {
    private int intIdRuta;
    private String strNombre;
    private String strOrigen;
    private String strDestino;
    private double douCosto;

    /**
     * @return the intIdRuta
     */
    public int getIntIdRuta() {
        return intIdRuta;
    }

    /**
     * @param intIdRuta the intIdRuta to set
     */
    public void setIntIdRuta(int intIdRuta) {
        this.intIdRuta = intIdRuta;
    }

    /**
     * @return the strNombre
     */
    public String getStrNombre() {
        return strNombre;
    }

    /**
     * @param strNombre the strNombre to set
     */
    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    /**
     * @return the strOrigen
     */
    public String getStrOrigen() {
        return strOrigen;
    }

    /**
     * @param strOrigen the strOrigen to set
     */
    public void setStrOrigen(String strOrigen) {
        this.strOrigen = strOrigen;
    }

    /**
     * @return the strDestino
     */
    public String getStrDestino() {
        return strDestino;
    }

    /**
     * @param strDestino the strDestino to set
     */
    public void setStrDestino(String strDestino) {
        this.strDestino = strDestino;
    }

    /**
     * @return the douCosto
     */
    public double getDouCosto() {
        return douCosto;
    }

    /**
     * @param douCosto the douCosto to set
     */
    public void setDouCosto(double douCosto) {
        this.douCosto = douCosto;
    }
}
