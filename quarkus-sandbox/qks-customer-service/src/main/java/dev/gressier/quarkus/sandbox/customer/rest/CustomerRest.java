package dev.gressier.quarkus.sandbox.customer.rest;

import dev.gressier.quarkus.sandbox.customer.model.Customer;
import dev.gressier.quarkus.sandbox.customer.repositories.CustomerRepository;
import io.vavr.collection.List;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@ApplicationScoped
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerRest {

    @Inject
    private CustomerRepository customerRepository;

    @POST
    public Response post(final Customer customer) {
        return Response
                .status(Response.Status.CREATED)
                .entity(customerRepository.save(customer))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") final UUID id) {
        val customer = customerRepository.findById(id);
        if (customer.isDefined()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(customer.get())
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    public List<Customer> getAll() {
        return List.of(new Customer());
    }
}
