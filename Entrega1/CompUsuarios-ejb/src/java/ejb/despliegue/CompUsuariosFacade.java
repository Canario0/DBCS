/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Usuario;
import ejb.persistencia.UsuarioFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pabrene
 */
@Stateless
public class CompUsuariosFacade implements CompUsuariosFacadeLocal {

    private final static Logger LOGGER = Logger.getLogger(CompUsuariosFacade.class.getName());

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public boolean controlAcceso(String nombre, String clave, String tipoUsuario) {
        LOGGER.log(Level.INFO, "Llamada a controlAcceso");

        Boolean valid = false;
        if (nombre == null || clave == null || tipoUsuario == null) {
            return valid;
        }
        Usuario usuario = usuarioFacade.findByUsuarioAndClave(nombre, clave);
        if (usuario == null) {
            return valid;
        }
        switch (tipoUsuario.toLowerCase()) {
            case "empleado":
                if (usuario.getEmpleado() != null) {
                    valid = true;
                }
                break;
            case "cliente":
                if (usuario.getCliente() != null) {
                    valid = true;
                }
                break;
            default:
                valid = false;
                break;
        }
        return valid;
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
