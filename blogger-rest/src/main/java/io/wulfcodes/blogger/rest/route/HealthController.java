package io.wulfcodes.blogger.rest.route;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@Controller
@Path("/health")
public class HealthController {

    @GET
    @Produces(TEXT_PLAIN_VALUE)
    public Response ping() {
        return Response.ok().entity("Hello from blogger app.").build();
    }

}
