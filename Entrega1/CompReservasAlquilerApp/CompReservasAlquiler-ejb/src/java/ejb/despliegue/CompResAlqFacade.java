/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.despliegue;

import com.sun.xml.wss.util.DateUtils;
import ejb.dominio.Alquiler;
import ejb.dominio.Reserva;
import ejb.persistencia.AlquilerFacadeLocal;
import ejb.persistencia.ReservaFacadeLocal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ivan
 */
@Stateless
public class CompResAlqFacade implements CompResAlqFacadeRemote {

    @EJB
    private AlquilerFacadeLocal alquilerFacade;

    @EJB
    private ReservaFacadeLocal reservaFacade;

    public boolean addReserva(Date fechaInicio, Date fechaFin, String nif, String matricula) {
        if (fechaInicio == null || isDateValid(fechaInicio)) {
            return false;
        } else if (fechaFin == null || isDateValid(fechaFin)) {
            return false;
        } else if (nif == null || "".equals(nif.trim())) {
            return false;
        } else if (matricula == null || "".equals(matricula.trim())) {
            return false;
        }
        Reserva r = new Reserva();
        r.setIdreserva(reservaFacade.count() + 1);
        r.setFechareserva(getToday());
        r.setFechainicioalquiler(fechaInicio);
        r.setFechafinalquiler(fechaFin);
        r.setNif(nif);
        r.setMatricula(matricula);
        r.setEjecutada('F');
        reservaFacade.create(r);
        if (reservaFacade.find(r.getIdreserva()) == null) {
            return false;
        }
        return true;
    }

    public List<Reserva> getReservasF(String nif) {
        if (nif == null || "".equals(nif.trim())) {
            return null;
        }
        return reservaFacade.findF(nif);
    }

    public boolean addAlquiler(int reserva, float km, String idEmpleado) {
        if (reserva <= 0) {
            return false;
        } else if (km < 0) {
            return false;
        } else if (idEmpleado == null || "".equals(idEmpleado.trim())) {
            return false;
        }
        Reserva r = reservaFacade.find(reserva);
        if (r == null) {
            return false;
        } else if (r.getEjecutada() == 'T') {
            return false;
        }
        Alquiler a = new Alquiler();
        a.setIdalquiler(alquilerFacade.count() + 1);
        a.setFechainicio(getToday());
        Date dt = getToday();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 10);
        dt = c.getTime();
        a.setFechafin(dt);
        a.setKilometrajesalida(km);
        a.setCliente(r.getNif());
        a.setMatricula(r.getMatricula());
        a.setRealizadopor(idEmpleado);
        r.setEjecutada('T');
        reservaFacade.edit(r);
        if(reservaFacade.find(r.getIdreserva()).getEjecutada() == 'F'){
            return false;
        }
        alquilerFacade.create(a);
        if (alquilerFacade.find(a.getIdalquiler()) == null) {
            return false;
        }
        return true;
    }

    public String[] getReservados(Date fechaInicial, Date fechaFinal) {
        if(fechaInicial == null){
            return null;
        }else if(fechaFinal == null){
            return null;
        }
        return reservaFacade.findInDate(fechaInicial, fechaFinal);
    }

    private Date getToday() {
        return Calendar.getInstance().getTime();
    }

    private boolean isDateValid(Date date) {
        if (date.compareTo(getToday()) > 0) {
            return false;
        }
        return true;
    }
}
