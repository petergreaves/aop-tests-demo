package com.springfirst.aop_tests.services;

import com.springfirst.aop_tests.domain.Customer;

import java.util.List;

public interface CustomerManagerService {

    Customer addCustomer(Customer c);
    void removeCustomer(Customer c);
    List<Customer> getCustomers();
}
