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
public class Venta {

    private int intIdVenta;
    private Cliente cliente;
    private Ruta ruta;
    private Timestamp datFecha;
    private String strHora;
    private double dobCosto;
    private Usuario usuario;

    /**
     * @return the intIdVenta
     */
    public int getIntIdVenta() {
        return intIdVenta;
    }

    /**
     * @param intIdVenta the intIdVenta to set
     */
    public void setIntIdVenta(int intIdVenta) {
        this.intIdVenta = intIdVenta;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the ruta
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the datFecha
     */
    public Timestamp getDatFecha() {
        return datFecha;
    }

    /**
     * @param datFecha the datFecha to set
     */
    public void setDatFecha(Timestamp datFecha) {
        this.datFecha = datFecha;
    }

    /**
     * @return the strHora
     */
    public String getStrHora() {
        return strHora;
    }

    /**
     * @param strHora the strHora to set
     */
    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    /**
     * @return the dobCosto
     */
    public double getDobCosto() {
        return dobCosto;
    }

    /**
     * @param dobCosto the dobCosto to set
     */
    public void setDobCosto(double dobCosto) {
        this.dobCosto = dobCosto;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}