package dev.gressier.quarkus.sandbox.customer.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {

    private UUID id;
    private String firstName;
    private String lastName;
}
