/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import dominio.Cliente;
import dominio.Reserva;
import dominio.Tipocarnet;
import dominio.Vehiculo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistencia.ClienteFacadeLocal;
import persistencia.LicenciaspormodeloFacadeLocal;
import persistencia.ModeloFacadeLocal;
import persistencia.ReservaFacadeLocal;
import persistencia.VehiculoFacadeLocal;

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
    private ReservaFacadeLocal reservaFacade;
    @EJB
    private VehiculoFacadeLocal vehiculoFacade;
    @EJB
    private LicenciaspormodeloFacadeLocal licenciaspormodeloFacade;
    @EJB
    private ClienteFacadeLocal clienteFacade;

    private final String NIFINCORRECTO = "Nif incorrecto";
    private final String RESERVANOENCONTRADA = "Reserva no encontrada";
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

    /**
     * Endpoint para obtener las reservas de un Usuario dado.
     *
     * @param userNif Nif del usuario del que queremos obtener las reservas.
     * @return La lista de reservas del usuario si todo va bien y un 200, si no,
     * un 404.
     */
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

    /**
     * Endpoint para obtener una reserva concreta.
     *
     * @param userNif Nif del Usuario del que queremos consultar la Reserva.
     * @param idReserva Identificador de la Reserva que queremos consultar.
     * @return Si todo va bien, la Reserva junto con un 200. Si no, un 404.
     */
    @GET
    @Path("/{userNif}/reserva/{idReserva}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReserva(@PathParam("userNif") String userNif, @PathParam("idReserva") String idReserva) {
        Reserva r = reservaFacade.find(Integer.parseInt(idReserva));
        if (r != null) {
            return Response.status(Response.Status.OK)
                    .entity(r)
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{ \"message\": \"" + RESERVANOENCONTRADA + "\"}")
                    .build();
        }
    }

    /**
     * Endpoint para obtener los Vehiculos que puede reservar un Usuario entre
     * un intervalo de fechas dado.
     *
     * @param userNif Usuario que quiere consultar los Vehiculos disponibles.
     * @param fechaInicio Fecha de inicio del intervalo de consulta.
     * @param fechaFin Fecha de fin del intervalo de consulta.
     * @return Si todo va bien, la lista de Vehiculos disponibles con un 200. Si no, un 404.
     */
    @GET
    @Path("/{userNif}/car")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@PathParam("userNif") String userNif, @QueryParam("fechaInicio") String fechaInicio, @QueryParam("fechaFin") String fechaFin) {
        Date inicio = null;
        Date fin = null;
        List<Vehiculo> vehiculos = null;
        try {
            inicio = new SimpleDateFormat("yyyy-mm-dd").parse(fechaInicio);
            fin = new SimpleDateFormat("yyyy-mm-dd").parse(fechaFin);
            vehiculos = getVehiculos(getLicencias(userNif), inicio, fin);
            if (vehiculos != null) {
                return Response.status(Response.Status.OK)
                        .entity(vehiculos.toArray(new Vehiculo[0]))
                        .build();

            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("{ \"message\": \"" + RESERVANOENCONTRADA + "\"}")
                        .build();
            }
        } catch (ParseException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{ \"message\": \"" + RESERVANOENCONTRADA + "\"}")
                    .build();
        }
    }

    /**
     * Endpoint para introducir una Reserva nueva en la base de datos.
     * @param userNif Usuario que quiere realizar la nueva Reserva.
     * @param reserva Reserva con los datos seleccionados por el Usuario.
     * @return Un 201 si todo va bien, si no, un 401.
     */
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

    /**
     * Endpoint para modificar una Reserva de un Usuario.
     * @param userNif Usuario que va a realizar la modificacion.
     * @param idReserva Reserva sobre la que hacer la modificacion.
     * @param reserva Reserva con los datos modificados.
     * @return Si todo va bien un 200, si no, un 403.
     */
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
        } else {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("{ \"message\": \"" + RESERVAACTUALIZADAERROR + "\"}")
                    .build();
        }
    }

    /**
     * Endpoint para Eliminar una Reserva de un Usuario dado.
     * @param userNif Usuario que va a eliminar la Reserva.
     * @param idReserva Identificador de la Reserva que se quiere eliminar.
     * @return Si todo va bien, un 200, si no, un 403.
     */
    @DELETE
    @Path("/{userNif}/reserva/{idReserva}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReserva(@PathParam("userNif") String userNif, @PathParam("idReserva") String idReserva) {
        if (removeReserva(Integer.parseInt(idReserva))) {
            return Response
                    .status(Response.Status.OK)
                    .entity("{ \"message\": \"" + RESERVABORRADA + "\"}")
                    .build();
        } else {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity("{ \"message\": \"" + RESERVABORRADAERROR + "\"}")
                    .build();
        }
    }

    private boolean addReserva(Date fechaInicio, Date fechaFin, String nif, String matricula) {
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

    private List<Reserva> getReservas(String nif) {
        if (nif == null || "".equals(nif.trim())) {
            return null;
        }
        return reservaFacade.findByNif(nif);
    }

    private boolean updateReserva(int idReserva, Date fechaInicioAlquiler, Date fechaFinAlquiler) {
        if (fechaInicioAlquiler == null) {
            return false;
        } else if (fechaFinAlquiler == null) {
            return false;
        } else if (idReserva <= 0) {
            return false;
        }

        Reserva r = reservaFacade.find(idReserva);
        if (r == null) {
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

    private boolean removeReserva(int idReserva) {
        if (idReserva <= 0) {
            return false;
        }

        Reserva r = reservaFacade.find(idReserva);
        if (r == null) {
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

    private List<Vehiculo> getVehiculos(String[] licencias, Date fechaIni, Date fechaFin) {
        if (licencias == null) {
            return null;
        } else if (fechaIni == null) {
            return null;
        } else if (fechaFin == null) {
            return null;
        }
        List<Vehiculo> vehiculos = vehiculoFacade.findAll();
        if (vehiculos == null) {
            return null;
        }
        String[] reservados = getReservados(fechaIni, fechaFin);
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
        System.out.println(Arrays.toString(licencias));
        List<Vehiculo> result = new ArrayList<Vehiculo>();
        List<String> idModelos;
        List<String> licenciasList = Arrays.asList(licencias);
        for (Vehiculo v : disponibles) {
            idModelos = licenciaspormodeloFacade.findByIdModelo(v.getModelo());
            for (String m : idModelos) {
                if (licenciasList.contains(m)) {
                    result.add(v);
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(result.toArray()));

        return result;
    }

    private String[] getReservados(Date fechaInicial, Date fechaFinal) {
        if (fechaInicial == null) {
            return null;
        } else if (fechaFinal == null) {
            return null;
        }
        return reservaFacade.findInDate(fechaInicial, fechaFinal);
    }

    private String[] getLicencias(String NIF) {
        if (NIF == null) {
            return null;
        }
        Cliente cliente = clienteFacade.find(NIF);
        if (cliente == null) {
            return null;
        }
        // Tipocarnet::getTipo es lo mismo que hacer x -> x.getTypo y String[]::new es lo mismo que hacer size -> new String[size]
        //return cliente.getTipocarnetList().stream().map(Tipocarnet::getTipo).toArray(String[]::new);
        // Este fragmento está comentado porque produce conflictos con el servidor embebido de testing
        List<Tipocarnet> tipoCarnets = cliente.getTipocarnetList();
        if (tipoCarnets == null) {
            return null;
        }
        String[] licencias = new String[tipoCarnets.size()];
        for (int i = 0; i < tipoCarnets.size(); i++) {
            licencias[i] = tipoCarnets.get(i).getTipo();
        }
        return licencias;
    }

}
