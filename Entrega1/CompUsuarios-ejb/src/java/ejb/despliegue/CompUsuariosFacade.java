/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Cliente;
import ejb.dominio.Tipocarnet;
import ejb.dominio.Usuario;
import ejb.persistencia.ClienteFacadeLocal;
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
    @EJB
    private ClienteFacadeLocal clienteFacade;

    public boolean controlAccesos(String nombre, String clave, String tipoUsuario) {
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

    public char bloqueado(String NIF) {
        LOGGER.log(Level.INFO, "Llamada a bloqueado");
        if (NIF == null) {
            return 'E';
        }
        Cliente cliente = clienteFacade.find(NIF);
        if (cliente == null) {
            return 'E';
        }
        return cliente.getBloqueado();

    }

    public String[] getLicencias(String NIF) {
        LOGGER.log(Level.INFO, "Llamada a getLicencias");
        if (NIF == null) {
            return null;
        }
        Cliente cliente = clienteFacade.find(NIF);
        if (cliente == null) {
            return null;
        }
        // Tipocarnet::getTipo es lo mismo que hacer x -> x.getTypo y String[]::new es lo mismo que hacer size -> new String[size]
        return cliente.getTipocarnetList().stream().map(Tipocarnet::getTipo).toArray(String[]::new);
    }
}
