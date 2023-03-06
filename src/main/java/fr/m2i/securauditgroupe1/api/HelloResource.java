package fr.m2i.securauditgroupe1.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;


@Path("/hello-world")
public class HelloResource {
    @GET
    @Path("/test")
    public String hello() {
        return "Hello, World!";
    }
}