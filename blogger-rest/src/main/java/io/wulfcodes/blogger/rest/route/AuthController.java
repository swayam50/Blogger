package io.wulfcodes.blogger.rest.route;

import java.util.Objects;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.javatuples.Triplet;
import org.javatuples.Quartet;
import io.wulfcodes.blogger.rest.service.AuthService;
import io.wulfcodes.blogger.rest.model.request.RegistrationRequest;
import io.wulfcodes.blogger.rest.model.request.LoginRequest;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;
import io.wulfcodes.blogger.rest.util.ResourceUtils;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_LOCATION;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Controller
@Path("/auth")
@Consumes(APPLICATION_FORM_URLENCODED)
@Produces(APPLICATION_JSON)
public class AuthController {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private AuthService authService;

    @POST
    @Path("/register")
    public Response userRegistration(@BeanParam RegistrationRequest request) {
        Quartet<Boolean, Boolean, String, String> userSaved_userConflict_userId_message = authService.registerUser(request.getEmail(), request.getUsername(), request.getPassword(), request.getProfilePic());
        Boolean userSaved = userSaved_userConflict_userId_message.getValue0();
        Boolean userConflict = userSaved_userConflict_userId_message.getValue1();
        String userId = userSaved_userConflict_userId_message.getValue2();

        String message = userSaved_userConflict_userId_message.getValue3();
        Status status = userConflict ? CONFLICT : (userSaved ? CREATED : INTERNAL_SERVER_ERROR);

        AuthResponse response = AuthResponse.of(status.getStatusCode(), message);

        return Response.status(status).entity(response)
                       .header(CONTENT_LOCATION, Objects.nonNull(userId) ? ResourceUtils.generateResourceInfoWithId(uriInfo, UserResource.class, "getUser", userId) : null)
                       .build();
    }

    @POST
    @Path("/login")
    public Response userLogin(@BeanParam LoginRequest request) {
        Triplet<Boolean, Boolean, String> userFound_userValid = authService.loginUser(request.getUsername(), request.getPassword());
        boolean userFound = userFound_userValid.getValue0();
        boolean userValid = userFound_userValid.getValue1();

        String message = userFound_userValid.getValue2();
        Status status = userFound ? (userValid ? OK : UNAUTHORIZED) : NOT_FOUND;

        AuthResponse response = AuthResponse.of(status.getStatusCode(), message);

        return Response.status(status).entity(response).build();
    }

    @POST
    @Path("/logout")
    public Response userLogout() {
        return null;
    }

}
