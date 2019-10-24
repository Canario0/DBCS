/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import javax.ejb.Local;

/**
 *
 * @author pabrene
 */
@Local
public interface CompFlotaFacadeLocal {
    
    /**
     * Elimina un vhículo del sistema.
     *
     * @param matricula matrícula del vehículo que se quiere borrar.
     * @return true si el vehículo se ha borrado correctamente, false en cualquier otro caso.
     * contrario.
     */
    boolean delVehiculo (String matricula);
}
