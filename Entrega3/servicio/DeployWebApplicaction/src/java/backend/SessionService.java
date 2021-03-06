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

    
    @Context
    private UriInfo context;

    private final String CORRECTO = "Login correcto";
    private final String INCORRECTO = "Usuario incorrecto";
    private final String BLOQUEADO = "Usuario bloqueado";

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

    private char bloqueado(String NIF) {
        if (NIF == null) {
            return 'E';
        }
        Cliente cliente = clienteFacade.find(NIF);
        if (cliente == null) {
            return 'E';
        }
        return cliente.getBloqueado();

    }

}
