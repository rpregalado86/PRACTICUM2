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
public class Cliente extends Persona {
    private int intIdCliente;
    private String strTipoCliente;

    /**
     * @return the intIdCliente
     */
    public int getIntIdCliente() {
        return intIdCliente;
    }

    /**
     * @param intIdCliente the intIdCliente to set
     */
    public void setIntIdCliente(int intIdCliente) {
        this.intIdCliente = intIdCliente;
    }

    /**
     * @return the strTipoCliente
     */
    public String getStrTipoCliente() {
        return strTipoCliente;
    }

    /**
     * @param strTipoCliente the strTipoCliente to set
     */
    public void setStrTipoCliente(String strTipoCliente) {
        this.strTipoCliente = strTipoCliente;
    }
}
