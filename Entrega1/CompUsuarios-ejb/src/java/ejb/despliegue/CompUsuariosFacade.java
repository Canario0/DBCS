/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import javax.ejb.Stateless;

/**
 *
 * @author pabrene
 */
@Stateless
public class CompUsuariosFacade implements CompUsuariosFacadeLocal {

    public boolean controlAcceso(String nombre, String clave, String tipoUsuario) {
        return true;
    }

    public String getNIF(String nombre) {
        return "SSSS";
    }

    public char bloquedo(String NIF) {
        return 'T';
    }

    public String[] getLicencias(String NIF) {
        return null;
    }
}
