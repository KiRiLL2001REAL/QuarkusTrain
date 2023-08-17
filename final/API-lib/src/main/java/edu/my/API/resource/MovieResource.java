package edu.my.API.resource;

import edu.my.API.data.dto.MovieDTO;
import edu.my.API.data.dto.TagDTO;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MovieResource {

    @GET
    @Operation(summary = "Get list of movies", description = "Shows all available movies")
    @APIResponse(
            responseCode = "200",
            description = "List of all movies",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
    )
    Response getAll();

    @GET
    @Path("/{id}")
    @Operation(summary = "Get movie by ID", description = "Shows available movie")
    @APIResponse(
            responseCode = "200",
            description = "Selected movie",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
    )
    Response getById(
            @Parameter(description = "The ID that needs to be fetched", required = true)
            @PathParam("id")
            Long id
    );

    @POST
    @Operation(summary = "Add not existing movie", description = "Adds new movie")
    @APIResponse(
            responseCode = "201",
            description = "Movie is added",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
    )
    Response add(
            @Valid
            MovieDTO movieDTO
    );

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete existing movie", description = "Deletes the movie")
    @APIResponse(
            responseCode = "204",
            description = "Movie is deleted"
    )
    Response deleteById(
            @Parameter(description = "The movie's id that needs to be deleted", required = true)
            @PathParam("id")
            Long id
    );

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update available movie", description = "Updates the movie")
    @APIResponse(
            responseCode = "200",
            description = "Movie is updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
    )
    Response update(
            @Parameter(description = "The ID that needs to find movie to be updated", required = true)
            @PathParam("id")
            Long id,
            @Valid
            MovieDTO movieDTO
    );

    @GET
    @Path("/tags/{id}")
    @Operation(summary = "Get list of movie's tags", description = "Shows all tags which are linked to movie")
    @APIResponse(
            responseCode = "200",
            description = "List of attached tags",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagDTO.class))
    )
    Response getAllTags(
            @Parameter(description = "The ID to get the associated tags", required = true)
            @PathParam("id")
            Long id
    );

    @POST
    @Path("/tag")
    @Operation(summary = "Attach tag", description = "Attaches existing tag to existing movie")
    @APIResponse(
            responseCode = "200",
            description = "List of attached tags",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagDTO.class))
    )
    Response attachTag(
            @Parameter(description = "The movie ID to attach tag", required = true)
            @QueryParam("movie_id")
            Long id,
            @Valid
            TagDTO tagDTO
    );

    @POST
    @Path("/tags")
    @Operation(summary = "Attach multiple tags", description = "Attaches existing tags to existing movie")
    @APIResponse(
            responseCode = "200",
            description = "List of attached tags",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TagDTO.class))
    )
    Response attachTags(
            @Parameter(description = "The movie ID to attach tag", required = true)
            @QueryParam("movie_id")
            Long id,
            List<@Valid TagDTO> tagDTOList
    ) throws SystemException;

    @DELETE
    @Path("/tag")
    @Operation(summary = "Detach existing tag", description = "Deletes link between movie and associated tag")
    @APIResponse(
            responseCode = "200",
            description = "Tag is detached",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class))
    )
    Response detachTag(
            @Parameter(description = "The movie ID to detach tag", required = true)
            @QueryParam("movie_id")
            Long id,
            @Valid
            TagDTO tagDTO
    );

    @DELETE
    @Path("/tags")
    @Operation(summary = "Detach all existing tag", description = "Deletes all links between movie and associated tags")
    @APIResponse(
            responseCode = "204",
            description = "All tags are detached"
    )
    Response detachAllTags(
            @Parameter(description = "The movie ID to detach tags", required = true)
            @QueryParam("movie_id")
            Long id
    ) throws SystemException;
}