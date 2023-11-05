package io.wulfcodes.blogger.rest.route;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.javatuples.Pair;
import io.wulfcodes.blogger.rest.model.request.RegistrationRequest;
import io.wulfcodes.blogger.rest.model.response.RegistrationResponse;
import io.wulfcodes.blogger.rest.service.UserService;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.OK;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Path("/auth")
@Consumes(APPLICATION_FORM_URLENCODED_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @POST
    @Path("/register")
    public Response userRegistration(@BeanParam RegistrationRequest request) {
        Pair<Boolean, Boolean> email_username = userService.checkUserExistence(request.getEmail(), request.getUsername());
        boolean emailExists = email_username.getValue0().booleanValue();
        boolean usernameExists = email_username.getValue1().booleanValue();

        if (emailExists || usernameExists) {
            return Response.status(CONFLICT)
                           .entity(RegistrationResponse.of(CONFLICT.getStatusCode(), "%s already exists!".formatted(emailExists ? "Email" : "Username")))
                           .build();
        }

        String userId = userService.saveUser(request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getProfilePic());

        return Response.status(OK)
                .entity(RegistrationResponse.of(OK.getStatusCode(), "User saved with id '%s' successfully".formatted(userId)))
                .build();
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
