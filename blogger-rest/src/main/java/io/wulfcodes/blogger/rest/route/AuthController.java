package io.wulfcodes.blogger.rest.route;

import java.util.Base64;
import java.util.Objects;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.javatuples.Quartet;
import io.wulfcodes.blogger.rest.service.AuthService;
import io.wulfcodes.blogger.rest.model.request.RegistrationRequest;
import io.wulfcodes.blogger.rest.model.request.LoginRequest;
import io.wulfcodes.blogger.rest.model.response.AuthResponse;
import io.wulfcodes.blogger.rest.util.ResourceUtils;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;
import static jakarta.ws.rs.core.Response.Status.OK;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static jakarta.ws.rs.core.HttpHeaders.CONTENT_LOCATION;
import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Cookie.DEFAULT_VERSION;
import static jakarta.ws.rs.core.NewCookie.DEFAULT_MAX_AGE;
import static jakarta.ws.rs.core.NewCookie.SameSite.NONE;

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

        return Response.status(status)
                       .entity(AuthResponse.of(status.getStatusCode(), message))
                       .header(CONTENT_LOCATION, Objects.nonNull(userId) ? ResourceUtils.generateResourceInfoWithId(uriInfo, UserResource.class, "getUser", userId) : null)
                       .build();
    }

    @POST
    @Path("/login")
    public Response userLogin(@BeanParam LoginRequest request) {
        Quartet<Boolean, Boolean, String, String> userFound_userValid_message_accessToken = authService.loginUser(request.getUsername(), request.getPassword());
        boolean userFound = userFound_userValid_message_accessToken.getValue0();
        boolean userValid = userFound_userValid_message_accessToken.getValue1();

        String message = userFound_userValid_message_accessToken.getValue2();
        String accessToken = userFound_userValid_message_accessToken.getValue3();
        String proxyToken = Base64.getEncoder().encodeToString(String.format("%s:%s", request.getUsername(), request.getPassword()).getBytes(ISO_8859_1));
        Status status = userFound ? (userValid ? OK : UNAUTHORIZED) : NOT_FOUND;

        return Response.status(status)
                       .entity(AuthResponse.of(status.getStatusCode(), message))
                       .header("X-Access-Token", accessToken)
                       .header("X-Proxy-Token", proxyToken)
                       .cookie(new NewCookie("access_token", accessToken, "/", ".wulfcodes.io", DEFAULT_VERSION, "Basic or JWT Token", DEFAULT_MAX_AGE, null, false, true, NONE))
                       .build();
    }

    @POST
    @Path("/logout")
    public Response userLogout() {
        return null;
    }

}
