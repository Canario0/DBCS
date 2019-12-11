import javax.ws.rs.core;

@Path("/login-service")
public class LoginService {

    private UriInfo context;

    public LoginService() {

    }

    @GET
    @Path("/order/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUserByName(@PathParam("userName") String userName,
            @PathParam("userPassword") String userPassword) {
        return null;
    }

    private boolean isUserAuthenticated() {
        return false;
    }

}