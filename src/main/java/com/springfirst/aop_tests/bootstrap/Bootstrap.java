package com.springfirst.aop_tests.bootstrap;

import com.springfirst.aop_tests.domain.Customer;
import com.springfirst.aop_tests.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public Bootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args){

        Customer c1 = Customer.builder().firstName("Anne").lastName("Jones").build();
        Customer c2 = Customer.builder().firstName("John").lastName("Smith").build();
        customerRepository.save(c1);
        customerRepository.save(c2);
        List<Customer> customerList = customerRepository.findAll();
        log.info("Number of customers saved : " + customerList.size());

    }
}
