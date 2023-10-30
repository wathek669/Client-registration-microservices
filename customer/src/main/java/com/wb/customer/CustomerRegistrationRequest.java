package com.wb.customer;

public record CustomerRegistrationRequest(
    String firstName,
    String lastName,
    String email ) {
}
