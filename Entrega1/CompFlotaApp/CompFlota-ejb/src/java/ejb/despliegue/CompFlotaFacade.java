/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Modelo;
import ejb.persistencia.VehiculoFacadeLocal;
import ejb.dominio.Vehiculo;
import ejb.persistencia.ModeloFacadeLocal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;

/**
 *
 * @author pabrene
 */
@Stateless
public class CompFlotaFacade implements CompFlotaFacadeLocal {

    private final static Logger LOGGER = Logger.getLogger(CompFlotaFacade.class.getName());

    @EJB
    private VehiculoFacadeLocal vehiculoFacade;
    @EJB
    private ModeloFacadeLocal modeloFacade;

    public List<Vehiculo> getVehiculos(String[] licencias, Date fechaIni, Date fechaFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean addVehiculo(String idModelo, String matricula, String color, float km, char averiado) {
        LOGGER.log(Level.INFO, "Llamada a addVehiculo");
        if (idModelo == null || idModelo.isEmpty() || matricula == null || matricula.isEmpty() || color == null || color.isEmpty() || km < 0 || (averiado != 'T' && averiado != 'F')) {
            return false;
        }
        Modelo modelo = modeloFacade.find(idModelo);
        if (modelo == null) {
            return false;
        }
        Vehiculo vehiculo = new Vehiculo(matricula, color, km, averiado);
        vehiculo.setIdmodelo(modelo);
        try {
            vehiculoFacade.create(vehiculo);
        } catch (EntityExistsException e) {
            return false;
        }
        return true;
    }

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
