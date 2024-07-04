package com.example.adapter.in.web;

import java.util.List;
import java.util.Optional;

import com.example.application.service.OrderService;
import com.example.domain.model.Order;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(Order order) {
        Order createdOrder = orderService.save(order);
        return Response.status(Response.Status.CREATED).entity(createdOrder).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Long id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(value -> Response.ok(value).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public Response getAllOrders() {
        List<Order> orders = orderService.findAll();
        return Response.ok(orders).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteById(id);
        return Response.noContent().build();
    }
}
