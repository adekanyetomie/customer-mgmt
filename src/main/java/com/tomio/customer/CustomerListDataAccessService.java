package com.tomio.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO {
    private static final List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer( 1, "Dami", "dami@gmail.com", 29, 5350));
        customers.add(new Customer( 2, "tee", "tomi@gmail.com", 26, 4123));
        customers.add(new Customer( 3, "Promise", "promise@gmail.com", 24, 2345));
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customers.stream().anyMatch(customer -> customer.getEmail().equals(email));
    }

    @Override
    public boolean existsById(Integer id) {
        return customers.stream().anyMatch(customer -> customer.getId().equals(id));
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.stream().filter(customer -> customer.getId().equals(id)).findFirst().ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
