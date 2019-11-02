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

    /**
     * Método para obtener aquelos vehículos disponibles para reservar entre dos
     * fechas
     * 
     * @param licencias licencias de los vehículos solicitados
     * @param fechaIni fecha de inicio
     * @param fechaFin fecha fin
     * @return lista de los vehículos disponibles, en caso de error null
     */
    List<Vehiculo> getVehiculos(String[] licencias, Date fechaIni, Date fechaFin);

    /**
     * Añade un vehículo al sistema
     *
     * @param idModelo  id del modelo en el sistema
     * @param matricula matricula del vehículo
     * @param color     color del vehículo
     * @param km        km actuales del vehículo
     * @param averiado  estado actual del vehículo, si se encuentra averiado el
     *                  valor debe ser T si no F
     * @return true si todo ha sido correcto, false en otro caso
     */
    boolean addVehiculo(String idModelo, String matricula, String color, float km, char averiado);

    /**
     * Elimina un vhículo del sistema.
     *
     * @param matricula matrícula del vehículo que se quiere borrar.
     * @return true si el vehículo se ha borrado correctamente, false en cualquier
     *         otro caso. contrario.
     */
    boolean delVehiculo(String matricula);
}
