package com.tomio.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Integer> {
    boolean existsByEmail(String email);
}
