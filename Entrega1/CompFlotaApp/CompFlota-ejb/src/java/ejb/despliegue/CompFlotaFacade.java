/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Licenciaspormodelo;
import ejb.dominio.Modelo;
import ejb.persistencia.VehiculoFacadeLocal;
import ejb.dominio.Vehiculo;
import ejb.persistencia.LicenciaspormodeloFacadeLocal;
import ejb.persistencia.ModeloFacadeLocal;
import java.util.ArrayList;
import java.util.Arrays;
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
    private CompResAlqFacadeRemote reservaAlquilerRemote;
    @EJB
    private VehiculoFacadeLocal vehiculoFacade;
    @EJB
    private ModeloFacadeLocal modeloFacade;
    @EJB
    private LicenciaspormodeloFacadeLocal licenciaspormodeloFacade;

    public List<Vehiculo> getVehiculos(String[] licencias, Date fechaIni, Date fechaFin) {
        if (licencias == null) {
            return null;
        } else if (fechaIni == null) {
            return null;
        } else if (fechaFin == null) {
            return null;
        }

        List<Vehiculo> vehiculos = vehiculoFacade.findNotAveriado('F');
        if (vehiculos == null) {
            return null;
        }

        String[] reservados = reservaAlquilerRemote.getReservados(fechaIni, fechaFin);
        if (reservados == null) {
            return null;
        }

        String matricula = null;
        List<Vehiculo> disponibles = new ArrayList<Vehiculo>();
        List<String> reservadosList = Arrays.asList(reservados);
        for (Vehiculo v : vehiculos) {
            matricula = v.getMatricula();
            if (!reservadosList.contains(matricula)) {
                disponibles.add(v);
            }
        }

        List<Vehiculo> result = new ArrayList<Vehiculo>();
        List<String> idModelos;
        List<String> licenciasList = Arrays.asList(licencias);
        for (Vehiculo v : disponibles) {
            idModelos = licenciaspormodeloFacade.findByIdModelo(v.getIdmodelo());
            for (String m : idModelos) {
                if (licenciasList.contains(m)) {
                    result.add(v);
                    break;
                }
            }
        }

        return result;

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
