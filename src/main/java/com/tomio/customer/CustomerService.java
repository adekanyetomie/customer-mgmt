package com.tomio.customer;

import com.tomio.exceptions.DuplicateResourceException;
import com.tomio.exceptions.ResourceNotFoundException;
import com.tomio.exceptions.ResourceNotUpdatedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer with id " + id +" not found"));
    }

    public void addCustomer(CustomerRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerDAO.existsByEmail(email)) {
            throw new DuplicateResourceException("email already exists");
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age(),
                customerRegistrationRequest.phoneNumber());

        customerDAO.addCustomer(customer);

    }

    public void deleteCustomer(Integer id) {
        if (!customerDAO.existsById(id)) {
            throw new ResourceNotFoundException("customer not found");
        }
        customerDAO.deleteCustomer(id);
    }

    public void updateCustomer(Integer id, CustomerRequest customerRequest) {
        if (!customerDAO.existsById(id)) {
            throw new ResourceNotFoundException("customer not found");
        }
        Customer customer = getCustomerById(id);
        boolean updates = false;

        if (customerRequest.name() != null && !customerRequest.name().equals(customer.getName())) {
            customer.setName(customerRequest.name());
            updates = true;
        }
        if (customerRequest.email() != null && !customerRequest.email().equals(customer.getEmail())) {
            customer.setEmail(customerRequest.email());
            updates = true;
        }
        if (customerRequest.age() != null && !customerRequest.age().equals(customer.getAge())) {
            customer.setAge(customerRequest.age());
            updates = true;
        }
        if (customerRequest.phoneNumber() != null && !customerRequest.phoneNumber().equals(customer.getPhoneNumber())) {
            customer.setPhoneNumber(customerRequest.phoneNumber());
            updates = true;
        }
        if (!updates) {
            throw new ResourceNotUpdatedException("no data changes found ");
        }
        customerDAO.updateCustomer(customer);
    }

}
