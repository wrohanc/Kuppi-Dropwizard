package com.ro.learn.resources;

import com.ro.learn.util.HTTP;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by rohan on 2016-12-13.
 */
@Path("/user_service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(
        value = "User services",
        description = "User management service.",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/dropwizard-kuppi/user_service"
)
public class UserResource {
    @Path("/hello/{name}")
    @GET
    @ApiOperation(
            value = "Say hello",
            notes = "Services to manage orders",
            httpMethod = "GET",
            response = Object.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = HTTP.HTTP_OK, message = "Say hello successful."),
            @ApiResponse(code = HTTP.HTTP_NO_CONTENT, message = "Say hello failed.")
    })
    public Response sayHello(@ApiParam @PathParam("name") String name) throws URISyntaxException {
        if (name == null || name.equals("Rohan")) {
            return Response.noContent().build();
        } else {
            return Response.ok("Hello " + name + "!").build();
        }
    }
}
