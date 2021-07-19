package com.springfirst.aop_tests.repository;

import com.springfirst.aop_tests.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
