/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.persistencia.VehiculoFacadeLocal;
import ejb.dominio.Vehiculo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author pabrene
 */
@Stateless
public class CompFlotaFacade implements CompFlotaFacadeLocal {

    private final static Logger LOGGER = Logger.getLogger(CompFlotaFacade.class.getName());

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;

    public boolean delVehiculo(String matricula) {
        LOGGER.log(Level.INFO, "Llamada a delVehiculo");
        if (matricula.isEmpty() || matricula == null) {
            return false;
        }
        Vehiculo vehiculo = vehiculoFacade.find(matricula);
        if (vehiculo == null) {
            return false;
        }
        vehiculoFacade.remove(vehiculo);
        return true;
    }
}
