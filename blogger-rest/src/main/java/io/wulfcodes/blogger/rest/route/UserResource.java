package io.wulfcodes.blogger.rest.route;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import io.wulfcodes.blogger.rest.model.request.UserRequest;
import io.wulfcodes.blogger.rest.service.UserService;
import io.wulfcodes.blogger.rest.util.IOUtils;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Path("/users")
@Consumes(APPLICATION_JSON_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    private UserService userService;

    @GET
    public Response ping() {
        return Response.ok().entity("Hello World").build();
    }

    @POST
    @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Response addUser(UserRequest request) {
        IOUtils.saveImageFile(String.format("%s-user-pic", request.getUsername()), request.getEncodedImage());
        return Response.ok().entity("Saved").build();
    }

}
