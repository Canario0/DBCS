/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import javax.ejb.Remote;
import ejb.dominio.Reserva;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ivan
 */
@Remote
public interface CompResAlqFacadeRemote {
    /**
     * Método para añadir una nueva Reserva al sistema
     * 
     * @param fechaInicio fecha de inicio de la reserva. Debe ser posterior a la
     *                    fecha actual
     * @param fechaFin    fecha fin de la reserva. Debe ser posterior a la fecha de
     *                    Inicio y a la fecha actual
     * @param nif         NIF del cliente.
     * @param matricula   matricula del coche que se quiere reservar.
     * @return si la reserva se ha realizado con éxito devolverá True, en caso
     *         contrario devolverá false.
     */
    boolean addReserva(Date fechaInicio, Date fechaFin, String nif, String matricula);

    /**
     * Método para obtener las reservar no ejecutadas del cliente
     * 
     * @param nif NIF del cliente.
     * @return Lista de las reservas no ejecutadas, en caso de error null.
     */
    List<Reserva> getReservasF(String nif);

    /**
     * Método para añadir un nuevo alquiler al sistema.
     * @param reserva id de la reservar
     * @param km kilometros actuales del coche
     * @param idEmpleado id del empleado que registra la operación
     * @return true si la operación ha sido completada, false en caso contrario.
     */
    boolean addAlquiler(int reserva, float km, String idEmpleado);
    /**
     * Método para obtener las matriculas de los coches reservados entre dos fechas
     * @param fechaInicial fecha inicial del period
     * @param fechaFinal fecha final del periodo. Debe ser posterior a la fecha inicial.
     * @return array de string conteniendo las matrículas, o en caso de error null.
     */
    String[] getReservados(Date fechaInicial, Date fechaFinal);
}
