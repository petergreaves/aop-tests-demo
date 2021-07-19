package com.springfirst.aop_tests.services;

import com.springfirst.aop_tests.annotations.BusinessService;
import com.springfirst.aop_tests.domain.Customer;
import com.springfirst.aop_tests.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@BusinessService
@Slf4j
public class CustomerManagerServiceImpl implements CustomerManagerService{

    private final CustomerRepository customerRepository;

    public CustomerManagerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer c) {

        return customerRepository.save(c);
    }

    @Override
    public void removeCustomer(Customer c) {

        customerRepository.delete(c);

    }

    @Override
    public List<Customer> getCustomers() {


        return customerRepository.findAll();
    }
}
