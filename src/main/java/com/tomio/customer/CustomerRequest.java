package com.tomio.customer;

public record CustomerRequest(String name, String email, Integer age, Integer phoneNumber) {
}
