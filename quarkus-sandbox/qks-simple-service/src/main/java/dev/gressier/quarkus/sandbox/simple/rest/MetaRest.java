package dev.gressier.quarkus.sandbox.simple.rest;

import dev.gressier.quarkus.sandbox.simple.services.MetaService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/meta")
public class MetaRest {

    @Inject
    MetaService metaService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hostname")
    public String hostname() {
        return metaService.hostname();
    }
}
