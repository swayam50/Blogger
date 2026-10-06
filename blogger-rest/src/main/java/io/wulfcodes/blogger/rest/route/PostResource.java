package io.wulfcodes.blogger.rest.route;

import io.wulfcodes.blogger.rest.model.persistent.Post;
import io.wulfcodes.blogger.rest.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;
import java.util.Optional;

@RestController
@Path("/posts")
@Consumes(APPLICATION_JSON_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class PostResource {

    private final PostService postService;

    @Autowired
    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GET
    public Response getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return Response.ok(posts).build();
    }

    @GET
    @Path("/{id}")
    public Response getPostById(@PathParam("id") Long id) {
        Optional<Post> post = postService.getPostById(id);
        if (post.isPresent()) {
            return Response.ok(post.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response createPost(Post post) {
        // TODO: Extract user ID from authenticated context instead of hardcoding
        String dummyUserId = "1";
        Post createdPost = postService.createPost(post, dummyUserId);
        return Response.status(Response.Status.CREATED).entity(createdPost).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") Long id) {
        postService.deletePost(id);
        return Response.noContent().build();
    }
}
