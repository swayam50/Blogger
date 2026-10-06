package io.wulfcodes.blogger.rest.route;

import io.wulfcodes.blogger.rest.model.persistent.Comment;
import io.wulfcodes.blogger.rest.service.CommentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;

@RestController
@Path("/comments")
@Consumes(APPLICATION_JSON_VALUE)
@Produces(APPLICATION_JSON_VALUE)
public class CommentResource {

    private final CommentService commentService;

    @Autowired
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @GET
    @Path("/post/{postId}")
    public Response getCommentsByPost(@PathParam("postId") Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return Response.ok(comments).build();
    }

    @POST
    public Response addComment(Comment comment) {
        // TODO: Extract user ID from authenticated context
        String dummyUserId = "1";
        Comment createdComment = commentService.addComment(comment, dummyUserId);
        return Response.status(Response.Status.CREATED).entity(createdComment).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteComment(@PathParam("id") Long id) {
        commentService.deleteComment(id);
        return Response.noContent().build();
    }
}
