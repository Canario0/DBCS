/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import dominio.Alquiler;
import dominio.Reserva;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistencia.AlquilerFacadeLocal;
import persistencia.ReservaFacadeLocal;

/**
 * REST Web Service
 *
 * @author ivan
 */
@Path("/user")
public class UserService {

    @Context
    private UriInfo context;

    @EJB
    private AlquilerFacadeLocal alquilerFacade;

    @EJB
    private ReservaFacadeLocal reservaFacade;

    private final String NIFINCORRECTO = "Nif incorrecto";
    private final String RESERVACREADA = "Reserva creada correctamente";
    private final String RESERVAERROR = "Ha habido un error al crear la reserva";
    private final String RESERVAACTUALIZADA = "Reserva actualizada correctamente";
    private final String RESERVAACTUALIZADAERROR = "Ha habido un error al actualizar la reserva";
    private final String RESERVABORRADA = "Reserva borrada correctamente";
    private final String RESERVABORRADAERROR = "Ha habido un error al borrar la reserva";

    /**
     * Creates a new instance of UserService
     */
    public UserService() {
    }

    @GET
    @Path("/{userNif}/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservasUsuario(@PathParam("userNif") String userNif) {
        List<Reserva> reservas = getReservas(userNif);
        if (reservas != null) {
            return Response.status(Response.Status.OK)
                    .entity(reservas.toArray(new Reserva[0]))
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{ \"message\": \"" + NIFINCORRECTO + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/{userNif}/reserva")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAddReserva(@PathParam("userNif") String userNif, Reserva reserva) {
        //we can make use of UserProfile now
        Date fechaInicio = reserva.getFechainicioalquiler();
        Date fechaFin = reserva.getFechafinalquiler();
        String matricula = reserva.getMatricula();

        if (addReserva(fechaInicio, fechaFin, userNif, matricula)) {
            return Response
                    .status(Response.Status.CREATED)
                    .entity("{ \"message\": \"" + RESERVACREADA + "\"}")
                    .build();
        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("{ \"message\": \"" + RESERVAERROR + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{userNif}/reserva/{idReserva}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putFechaReserva(@PathParam("userNif") String userNif, @PathParam("idReserva") String idReserva, Reserva reserva) {
        if (updateReserva(Integer.parseInt(idReserva), reserva.getFechainicioalquiler(), reserva.getFechafinalquiler())) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{ \"message\": \"" + RESERVAACTUALIZADA + "\"}")
                    .build();
        }else{
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("{ \"message\": \"" + RESERVAACTUALIZADAERROR + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{userNif}/reserva/{idReserva}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReserva(@PathParam("userNif") String userNif, @PathParam("idReserva") String idReserva) {
        if (removeReserva(Integer.parseInt(idReserva))) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{ \"message\": \"" + RESERVABORRADA + "\"}")
                    .build();
        }else{
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("{ \"message\": \"" + RESERVABORRADAERROR + "\"}")
                    .build();
        }
    }

    public boolean addReserva(Date fechaInicio, Date fechaFin, String nif, String matricula) {
        if (fechaInicio == null) {
            return false;
        } else if (fechaFin == null) {
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

    public List<Reserva> getReservas(String nif) {
        if (nif == null || "".equals(nif.trim())) {
            return null;
        }
        return reservaFacade.findByNif(nif);
    }

    public boolean updateReserva(int idReserva, Date fechaInicioAlquiler, Date fechaFinAlquiler) {
        if (fechaInicioAlquiler == null) {
            return false;
        } else if (fechaFinAlquiler == null) {
            return false;
        } else if (idReserva <= 0) {
            return false;
        }
       
        Reserva r = reservaFacade.find(idReserva);
        if(r == null){
            return false;
        }
        Reserva edited = new Reserva();
        edited.setIdreserva(idReserva);
        edited.setFechareserva(r.getFechareserva());
        edited.setFechainicioalquiler(fechaInicioAlquiler);
        edited.setFechafinalquiler(fechaFinAlquiler);
        edited.setNif(r.getNif());
        edited.setMatricula(r.getMatricula());
        edited.setEjecutada(r.getEjecutada());

        reservaFacade.edit(edited);
        if (reservaFacade.find(r.getIdreserva()) == null) {
            return false;
        }
        return true;

    }
    
    public boolean removeReserva(int idReserva) {
        if (idReserva <= 0) {
            return false;
        }

        Reserva r = reservaFacade.find(idReserva);
        if(r == null){
            return false;
        }
        reservaFacade.remove(r);

        if (reservaFacade.find(r.getIdreserva()) != null) {
            return false;
        }
        return true;

    }
    
    private Date getToday() {
        return Calendar.getInstance().getTime();
    }
}