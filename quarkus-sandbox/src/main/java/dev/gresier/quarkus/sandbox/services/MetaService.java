package dev.gresier.quarkus.sandbox.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MetaService {

    public String hostname() {
        return System.getenv().getOrDefault("HOSTNAME", "unknown");
    }
}
