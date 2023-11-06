package io.wulfcodes.blogger.rest.route;

import io.wulfcodes.blogger.rest.model.data.UserData;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.javatuples.Triplet;
import io.wulfcodes.blogger.rest.service.AuthService;
import io.wulfcodes.blogger.rest.model.request.RegistrationRequest;
import io.wulfcodes.blogger.rest.model.response.RegistrationResponse;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Path("/auth")
@Consumes(APPLICATION_FORM_URLENCODED_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private AuthService authService;

    @POST
    @Path("/register")
    public Response userRegistration(@BeanParam RegistrationRequest request) {
        UserData userData = new UserData(request.getEmail(), request.getUsername(), request.getPassword(), request.getProfilePic());

        Triplet<Boolean, Boolean, String> userSaved_userConflict_message = authService.registerUser(userData);
        Boolean userSaved = userSaved_userConflict_message.getValue0();
        Boolean userConflict = userSaved_userConflict_message.getValue1();

        String message = userSaved_userConflict_message.getValue2();
        Status status = userConflict ? CONFLICT : (userSaved ? OK : INTERNAL_SERVER_ERROR);

        RegistrationResponse response = RegistrationResponse.of(status.getStatusCode(), message);

        return Response.status(status).entity(response).build();
    }

    @POST
    @Path("/login")
    public Response userLogin() {
        return null;
    }

    @POST
    @Path("/logout")
    public Response userLogout() {
        return null;
    }

}
