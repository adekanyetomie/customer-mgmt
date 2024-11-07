package com.tomio;

import com.tomio.customer.Customer;
import com.tomio.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository repository) {
        return args -> {
         List<Customer> customers = List.of(
                 new Customer( 1, "Dami", "dami@gmail.com", 29, 5350),
                 new Customer( 2, "tee", "tomi@gmail.com", 26, 4123),
                 new Customer( 3, "Promise", "promise@gmail.com", 24, 2345));
         repository.saveAll( customers );
        };
    }
}
