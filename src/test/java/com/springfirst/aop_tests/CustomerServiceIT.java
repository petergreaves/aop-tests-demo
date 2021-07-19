package com.springfirst.aop_tests;

import com.springfirst.aop_tests.aspects.CustomerManagerServiceAOPLogging;
import com.springfirst.aop_tests.domain.Customer;
import com.springfirst.aop_tests.repository.CustomerRepository;
import com.springfirst.aop_tests.services.CustomerManagerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@EnableAutoConfiguration
@ContextConfiguration(classes = com.springfirst.aop_tests.config.TestConfig.class)
@Slf4j
public class CustomerServiceIT {

    @Autowired
    private CustomerManagerService customerManagerService;

    @Autowired
    private CustomerManagerServiceAOPLogging customerManagerServiceAOPLogging;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup(){

        Customer c1 = Customer.builder().firstName("Anne").lastName("Jones").build();
        Customer c2 = Customer.builder().firstName("John").lastName("Smith").build();
        customerRepository.save(c1);
        customerRepository.save(c2);

    }
    @AfterEach
    public void destroy(){

        customerRepository.deleteAll();
    }

    @Test
    public void testServiceIsNotNull(){

        Assertions.assertNotNull(customerManagerService);

    }

    @Test
    public void testServiceReturnsTwo(){

        assertEquals(customerManagerService.getCustomers().size(), 2);

    }

    @Test
    public void testServiceRemovesOne(){
        assertEquals(customerManagerService.getCustomers().size(), 2);

    }

    @Test
    public void testServiceAddOne(){

        Customer newCustomer = Customer.builder().firstName("Tony").lastName("Foo").build();
        customerManagerService.addCustomer(newCustomer);
        Assertions.assertAll(
                ()->{ assertEquals(customerManagerService.getCustomers().size(), 3);}

        );
    }

    @Test
    public void testServiceListCustomers(){

        List<Customer> customers = customerManagerService.getCustomers();

        Assertions.assertAll(
                ()->{ assertEquals(customers.size(), 2);}

        );
    }
}
