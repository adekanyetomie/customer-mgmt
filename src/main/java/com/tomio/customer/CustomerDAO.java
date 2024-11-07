package com.tomio.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAll();
    Optional<Customer> findById(Integer id);
    void addCustomer(Customer customer);
    boolean existsByEmail(String email);
    boolean existsById(Integer id);
    void deleteCustomer(Integer id);
    void updateCustomer(Customer customer);
}
