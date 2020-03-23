package dev.gressier.quarkus.sandbox.rest;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/hello")
public class GreetingRest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello!";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{name}")
    public String hello(@PathParam("name") final String name) {
        log.info("`hello` called with `{}`", name);
        return String.format("Hello %s!", name);
    }
}
