package com.ro.learn.resources;

import com.ro.learn.api.SearchRequestAPI;
import com.ro.learn.api.SearchResponseAPI;
import com.ro.learn.model.Order;
import com.ro.learn.services.OrderService;
import com.ro.learn.util.HTTP;
import com.wordnik.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;

/**
 * Created by rohan on 2016-12-13.
 */
@Path("/order_service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(
        value = "Place order services",
        description = "Order management service.",
        consumes = MediaType.APPLICATION_JSON,
        produces = MediaType.APPLICATION_JSON,
        basePath = "/dropwizard-kuppi/order_service"
)
public class OrderResource {
    OrderService orderService = new OrderService();

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

    @Path("/order")
    @PUT
    @ApiOperation(
            value = "Place order",
            notes = "Services to manage orders",
            httpMethod = "PUT",
            response = Order.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = HTTP.HTTP_OK, message = "Place order successful."),
            @ApiResponse(code = HTTP.HTTP_NO_CONTENT, message = "Place order failed.")
    })
    public Response placeOrder(@ApiParam @Valid Order order) throws URISyntaxException {
        order = orderService.placeOrder(order);
        if (order == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(order).build();
        }
    }

    @Path("/amend")
    @POST
    @ApiOperation(
            value = "Amend order",
            notes = "Services to manage orders",
            httpMethod = "POST",
            response = Order.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = HTTP.HTTP_OK, message = "Amend order successful."),
            @ApiResponse(code = HTTP.HTTP_NO_CONTENT, message = "Amend order failed.")
    })
    public Response amendOrder(@ApiParam @Valid Order order) throws URISyntaxException {
        order = orderService.amendOrder(order);
        if (order == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(order).build();
        }
    }

    @GET
    @Path("/orders/{productCode}")
    @ApiOperation(
            value = "findByOrganizationId",
            notes = "Find addons by organization",
            httpMethod = "GET",
            response = SearchResponseAPI.class
    )
    public Response findByProduct(@ApiParam @PathParam("productCode") @DefaultValue("0") String productCode,
                                  @ApiParam @QueryParam("offset") @DefaultValue("0") int offset,
                                  @ApiParam @QueryParam("limit") @DefaultValue("10") short limit) throws URISyntaxException {
        SearchRequestAPI api = new SearchRequestAPI();
        api.setProductCode(productCode);
        api.setOffset(offset);
        api.setLimit(limit);

        SearchResponseAPI searchResponse = orderService.findByProduct(api);
        if (searchResponse.getOrder() == null || searchResponse.getOrder().size() < 1) {
            return Response.noContent().build();
        } else {
            return Response.ok(searchResponse).build();
        }
    }
}
