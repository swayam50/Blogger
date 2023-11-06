package io.wulfcodes.blogger.rest.route;

import io.wulfcodes.blogger.rest.model.data.UserData;
import io.wulfcodes.blogger.rest.model.persistent.Image;
import io.wulfcodes.blogger.rest.model.persistent.User;
import io.wulfcodes.blogger.rest.model.response.UserResponse;
import io.wulfcodes.blogger.rest.service.ImageService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import io.wulfcodes.blogger.rest.service.UserService;

import static jakarta.ws.rs.core.Response.Status.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Path("/users")
@Consumes(APPLICATION_JSON_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        User user = userService.fetchUser(userId);
        Image userImage = imageService.fetchUserImage(userId);

        UserData userData = new UserData(user.getEmail(), user.getUsername(), user.getPassword(), userImage.getImageEncoded());
        UserResponse response = UserResponse.of(OK.getStatusCode(), "User fetched successfully.", userData);

        return Response.status(response.status()).entity(response).build();
    }

}
