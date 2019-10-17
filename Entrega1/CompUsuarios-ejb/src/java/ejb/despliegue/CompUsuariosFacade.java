/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author pabrene
 */
@Stateless
public class CompUsuariosFacade implements CompUsuariosFacadeLocal {
    
    private final static Logger LOGGER = Logger.getLogger(CompUsuariosFacade.class.getName());

    public boolean controlAcceso(String nombre, String clave, String tipoUsuario) {
        LOGGER.log(Level.INFO, "Llamada a controlAcceso");
        return true;
    }

    public String getNIF(String nombre) {
        LOGGER.log(Level.INFO, "Llamada a getNIF");
        return "SSSS";
    }

    public char bloquedo(String NIF) {
        LOGGER.log(Level.INFO, "Llamada a bloqueado");
        return 'T';
    }

    public String[] getLicencias(String NIF) {
        LOGGER.log(Level.INFO, "Llamada a getLicencias");
        return null;
    }
}
