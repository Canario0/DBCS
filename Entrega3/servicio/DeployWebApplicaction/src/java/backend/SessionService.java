package backend;

import dominio.Alquiler;
import dominio.Cliente;
import dominio.Reserva;
import dominio.Tipocarnet;
import dominio.Usuario;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import persistencia.AlquilerFacadeLocal;
import persistencia.ClienteFacadeLocal;
import persistencia.ReservaFacadeLocal;
import persistencia.UsuarioFacadeLocal;

/**
 * Clase para gestionar la sesión de los usuarios dentro de la aplicación
 *
 * @author ivan
 */
@Path("/login")
public class SessionService {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    @EJB
    private ClienteFacadeLocal clienteFacade;
    @EJB
    private AlquilerFacadeLocal alquilerFacade;

    @EJB
    private ReservaFacadeLocal reservaFacade;

    
    @Context
    private UriInfo context;

    private final String CORRECTO = "Login correcto";
    private final String INCORRECTO = "Usuario incorrecto";
    private final String BLOQUEADO = "Usuario bloqueado";
    private final String NIFINCORRECTO = "Nif incorrecto";

    public SessionService() {

    }

    /**
     * Este método controlará el logeo de usuarios en la aplicación.
     *
     * @param username Nombre del usuario que entrará en la aplicación.
     * @param headers Clave asociada al usuario que entrará en la aplicación.
     * @return Código asociado a la respuesta proporcionada por el servidor,
     * teniendo en cuenta los datos recibidos.
     */
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserLogin(@PathParam("username") String username,
            @Context HttpHeaders headers) {

        String authorization = headers.getRequestHeader("Authorization").get(0);
        boolean isValid = controlAccesos(username, authorization, "cliente");

        if (isValid) {

            char isBlocked = bloqueado(getNIF(username));
            if (isBlocked == 'T') {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("{ \"message\": \"" + BLOQUEADO + "\"}")
                        .build();
            } else {
                String nif = getNIF(username);
                return Response
                        .status(Response.Status.OK)
                        .entity("{ \"nif\": \""+nif+"\", \"message\": \"" + CORRECTO + "\"}")
                        .build();
            }

        } else {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("{ \"message\": \"" + INCORRECTO + "\"}")
                    .build();

        }
    }

     @GET
    @Path("/{userNif}/reserva")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservasUsuario(@PathParam("userNif") String userNif) {

        List<Reserva> reservas = getReservas(userNif);
        System.out.println("Estoy cogiendo las reservas");
        if (reservas != null) {
            GenericEntity<List<Reserva>> entity = 
            new GenericEntity<List<Reserva>>(reservas) {};
            return Response.status(Response.Status.OK)
                    .entity(entity)
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("{ \"message\": \"" + NIFINCORRECTO + "\"}")
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
        if (reservaFacade.find(r.getIdreserva()).getEjecutada() == 'F') {
            return false;
        }
        alquilerFacade.create(a);
        if (alquilerFacade.find(a.getIdalquiler()) == null) {
            return false;
        }
        return true;
    }

    public String[] getReservados(Date fechaInicial, Date fechaFinal) {
        if (fechaInicial == null) {
            return null;
        } else if (fechaFinal == null) {
            return null;
        }
        return reservaFacade.findInDate(fechaInicial, fechaFinal);
    }

    private Date getToday() {
        return Calendar.getInstance().getTime();
    }
    private boolean controlAccesos(String nombre, String clave, String tipoUsuario) {

        Boolean valid = false;
        if (nombre == null || clave == null || tipoUsuario == null) {
            return valid;
        }
        Usuario usuario = usuarioFacade.findByUsuarioAndClave(nombre, clave);
        if (usuario == null) {
            return valid;
        }
        switch (tipoUsuario.toLowerCase()) {
            case "empleado":
                if (usuario.getEmpleado() != null) {
                    valid = true;
                }
                break;
            case "cliente":
                if (usuario.getCliente() != null) {
                    valid = true;
                }
                break;
            default:
                valid = false;
                break;
        }
        return valid;
    }

    private String getNIF(String nombre) {
        String nif = null;
        nif = usuarioFacade.findByUsuario(nombre);
        return nif;
    }

    public char bloqueado(String NIF) {
        if (NIF == null) {
            return 'E';
        }
        Cliente cliente = clienteFacade.find(NIF);
        if (cliente == null) {
            return 'E';
        }
        return cliente.getBloqueado();

    }

    public String[] getLicencias(String NIF) {
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
