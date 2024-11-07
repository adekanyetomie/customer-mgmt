package com.tomio.customer;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    private List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer/{id}")
    private Customer getCustomerById(@PathVariable("id") int id) {
        return  customerService.getCustomerById(id);
    }

    @PostMapping()
    private void addCustomer(@RequestBody CustomerRequest request){
        customerService.addCustomer(request);
    }

    @DeleteMapping("/customer/{id}")
    private void deleteCustomer(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
    }

    @PutMapping("/customer/{id}")
    private void updateCustomer(@PathVariable("id") int id,  @RequestBody CustomerRequest request){
        customerService.updateCustomer(id, request);
    }
}
