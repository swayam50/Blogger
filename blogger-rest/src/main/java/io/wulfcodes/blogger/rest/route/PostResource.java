package io.wulfcodes.blogger.rest.route;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Path("/posts")
@Consumes(APPLICATION_JSON_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class PostResource {
}
