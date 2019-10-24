/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Vehiculo;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pabrene
 */
@Local
public interface CompFlotaFacadeLocal {

    List<Vehiculo> getVehiculos(String[] licencias, Date fechaIni, Date fechaFin);

    /**
     * Añade un vehículo al sistema
     *
     * @param idModelo
     * @param matricula
     * @param color
     * @param km
     * @param averiado
     * @return
     */
    boolean addVehiculo(String idModelo, String matricula, String color, float km, char averiado);

    /**
     * Elimina un vhículo del sistema.
     *
     * @param matricula matrícula del vehículo que se quiere borrar.
     * @return true si el vehículo se ha borrado correctamente, false en
     * cualquier otro caso. contrario.
     */
    boolean delVehiculo(String matricula);
}
