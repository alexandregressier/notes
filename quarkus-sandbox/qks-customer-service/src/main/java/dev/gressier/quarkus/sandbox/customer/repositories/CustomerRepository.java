package dev.gressier.quarkus.sandbox.customer.repositories;

import dev.gressier.quarkus.sandbox.customer.model.Customer;
import io.vavr.control.Option;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.vavr.API.None;
import static io.vavr.API.Some;

@Slf4j
@ApplicationScoped
public class CustomerRepository {

    private static final Map<UUID, Customer> customers = new HashMap<>();

    public Customer save(final Customer customer) {
        val id = customer.getId() != null && existsById(customer.getId())
                ? customer.getId()
                : UUID.randomUUID();

        customer.setId(id);
        customers.put(id, customer);
        log.info("Saved customer with id `{}`", id);

        return customer;
    }

    public boolean existsById(final UUID id) {
        return customers.containsKey(id);
    }

    public long count() {
        return customers.size();
    }

    public Option<Customer> findById(final UUID id) {
        return existsById(id)
                ? Some(customers.get(id))
                : None();
    }

    public Collection<Customer> findAll() {
        return customers.values();
    }

    public void deleteById(final UUID id) {
        if (existsById(id)) {
            customers.remove(id);
        }
    }

    public void deleteAll() {
        customers.keySet().removeAll(customers.keySet());
    }
}
