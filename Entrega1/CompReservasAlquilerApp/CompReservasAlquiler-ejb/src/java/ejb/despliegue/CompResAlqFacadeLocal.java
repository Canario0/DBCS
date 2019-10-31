/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import ejb.dominio.Reserva;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ivan
 */
@Local
public interface CompResAlqFacadeLocal {
    
    boolean addReserva(Date fechaInicio, Date fechaFin, String nif, String matricula);
    List<Reserva> getReservasF(String nif);
    boolean addAlquiler(int reserva, float km, String idEmpleado);
    String[] getReservados(Date fechaInicial, Date fechaFinal);
    
    
}
